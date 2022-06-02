package com.jhm.pojo;


import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class Type {
    private Long id;
    /*JPA自带NotBlank，但是我们是mybatis所以要自己引入依赖*/
    @NotNull(message = "分类名字不能为空")
    private String name;

    private List<BlogInfo> blogInfos;
    public Type() {
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
