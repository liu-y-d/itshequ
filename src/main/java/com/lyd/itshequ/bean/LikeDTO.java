package com.lyd.itshequ.bean;

import lombok.Data;

@Data
public class LikeDTO {
    private Long id;
    private Long postId;
    private Long userId;
    private Integer likeStatus;
}
