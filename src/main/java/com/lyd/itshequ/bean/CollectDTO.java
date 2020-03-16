package com.lyd.itshequ.bean;

import com.lyd.itshequ.model.Post;
import lombok.Data;

@Data
public class CollectDTO {
    private Long id;
    private Long userId;
    private Long postId;
    private Post post;
}
