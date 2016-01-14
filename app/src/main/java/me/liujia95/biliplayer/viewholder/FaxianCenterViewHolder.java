package me.liujia95.biliplayer.viewholder;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.data.FaxianData;
import me.liujia95.biliplayer.utils.UIUtils;
import me.liujia95.biliplayer.view.FlowLayout;
import me.liujia95.biliplayer.view.MyScrollView;

/**
 * Created by Administrator on 2016/1/14 16:12.
 */
public class FaxianCenterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @InjectView(R.id.faxian_center_wiki)
    MyScrollView mWiki;
    @InjectView(R.id.faxian_center_tv)
    TextView     mTv;
    @InjectView(R.id.faxian_bottom_arrow)
    ImageView    mIvArrow;
    @InjectView(R.id.faxian_center_container)
    LinearLayout mContainer;

    private boolean isOpened;

    private final String[] mDatas;

    public FaxianCenterViewHolder(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
        mDatas = FaxianData.createDatas();
        initData();

        mTv.setOnClickListener(this);
        mIvArrow.setOnClickListener(this);
    }

    private void initData() {
        if (mDatas == null) {
            mContainer.setVisibility(View.GONE);
            return;
        }
        mContainer.setVisibility(View.VISIBLE);

        FlowLayout mLayout = new FlowLayout(UIUtils.getContext());
        mWiki.addView(mLayout);

        ViewGroup.LayoutParams layoutParams = mWiki.getLayoutParams();
        layoutParams.height = UIUtils.dip2px(80);
        mWiki.setLayoutParams(layoutParams);

        mLayout.setSpace(UIUtils.dip2px(12), UIUtils.dip2px(12));

        for (int i = 0; i < mDatas.length; i++) {

            TextView tv = new TextView(UIUtils.getContext());
            tv.setText(mDatas[i]);
            tv.setPadding(UIUtils.dip2px(5), UIUtils.dip2px(5), UIUtils.dip2px(5), UIUtils.dip2px(5));
            //设置字体颜色的选择器
            ColorStateList colorSateList = (ColorStateList) UIUtils.getResources().getColorStateList(R.color.faxian_text_selector);
            tv.setTextColor(colorSateList);

            GradientDrawable normal = new GradientDrawable();
            normal.setShape(GradientDrawable.RECTANGLE);
            normal.setCornerRadius(UIUtils.dip2px(3));
            normal.setColor(Color.parseColor("#ffffff"));

            GradientDrawable pressed = new GradientDrawable();
            pressed.setShape(GradientDrawable.RECTANGLE);
            pressed.setCornerRadius(UIUtils.dip2px(3));
            pressed.setColor(Color.parseColor("#97445C"));

            StateListDrawable selector = new StateListDrawable();
            selector.addState(new int[]{android.R.attr.state_pressed}, pressed);
            selector.addState(new int[]{}, normal);

            tv.setBackgroundDrawable(selector);
            final int index = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(UIUtils.getContext(), "" + mDatas[index], Toast.LENGTH_SHORT).show();
                }
            });
            mLayout.addView(tv);
        }
    }

    @Override
    public void onClick(View v) {
        if (!isOpened) {
            ObjectAnimator.ofFloat(mIvArrow, "rotation", 0, 180).start();
            animate(UIUtils.dip2px(80), UIUtils.dip2px(200));
            mTv.setText("收起");
            //设置可滑动
            mWiki.setCanScroll(true);
        } else {
            ObjectAnimator.ofFloat(mIvArrow, "rotation", -180, 0).start();
            animate(UIUtils.dip2px(200), UIUtils.dip2px(80));
            mTv.setText("查看更多");
            //设置不可滑动
            mWiki.setCanScroll(false);
        }
        isOpened = !isOpened;
    }

    private void animate(int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.setDuration(400);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = mWiki.getLayoutParams();
                layoutParams.height = value;
                mWiki.setLayoutParams(layoutParams);
            }
        });
        animator.start();
    }
}
