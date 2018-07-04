package com.stworks.friends.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.stworks.common.utils.SWLog;
import com.stworks.common.utils.ViewUtils;
import com.stworks.friends.Config.ViewConfig;
import com.stworks.friends.R;
import com.stworks.friends.bean.FriendsList;

/**
 * Created by wwq on 2018/7/4.
 */

public class FriendsAdapter extends BaseQuickAdapter<FriendsList, BaseViewHolder> {

    private int layoutId;

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
        if (viewType == ViewConfig.ONLY_WORDS) {
            return new OnlyWordViewHolder(getItemView(layoutId, parent));
        } else if (viewType == ViewConfig.WORD_AND_PICTURE) {
            return new WithPictureViewHolder(getItemView(layoutId, parent));
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendsList item) {

        ImageView ivHeader = helper.getView(R.id.iv_header);
        TextView tvUserName = helper.getView(R.id.tv_user_name);
        tvUserName.setText(TextUtils.isEmpty(item.getSender().getNick()) ? ViewUtils.getContent(item.getSender().getUsername()) : item.getSender().getNick());
        Glide.with(mContext).load(item.getSender().getAvatar()).into(ivHeader);
        ViewUtils.intoImageView(mContext, item.getSender().getAvatar(), ivHeader);


    }

    static class OnlyWordViewHolder extends BaseViewHolder {

        public OnlyWordViewHolder(View view) {
            super(view);
            ViewStub viewStub = (ViewStub) view.findViewById(R.id.viewStub);
            if (viewStub != null) {
                viewStub.setLayoutResource(R.layout.friends_header);
                View subViw = viewStub.inflate();
            } else {
                SWLog.d("subView is null");
            }
        }
    }

    static class WithPictureViewHolder extends BaseViewHolder {
        public WithPictureViewHolder(View view) {
            super(view);
            ViewStub viewStub = (ViewStub) view.findViewById(R.id.viewStub);
            if (viewStub != null) {
                viewStub.setLayoutResource(R.layout.friends_header);
                View subViw = viewStub.inflate();
            } else {
                SWLog.d("subView is null");
            }
        }
    }
}
