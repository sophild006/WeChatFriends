package com.stworks.friends.bean;

import android.content.Context;
import android.text.SpannableStringBuilder;
import com.stworks.common.utils.StringUtils;

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

    public final static class CommentType {
        //单一评论
        public final static int COMMENT_TYPE_SINGLE = 0;
        //回复评论
        public final static int COMMENT_TYPE_REPLY = 1;
    }

    /**
     * 富文本内容
     */
    private SpannableStringBuilder commentContentSpan;

    public SpannableStringBuilder getCommentContentSpan() {
        return commentContentSpan;
    }

    /**
     * 默认单一评论
     *
     * @param context
     */
    public void build(Context context) {
        if (0 == CommentType.COMMENT_TYPE_SINGLE) {
            commentContentSpan = StringUtils.createCommentSpan(context, getSender().getNick(), getContent());
        }
    }
}
