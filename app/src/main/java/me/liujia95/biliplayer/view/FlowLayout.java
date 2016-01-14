package me.liujia95.biliplayer.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FlowLayout extends ViewGroup {

    // 记录当前有多少行
    private List<Line> mLines = new ArrayList<Line>();
    private Line mCurrentLine;
    private int horizontalSpace = 10;
    private int verticalSpace   = 10;


    public void setSpace(int horizontalSpace, int verticalSpace) {
        this.horizontalSpace = horizontalSpace;
        this.verticalSpace = verticalSpace;
    }


    public FlowLayout(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 清空
        mLines.clear();
        mCurrentLine = null;

        // 获得容器的宽度
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int maxWidth = widthSize - getPaddingLeft() - getPaddingRight();

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);

            // // measure: 父容器期望孩子的宽度和高度
            // // 32位0010101，头2位模式:
            // // UNSPECIFIED:未声明,孩子宽高随意
            // // EXACTLY:精确的,指定孩子的宽度和高度,后30位 10101-->320px
            // // AT_MOST:最大,宽高不超过某个值，后30位 10101-->320px
            // view.measure(MeasureSpec.makeMeasureSpec(200,
            // MeasureSpec.EXACTLY),
            // MeasureSpec.makeMeasureSpec(200, MeasureSpec.EXACTLY));

            if (view.getVisibility() == View.GONE) {
                continue;
            }

            // 测量孩子
            measureChild(view, widthMeasureSpec, heightMeasureSpec);

            // 将孩子记录到行中
            if (mCurrentLine == null) {
                // 新建行
                mCurrentLine = new Line(maxWidth, horizontalSpace);

                // 记录行
                mLines.add(mCurrentLine);

                // 给行添加孩子
                mCurrentLine.addView(view);
            } else {
                // 判断是否可以添加
                if (mCurrentLine.canAdd(view)) {
                    // 可以添加
                    mCurrentLine.addView(view);
                } else {
                    // 不可以添加
                    // 新建一行
                    mCurrentLine = new Line(maxWidth, horizontalSpace);
                    // 记录行
                    mLines.add(mCurrentLine);

                    // 给行添加孩子
                    mCurrentLine.addView(view);
                }
            }
        }

        // 设置自己的宽度和高度
        int measuredHeight = getPaddingTop() + getPaddingBottom();

        // 添加行的高度
        for (int i = 0; i < mLines.size(); i++) {
            measuredHeight += mLines.get(i).height;
        }

        // 间距
        measuredHeight += (mLines.size() - 1) * verticalSpace;

        // 设置自己的宽度和高度
        setMeasuredDimension(widthSize, measuredHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int top = getPaddingTop();
        for (int i = 0; i < mLines.size(); i++) {
            Line line = mLines.get(i);

            // 自己布局
            line.layout(top, getPaddingLeft());

            top += line.height;

            if (i != mLines.size() - 1) {
                top += verticalSpace;
            }
        }

    }

    private class Line {
        // 属性，方法，构造

        // 行中的控件
        private List<View> mViews = new ArrayList<View>();

        private int maxWidth;// 行最大宽度
        private int usedWidth;// 已经使用的宽度
        private int height;// 行的高度
        private int space;// view和view之间的间隔

        public Line(int maxWidth, int space) {
            this.space = space;
            this.maxWidth = maxWidth;
        }

        public void layout(int top, int left) {
            // 给view布局

            int extraWidth = maxWidth - usedWidth;
            int avgWidth = (int) (extraWidth * 1f / mViews.size() + 0.5f);// 均分的宽度

            for (int i = 0; i < mViews.size(); i++) {
                View view = mViews.get(i);

                int viewWidth = view.getMeasuredWidth();
                int viewHeight = view.getMeasuredHeight();

                if (avgWidth > 0) {

                    // 重新对孩子进行期望
                    view.measure(MeasureSpec.makeMeasureSpec(viewWidth
                            + avgWidth, MeasureSpec.EXACTLY), MeasureSpec
                            .makeMeasureSpec(viewHeight, MeasureSpec.EXACTLY));

                    viewWidth = view.getMeasuredWidth();
                    viewHeight = view.getMeasuredHeight();
                }

                int avgHeight = (int) ((height - viewHeight) / 2f + 0.5f);

                int vLeft = left;
                int vTop = top + avgHeight;
                int vRight = vLeft + viewWidth;
                int vBottom = vTop + viewHeight;
                view.layout(vLeft, vTop, vRight, vBottom);

                left += space + viewWidth;
            }

        }

        /**
         * 添加view的方法
         *
         * @param view
         */
        public void addView(View view) {

            int viewWidth = view.getMeasuredWidth();
            int viewHeight = view.getMeasuredHeight();
            if (mViews.size() == 0) {

                if (viewWidth > maxWidth) {
                    usedWidth = maxWidth;
                    height = viewHeight;
                } else {
                    usedWidth = viewWidth;
                    height = viewHeight;
                }
            } else {
                usedWidth += space + viewWidth;
                height = height > viewHeight ? height : viewHeight;// 选择大的高度
            }

            mViews.add(view);
        }

        /**
         * 判断是否可以往行中添加控件
         *
         * @param view
         */
        public boolean canAdd(View view) {

            if (mViews.size() == 0) {
                return true;
            }

            // 已经使用的 + 间距+ 要加的view的宽度 > maxWidth ,添加不进来
            if (usedWidth + space + view.getMeasuredWidth() > maxWidth) {
                return false;
            }

            return true;
        }
    }

}
