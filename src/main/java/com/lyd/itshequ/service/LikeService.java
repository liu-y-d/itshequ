package com.lyd.itshequ.service;

import com.lyd.itshequ.bean.LikeDTO;

public interface LikeService {
    LikeDTO getLikeDTO(LikeDTO likeDTO);
    LikeDTO like(LikeDTO likeDTO);
}
