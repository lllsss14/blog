package com.jhm.pojo;

import lombok.Data;

@Data
public class replycomment {
    private Long id;
    private Long parent_id;
    private String avatar;
    private String content;
    private String create_time;
    private String email;
    private String nickname;
    private Long blog_id;
    private boolean adminComment;

    private Comment comment;
    private BlogInfo blogInfo;

    @Override
    public String toString() {
        return "replycomment{" +
                "id=" + id +
                ", parent_id=" + parent_id +
                ", avatar='" + avatar + '\'' +
                ", content='" + content + '\'' +
                ", create_time='" + create_time + '\'' +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", blog_id=" + blog_id +
                ", adminComment=" + adminComment +
                '}';
    }
}
