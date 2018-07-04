package com.stworks.friends.friends;

import com.stworks.common.base.bean.BaseEntity;
import com.stworks.common.base.bean.UserInfo;
import com.stworks.common.base.mvp.HttpCallBack;
import com.stworks.common.base.mvp.MainPresenter;
import com.stworks.common.base.mvp.MainView;
import com.stworks.friends.bean.FriendsList;

import java.util.List;

/**
 * Created by wwq on 2018/7/4.
 */

public interface FriendContract {

    interface FriendView extends MainView {
        /**
         * 点赞
         */
        void doParise();

        /**
         * 发表评论
         */
        void doComment();

        /**
         * 数据获取接口
         *
         * @param friendsLists
         */
        void showData(List<FriendsList> friendsLists);

        void showMoreData(List<FriendsList> friendsLists);

        /**
         * 数据加载完成，没有更多数据
         */
        void loadFriendsEnd();

        /**
         * 加载失败
         */
        void loadFriendsFailed();

        void showUserInfo(UserInfo userInfo);
    }


    interface FriendPresenter extends MainPresenter<FriendView> {
        void getData(int pageSize, int pageNumber);

        void getUserInfo();
    }

    interface FriendModel {
        void getData(HttpCallBack<List<FriendsList>> callBack, int pageSize, int pageNumber);

        void getUserInfo(HttpCallBack<UserInfo> userInfo);
    }

}
