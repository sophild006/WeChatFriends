package com.stworks.common.base.bean;

/**
 * Created by wwq on 2018/7/5.
 */

public class UserInfo {

    private String avatar;
    private String nick;
    private String username;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "avatar='" + avatar + '\'' +
                ", nick='" + nick + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
