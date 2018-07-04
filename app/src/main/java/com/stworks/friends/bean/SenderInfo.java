package com.stworks.friends.bean;

public class SenderInfo {
    private String username;
    private String nick;
    private String avatar;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "SenderInfo{" +
                "username='" + username + '\'' +
                ", nick='" + nick + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}