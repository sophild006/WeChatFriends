package com.stworks.friends.bean;

import java.util.List;

public class FriendsList {
    private String content;
    private List<Images> images;
    private SenderInfo sender;
    private List<CommentInfo> comments;

    private int itemType;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public SenderInfo getSender() {
        return sender;
    }

    public void setSender(SenderInfo sender) {
        this.sender = sender;
    }

    public List<CommentInfo> getComments() {
        return comments;
    }

    public void setComments(List<CommentInfo> comments) {
        this.comments = comments;
    }


    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public String toString() {
        return "FriendsList{" +
                "content='" + content + '\'' +
                ", images=" + images +
                ", sender=" + sender +
                ", comments=" + comments +
                '}';
    }
}
