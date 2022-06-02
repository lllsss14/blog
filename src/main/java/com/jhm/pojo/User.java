package com.jhm.pojo;



import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class User {
    private Long id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Integer type;
    private Timestamp create_time;
    private Timestamp update_time;


    private List<BlogInfo> blogInfos;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", type=" + type +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
}
