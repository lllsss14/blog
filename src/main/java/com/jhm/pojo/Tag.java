package com.jhm.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class Tag {
    private Long id;
    private String name;

    private List<BlogInfo> blogInfos=new ArrayList<>();
    public Tag() {
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
