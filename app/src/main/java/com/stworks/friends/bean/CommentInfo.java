package com.stworks.friends.bean;

public class CommentInfo {
    private String content;
    private SenderInfo sender;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SenderInfo getSender() {
        return sender;
    }

    public void setSender(SenderInfo sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "CommentInfo{" +
                "content='" + content + '\'' +
                ", sender=" + sender +
                '}';
    }
}
