package me.liujia95.biliplayer.manager;

/**
 *
 */
public class ThreadPoolManager {

    private static ThreadPoolProxy mLongPool;                        // 耗时操作的池子
    private static Object mLongLock = new Object();

    private static ThreadPoolProxy mDownloadPool;                    // 下载的池子
    private static Object mDownloadLock = new Object();

    /**
     * 获得耗时操作的池子
     *
     * @return
     */
    public static ThreadPoolProxy getLongPool() {
        if (mLongPool == null) {
            synchronized (mLongLock) {
                if (mLongPool == null) {
                    mLongPool = new ThreadPoolProxy(3, 3, 0L);
                }
            }
        }
        return mLongPool;
    }

    /**
     * 获得下载的池子
     *
     * @return
     */
    public static ThreadPoolProxy getDownloadPool() {
        if (mDownloadPool == null) {
            synchronized (mDownloadLock) {
                if (mDownloadPool == null) {
                    mDownloadPool = new ThreadPoolProxy(3, 3, 0L);
                }
            }
        }
        return mDownloadPool;
    }
}
