package com.stworks.friends.friends;

import android.content.Context;

import com.stworks.common.base.bean.UserInfo;
import com.stworks.common.base.mvp.HttpCallBack;
import com.stworks.common.base.mvp.MainBasePresenter;
import com.stworks.friends.config.ViewConfig;
import com.stworks.friends.bean.CommentInfo;
import com.stworks.friends.bean.FriendsList;

import java.util.List;

/**
 * Created by wwq on 2018/7/4.
 */

public class FriendPresenterImpl extends MainBasePresenter<FriendContract.FriendView> implements FriendContract.FriendPresenter {
    private FriendContract.FriendModel model;

    public FriendPresenterImpl() {
        model = new FriendModelImpl();
    }

    @Override
    public void init(Context context) {
        super.init(context);
    }


    @Override
    public void getData(int pageSize, final int pageNumber) {
        model.getData(new HttpCallBack<List<FriendsList>>() {
            @Override
            public void onResponse(List<FriendsList> friendsLists, String error) {
                if (friendsLists != null && friendsLists.size() > 0) {
                    for (FriendsList list : friendsLists) {
                        if (list != null && list.getComments() != null && list.getComments().size() > 0) {
                            for (CommentInfo info : list.getComments()) {
                                info.build(mContext);
                            }
                        }
                        if (list.getImages() != null && list.getImages().size() > 0) {
                            list.setItemType(ViewConfig.WORD_AND_PICTURE);
                        } else {
                            list.setItemType(ViewConfig.ONLY_WORDS);
                        }
                    }
                    if (pageNumber == 0) {
                        if (getView() != null)
                            getView().setViewStatus(ViewConfig.NORMAL_STATUS);
                        if (getView() != null)
                            getView().showData(friendsLists);
                    } else {
                        if (getView() != null)
                            getView().showMoreData(friendsLists);
                    }
                } else {
                    if (friendsLists == null) {
                        if (pageNumber == 0) {
                            if (getView() != null)
                                getView().setViewStatus(ViewConfig.ERROR_STATUS);
                        } else {
                            if (getView() != null)
                                getView().loadFriendsFailed();
                        }
                    } else {
                        if (pageNumber == 0) {
                            if (getView() != null)
                                getView().setViewStatus(ViewConfig.EMPTY_STATUS);
                        } else {
                            if (getView() != null)
                                getView().loadFriendsEnd();
                        }
                    }
                }
            }
        }, pageSize, pageNumber);
    }

    @Override
    public void getUserInfo() {
        model.getUserInfo(new HttpCallBack<UserInfo>() {
            @Override
            public void onResponse(UserInfo userInfo, String error) {
                if (getView() != null)
                    getView().showUserInfo(userInfo);
            }
        });
    }

    @Override
    public void doParise() {
        model.doParise(new HttpCallBack() {
            @Override
            public void onResponse(Object o, String error) {
                if (getView() != null)
                    getView().doParise();
            }
        });
    }

    @Override
    public void doComment() {
        model.doComment(new HttpCallBack() {
            @Override
            public void onResponse(Object o, String error) {
                if (getView() != null)
                    getView().doComment();
            }
        });
    }
}