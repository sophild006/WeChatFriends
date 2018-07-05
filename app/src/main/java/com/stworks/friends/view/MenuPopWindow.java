package com.stworks.friends.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.stworks.common.utils.UiUtils;
import com.stworks.friends.R;

import java.util.ArrayList;

/**
 * Created by wwq on 2018/7/5.
 */

public class MenuPopWindow extends PopupWindow implements View.OnClickListener {

    private TextView tvPraise;
    private TextView tvComment;

    // 坐标的位置（x、y）
    private final int[] mLocation = new int[2];
    // 弹窗子类项选中时的监听
    private OnItemClickListener mItemClickListener;

    // 定义弹窗子类项列表
    public void setmItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public MenuPopWindow(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.friends_menu_popupwindow, null);
        tvPraise = (TextView) view.findViewById(R.id.tv_praise);
        tvComment = (TextView) view.findViewById(R.id.tv_comment);
        tvPraise.setOnClickListener(this);
        tvComment.setOnClickListener(this);

        this.setContentView(view);
        this.setWidth(UiUtils.dip2px(160));
        this.setHeight(UiUtils.dip2px(38));
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        this.setAnimationStyle(R.style.friends_menu_pop_anim);

        initItemData();
    }

    private void initItemData() {
    }

    public void showPopupWindow(View parent) {
        parent.getLocationOnScreen(mLocation);
        // 设置矩形的大小
        if (!this.isShowing()) {
            showAtLocation(parent, Gravity.NO_GRAVITY, mLocation[0] - this.getWidth()
                    , mLocation[1] - ((this.getHeight() - parent.getHeight()) / 2));
        } else {
            dismiss();
        }
    }

    @Override
    public void onClick(View view) {
        dismiss();
        switch (view.getId()) {
            case R.id.tv_praise:
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(0);

                }
                break;
            case R.id.tv_comment:
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(1);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 功能描述：弹窗子类项按钮监听事件
     */
    public static interface OnItemClickListener {
        public void onItemClick(int position);
    }
}
