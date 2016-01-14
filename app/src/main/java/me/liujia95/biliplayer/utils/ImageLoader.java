package me.liujia95.biliplayer.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by Administrator on 2016/1/12 19:25.
 */
public class ImageLoader {
    private static ImageLoader mInstance;

    /**
     * 图片缓存的核心对象
     */
    private LruCache<Integer, Bitmap> mLruCache;

    /**
     * 线程池
     */
    private ExecutorService mThreadPool;

    private static final int DEFAULT_THREAD_COUNT = 1;

    /**
     * 队列的调度方式
     */
    private Type mType = Type.LIFO;

    /**
     * 任务队列
     */
    private LinkedList<Runnable> mTaskQueue;

    /**
     * 后台轮询线程
     */
    private Thread  mPoolThread;
    private Handler mPoolThreadHandler;

    /**
     * 创建一个信号量，避免PoolThreadHandler并发异常
     */
    private Semaphore mSemaphorePoolThreadHandler = new Semaphore(0);

    private Semaphore mSemaphoreThreadPool;

    /**
     * UI线程中的Handler
     */
    private Handler mUIHandler;


    private ImageLoader(int threadCount, Type type) {
        init(threadCount, type);
    }

    /**
     * 初始化
     *
     * @param threadCount 线程数
     * @param type        队列的调度方式
     */
    private void init(int threadCount, Type type) {
        //后台轮询线程
        mPoolThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                mPoolThreadHandler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        //从线程池取出一个任务去执行
                        mThreadPool.execute(getTask());

                        try {
                            mSemaphoreThreadPool.acquire();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };

                //添加完成后，释放一个信号量
                mSemaphorePoolThreadHandler.release();
                Looper.loop();
            }
        });

        mPoolThread.start();

        //获取应用的最大可用内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheMemory = maxMemory / 8;
        mLruCache = new LruCache<Integer, Bitmap>(cacheMemory) {
            @Override
            protected int sizeOf(Integer key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };

        //创建线程池
        mThreadPool = Executors.newFixedThreadPool(threadCount);
        mTaskQueue = new LinkedList<>();
        mType = type;

        //不直接初始化是因为线程数量是可定制的，有可能会出现并行
        mSemaphoreThreadPool = new Semaphore(threadCount);
    }

    /**
     * 从任务队列取出一个任务
     *
     * @return
     */
    private Runnable getTask() {
        if (mType == Type.FIFO) {
            return mTaskQueue.removeFirst();
        } else if (mType == Type.LIFO) {
            return mTaskQueue.removeLast();
        }
        return null;
    }

    /**
     * 获取实例
     *
     * @return
     */
    public static ImageLoader getInstance(int threadCount, Type type) {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader(threadCount, type);
                }
            }
        }
        return mInstance;
    }

    /**
     * 根据resId为imageview设置图片
     *
     * @param resId
     * @param imageview
     */
    public void loadImage(final int resId, final ImageView imageview) {
        imageview.setTag(resId);
        if (mUIHandler == null) {
            mUIHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    //获取得到图片，为imageview回调设置图片
                    ImgBeanHolder holder = (ImgBeanHolder) msg.obj;
                    Bitmap bm = holder.bitmap;
                    ImageView imageView = holder.imageview;
                    int resId = holder.resId;
                    //将path与getTag存储路径进行比对，防止错乱
                    int tag = (int) imageView.getTag();
                    if (tag == resId) {
                        imageView.setImageBitmap(bm);
                    }
                }
            };
        }

        //根据path在缓存中获取bitmap
        Bitmap bm = getBitmapFromLruCache(resId);

        if (bm != null) {
            refreshBitmap(bm, resId, imageview);
        } else {
            addTask(new Runnable() {
                @Override
                public void run() {
                    //加载图片，图片的压缩
                    //1、获得图片需要显示的大小
                    ImageSize imageSize = getImageViewSize(imageview);
                    //2、压缩图片
                    Bitmap bm = decodeSampledBitmapFromPath(resId, imageSize.width, imageSize.height);
                    //3、把图片加入到缓存
                    addBitmapToLruCache(resId, bm);

                    refreshBitmap(bm, resId, imageview);

                    mSemaphoreThreadPool.release();
                }
            });
        }

    }

    private void refreshBitmap(Bitmap bm, int resId, ImageView imageview) {
        Message message = Message.obtain();
        ImgBeanHolder holder = new ImgBeanHolder();
        holder.bitmap = bm;
        holder.resId = resId;
        holder.imageview = imageview;
        message.obj = holder;
        mUIHandler.sendMessage(message);
    }

    /**
     * 把图片加入到LruCache
     *
     * @param resId
     * @param bm
     */
    private void addBitmapToLruCache(int resId, Bitmap bm) {
        if (getBitmapFromLruCache(resId) != null) {
            if (bm != null) {
                mLruCache.put(resId, bm);
            }
        }
    }

    /**
     * 根据图片需要显示的宽和高对图片进行压缩
     *
     * @param resId
     * @param width
     * @param height
     * @return
     */
    private Bitmap decodeSampledBitmapFromPath(int resId, int width, int height) {
        //获得图片的宽和高，不把图片加载到内存
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(UIUtils.getResources(), resId, options);

        options.inSampleSize = caculateInSampleSize(options, width, height);

        //使用获取到的imSampleSize再次解析图片
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeResource(UIUtils.getResources(), resId, options);
        return bitmap;
    }

    /**
     * 根据需求的宽高以及图片的宽高计算SimpleSize
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private int caculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int width = options.outWidth;
        int height = options.outHeight;

        int inSimpleSize = 1;

        if (width > reqWidth || height > reqHeight) {
            //获取长宽比率
            int widthRatio = Math.round(width * 1.0f / reqWidth);
            int heightRatio = Math.round(height * 1.0f / reqHeight);
            //两者之间取最大的比率值
            inSimpleSize = Math.max(widthRatio, heightRatio);
        }
        return inSimpleSize;
    }

    /**
     * 根据ImageView获取适当的压缩宽和高
     *
     * @param imageview
     * @return
     */
    private ImageSize getImageViewSize(ImageView imageview) {
        ImageSize imageSize = new ImageSize();

        DisplayMetrics displayMetrics = imageview.getContext().getResources().getDisplayMetrics();

        ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) imageview.getLayoutParams();
        int width = imageview.getWidth(); //获取imageview实际的宽度
        if (width <= 0) {
            width = lp.width;//获取imageview在layout中声明的宽度
        }
        if (width <= 0) {
            //width = imageview.getMaxWidth();//检查最大值
            width = getImageViewFieldValue(imageview, "mMaxWidth");
        }
        if (width <= 0) {
            width = displayMetrics.widthPixels;
        }

        int height = imageview.getHeight();
        if (height <= 0) {
            height = lp.height;
        }
        if (height <= 0) {
            //height = imageview.getMaxHeight();
            width = getImageViewFieldValue(imageview, "mMaxHeight");
        }
        if (height <= 0) {
            height = displayMetrics.heightPixels;
        }

        imageSize.width = width;
        imageSize.height = height;

        return imageSize;
    }

    /**
     * 通过反射获取imageview的某个属性值
     *
     * @param object
     * @param fieldName
     * @return
     */
    private static int getImageViewFieldValue(Object object, String fieldName) {
        int value = 0;
        try {
            Field field = ImageView.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            int fieldValue = field.getInt(object);
            if (fieldValue > 0 && fieldValue < Integer.MAX_VALUE) {
                value = fieldValue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }

    //同步是为了避免多个线程同时执行造成死锁的现象
    private synchronized void addTask(Runnable runnable) {
        mTaskQueue.add(runnable);

        try {
            if (mPoolThreadHandler == null)
                mSemaphorePoolThreadHandler.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mPoolThreadHandler.sendEmptyMessage(0x100);

    }

    private class ImageSize {
        int width;
        int height;
    }

    private class ImgBeanHolder {
        Bitmap    bitmap;
        ImageView imageview;
        int       resId;
    }

    private Bitmap getBitmapFromLruCache(Integer resId) {
        return mLruCache.get(resId);
    }


    public enum Type {
        FIFO, LIFO;
    }
}
