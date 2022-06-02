package com.jhm.pojo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class BlogInfo {
     private Long id;
     private String title;
     private String content;
     private String picture;
     private String flag;
     private Integer views;
     private boolean appreciation;
     private boolean copyright;
     private boolean commented;
     private boolean published;
     private boolean recommend;
     private Timestamp create_time;
     private Timestamp update_time;

     //这个属性用来在mybatis中进行连接查询的
     private Long typeId;
     private Long userId;
     //获取多个标签的id，这个是不给数据库的，单纯的来根据前端传过来的tagid去
     private String tagIds;

     private Type type;
     private List<Tag> tags=new ArrayList<>();
     private User user;
     private List<Comment> comments =new ArrayList<>();

     public BlogInfo() {
     }

     public void init(){
          this.tagIds=tagsToIds(this.getTags());
     }
     /*将tags数组[1,2,3]转换为tagIds字符串形式：“1,2,3”,用于编辑博客时显示博客的tag*/
     private String tagsToIds(List<Tag> tags){
          if(!tags.isEmpty()){
               StringBuffer ids = new StringBuffer();
               boolean flag = false;
               for(Tag tag: tags){
                    if(flag){
                         ids.append(",");/*加上逗号*/
                    }else {
                         flag = true;
                    }
                    ids.append(tag.getId());
               }
               return ids.toString();
          }else {
               return tagIds;
          }
     }

     @Override
     public String toString() {
          return "BlogInfo{" +
                  "id=" + id +
                  ", title='" + title + '\'' +
                  ", content='" + content + '\'' +
                  ", picture='" + picture + '\'' +
                  ", flag='" + flag + '\'' +
                  ", views=" + views +
                  ", appreciation=" + appreciation +
                  ", copyright=" + copyright +
                  ", commented=" + commented +
                  ", published=" + published +
                  ", recommend=" + recommend +
                  ", create_time=" + create_time +
                  ", update_time=" + update_time +
                  ", typeId=" + typeId +
                  ", userId=" + userId +
                  ", type=" + type +
                  ", tags=" + tags +
                  ", user=" + user +
                  ", comments=" + comments +
                  '}';
     }
}
