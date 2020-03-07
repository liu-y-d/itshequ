package com.lyd.itshequ.bean;

import lombok.Data;

@Data
public class CommentCreateDTO {
    private Long parentId;
    private Integer type;
    private String commentValue;
}
