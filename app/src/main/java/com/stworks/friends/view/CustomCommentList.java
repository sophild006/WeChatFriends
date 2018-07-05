package com.stworks.friends.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.stworks.common.span.TextMovementMethod;
import com.stworks.common.utils.SimpleWeakObjectPool;
import com.stworks.common.utils.UiUtils;
import com.stworks.friends.R;
import com.stworks.friends.bean.CommentInfo;
import java.util.List;

/**
 * Created by wwq on 2018/7/5.
 */
public class CustomCommentList extends LinearLayout implements ViewGroup.OnHierarchyChangeListener {

    private List<CommentInfo> mCommentBeans;
    private LayoutParams mLayoutParams;
    private SimpleWeakObjectPool<View> COMMENT_TEXT_POOL;
    private int mCommentVerticalSpace;

    public CustomCommentList(Context context) {
        super(context);
        init();
    }

    public CustomCommentList(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomCommentList(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mCommentVerticalSpace = UiUtils.dip2px(3f);
        COMMENT_TEXT_POOL = new SimpleWeakObjectPool<>();
        setOnHierarchyChangeListener(this);
    }


    public void addComments(List<CommentInfo> commentBeans, boolean isStartAnimation) {
        this.mCommentBeans = commentBeans;
        if (commentBeans != null) {
            int oldCount = getChildCount();
            int newCount = commentBeans.size();
            if (oldCount > newCount) {
                removeViewsInLayout(newCount, oldCount - newCount);
            }
            for (int i = 0; i < newCount; i++) {
                boolean hasChild = i < oldCount;
                View childView = hasChild ? getChildAt(i) : null;
                CommentInfo commentBean = commentBeans.get(i);
                SpannableStringBuilder commentSpan = commentBean.getCommentContentSpan();
                if (childView == null) {
                    childView = COMMENT_TEXT_POOL.get();
                    if (childView == null) {
                        addViewInLayout(makeCommentItemView(commentSpan, i, isStartAnimation), i, generateMarginLayoutParams(i), true);
                    } else {
                        addCommentItemView(childView, commentSpan, i, isStartAnimation);
                    }
                } else {
                    updateCommentData(childView, commentSpan, i, isStartAnimation);
                }
            }
            requestLayout();
        }
    }


    /**
     * 更新指定的position的comment
     */
    public void updateTargetComment(int position, List<CommentInfo> commentBeans) {
        int oldCount = getChildCount();
        for (int i = 0; i < oldCount; i++) {
            if (i == position) {
                View childView = getChildAt(i);
                if (childView != null) {
                    CommentInfo commentBean = commentBeans.get(i);
                    SpannableStringBuilder commentSpan = commentBean.getCommentContentSpan();
                    updateCommentData(childView, commentSpan, i, true);
                }
                break;
            }
        }
        requestLayout();
    }


    /**
     * 創建Comment item view
     */
    private View makeCommentItemView(SpannableStringBuilder content, int index, boolean isStartAnimation) {
        return makeContentTextView(content);
    }


    /**
     * 添加需要的Comment View
     */
    private void addCommentItemView(View view, SpannableStringBuilder builder, int index, boolean isStartAnimation) {
        if (view instanceof TextView) {
            ((TextView) view).setText(builder);
            addViewInLayout(view, index, generateMarginLayoutParams(index), true);
        }
    }

    private void updateCommentData(View view, SpannableStringBuilder builder, int index, boolean isStartAnimation) {
        if (view instanceof TextView) {
            ((TextView) view).setText(builder);
        }
    }

    private TextView makeContentTextView(SpannableStringBuilder content) {
        TextView textView = new TextView(getContext());
        textView.setTextColor(Color.parseColor("#333333"));
        textView.setBackgroundResource(R.drawable.selector_view_name_state);
        textView.setTextSize(16f);
        textView.setLineSpacing(mCommentVerticalSpace, 1f);
        textView.setText(content);
        textView.setMovementMethod(new TextMovementMethod());
        return textView;
    }


    private LayoutParams generateMarginLayoutParams(int index) {
        if (mLayoutParams == null) {
            mLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        if (mCommentBeans != null && index > 0) {
            mLayoutParams.bottomMargin = index == mCommentBeans.size() - 1 ? 0 : mCommentVerticalSpace;
        }
        return mLayoutParams;
    }

    @Override
    public void onChildViewAdded(View parent, View child) {

    }

    @Override
    public void onChildViewRemoved(View parent, View child) {
        COMMENT_TEXT_POOL.put(child);
    }
}
