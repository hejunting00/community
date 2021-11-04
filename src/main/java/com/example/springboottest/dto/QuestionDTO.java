package com.example.springboottest.dto;

import com.example.springboottest.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private Integer id;
    private String  title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer comentCount;
    private Integer likeCount;
    private User user;
    //此处所有属性必须和数据库或者其他类类目一样，可忽略下划线 因为fastjson会自动引入非常高级
}
