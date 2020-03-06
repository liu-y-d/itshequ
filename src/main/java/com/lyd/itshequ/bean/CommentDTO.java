package com.lyd.itshequ.bean;

import lombok.Data;

@Data
public class CommentDTO {
    private Long parentId;
    private Integer type;
    private String commentValue;
}
