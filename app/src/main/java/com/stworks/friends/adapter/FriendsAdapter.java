package com.stworks.friends.adapter;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.stworks.common.utils.SWLog;
import com.stworks.common.utils.ViewUtils;
import com.stworks.friends.config.ViewConfig;
import com.stworks.friends.R;
import com.stworks.friends.bean.FriendsList;
import com.stworks.friends.friends.FriendContract;
import com.stworks.friends.utils.ServiceUtils;
import com.stworks.friends.view.MenuPopWindow;
import com.stworks.friends.view.MultiImageView;
import com.stworks.friends.view.CustomCommentList;

/**
 * Created by wwq on 2018/7/4.
 */

public class FriendsAdapter extends BaseQuickAdapter<FriendsList, BaseViewHolder> {

    private int layoutId;

    private FriendContract.FriendPresenter  mPresenter;

    public void setPresenter(FriendContract.FriendPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return super.getItemViewType(position);
        }
        if (position == getData().size() + 1) {
            return super.getItemViewType(position);
        }
        return (getData() == null || getData().size() == 0) ? super.getItemViewType(position) : getData().get(position - 1).getItemType();
    }

    public FriendsAdapter(int layoutResId) {
        super(layoutResId);
        this.layoutId = layoutResId;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ViewConfig.WORD_AND_PICTURE) {
            return new WithPictureViewHolder(getItemView(layoutId, parent));
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendsList item) {
        ImageView ivHeader = helper.getView(R.id.iv_header);
        TextView tvUserName = helper.getView(R.id.tv_user_name);
        if (item.getSender() != null) {
            tvUserName.setText(TextUtils.isEmpty(item.getSender().getNick()) ? ViewUtils.getContent(item.getSender().getUsername()) : item.getSender().getNick());
//            Glide.with(mContext).load(item.getSender().getAvatar()).into(ivHeader);
            ViewUtils.intoImageView(mContext, item.getSender().getAvatar(), ivHeader);
        }
        TextView tvContent=helper.getView(R.id.tv_content);
        tvContent.setText(ViewUtils.getContent(item.getContent()));
        LinearLayout llComment = helper.getView(R.id.ll_praise_comment);
        CustomCommentList commentWidget = helper.getView(R.id.vertical_comment_widget);
        TextView tvPraise = helper.getView(R.id.tv_praise_content);
        if (item.getComments() != null && item.getComments().size() > 0) {
            llComment.setVisibility(View.VISIBLE);
            commentWidget.setVisibility(View.VISIBLE);
            commentWidget.addComments(item.getComments(), false);
            tvPraise.setVisibility(View.VISIBLE);
            tvPraise.setText(ServiceUtils.makePraiseSpan(mContext, item.getComments()));
        } else {
            llComment.setVisibility(View.GONE);
            tvPraise.setText("");
            tvPraise.setVisibility(View.GONE);
            commentWidget.setVisibility(View.GONE);
        }

        if (helper.getItemViewType() == ViewConfig.WORD_AND_PICTURE) {
            ((WithPictureViewHolder) helper).multiImageView.setList(item.getImages());
        }

        ImageView ivMenu = helper.getView(R.id.img_click_praise_or_comment);
        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MenuPopWindow popupWindow = new MenuPopWindow(mContext);
                popupWindow.showPopupWindow(view);
                popupWindow.setmItemClickListener(new OnMenuClickListener());
            }
        });
//        helper.addOnClickListener(R.id.img_click_praise_or_comment);
    }

//    static class OnlyWordViewHolder extends BaseViewHolder {
//
//        public OnlyWordViewHolder(View view) {
//            super(view);
//            ViewStub viewStub = (ViewStub) view.findViewById(R.id.viewStub);
//            if (viewStub != null) {
//                viewStub.setLayoutResource(R.layout.friends_header);
//                View subViw = viewStub.inflate();
//            } else {
//                SWLog.d("subView is null");
//            }
//        }
//    }

    static class WithPictureViewHolder extends BaseViewHolder {
        public MultiImageView multiImageView;

        public WithPictureViewHolder(View view) {
            super(view);
            ViewStub viewStub = view.findViewById(R.id.viewStub);
            if (viewStub != null) {
                viewStub.setLayoutResource(R.layout.friends_item_pictures);
                View subViw = viewStub.inflate();
                multiImageView = subViw.findViewById(R.id.multImageView);
            } else {
                SWLog.d("subView is null");
            }
        }
    }


    class OnMenuClickListener implements MenuPopWindow.OnItemClickListener {

        @Override
        public void onItemClick(int position) {
            switch (position) {
                case 0:
                    mPresenter.doParise();
                    break;
                case 1:
                    mPresenter.doComment();
                    break;
            }

        }
    }
}
