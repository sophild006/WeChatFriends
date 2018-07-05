package com.stworks.friends.friends;

import android.os.Handler;

import com.google.gson.Gson;
import com.stworks.common.base.bean.BaseEntity;
import com.stworks.common.base.bean.UserInfo;
import com.stworks.common.base.mvp.HttpCallBack;
import com.stworks.common.utils.GlobalContext;
import com.stworks.common.utils.JsonConvert;
import com.stworks.common.utils.SWLog;
import com.stworks.friends.bean.FriendsList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wwq on 2018/7/4.
 */

public class FriendModelImpl implements FriendContract.FriendModel {
    @Override
    public void getData(final HttpCallBack<List<FriendsList>> callBack, final int pageSize, final int pageNumber) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String friendsJson = JsonConvert.createData("FriendsJson", GlobalContext.getAppContext());
                FriendsList[] friendsLists = new Gson().fromJson(friendsJson, FriendsList[].class);
                if (friendsLists != null && friendsLists.length > 0) {
                    // TODO: 2018/7/4
                    if (callBack != null) {
                        List<FriendsList> friendsLists1 = Arrays.asList(friendsLists);
                        List<FriendsList> mList = new ArrayList<>(friendsLists1);
                        if (mList.size() >= pageNumber * pageSize + 5) {
                            callBack.onResponse(mList.subList(pageNumber * pageSize, pageNumber * pageSize + 5), null);
                        } else {
                            callBack.onResponse(new ArrayList<FriendsList>(), null);
                        }
//                        else {
//                            callBack.onResponse(mList.subList((pageNumber - 1) * pageSize + 5, mList.size()), null);
//                        }
                    }
                } else {
                    SWLog.d("friendsLists  is null");
                }
            }
        }, 2000);
    }

    @Override
    public void getUserInfo(HttpCallBack<UserInfo> callBack) {
        BaseEntity<UserInfo> userInfo_ = JsonConvert.fromJson("userInfo", GlobalContext.getAppContext(), UserInfo.class);
        callBack.onResponse(userInfo_.getRt(), null);
    }

    @Override
    public void doComment(HttpCallBack callBack) {

        if (callBack != null) {
            callBack.onResponse(null, null);
        }
    }

    @Override
    public void doParise(HttpCallBack callBack) {
        if (callBack != null) {
            callBack.onResponse(null, null);
        }
    }
}
