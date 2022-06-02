package com.jhm.pojo;

import lombok.Data;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
public class Comment {
    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    private Timestamp create_time;


    private BlogInfo blogInfo;
    /*子类，一个评论有多个子评论，有点绕自关联*/
    private List<Comment> replayComments;
    /*评论之能有一个父评论就其最近的上层*/
    private Comment parentComment;
    public Comment() {
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", create_time=" + create_time +
                '}';
    }
}
