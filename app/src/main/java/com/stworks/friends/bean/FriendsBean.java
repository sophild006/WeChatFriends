package com.stworks.friends.bean;

import java.util.List;

/**
 * Created by wwq on 2018/7/4.
 */

public class FriendsBean {
    private List<FriendsList> mFriends;

    public List<FriendsList> getmFriends() {
        return mFriends;
    }

    public void setmFriends(List<FriendsList> mFriends) {
        this.mFriends = mFriends;
    }

    @Override
    public String toString() {
        return "FriendsBean{" +
                "mFriends=" + mFriends +
                '}';
    }
}
