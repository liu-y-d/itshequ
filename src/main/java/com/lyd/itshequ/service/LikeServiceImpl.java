package com.lyd.itshequ.service;

import com.alibaba.fastjson.JSON;
import com.lyd.itshequ.bean.LikeDTO;
import com.lyd.itshequ.exception.MeErrorCode;
import com.lyd.itshequ.exception.MeExceptions;
import com.lyd.itshequ.mapper.LikeMapper;
import com.lyd.itshequ.mapper.PostMapper;
import com.lyd.itshequ.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "like")
@Service
public class LikeServiceImpl implements LikeService{

    @Autowired
    private LikeMapper likeMapper;
    @Autowired
    private PostMapper postMapper;
    @CachePut(/*key = "#result.id"*/)
    @Override
    public LikeDTO like(LikeDTO likeDTO) {
        LikeDTO getDTO = getLikeDTO(likeDTO);
        Post postById = postMapper.getPostById(likeDTO.getPostId());
        if (getDTO == null){
            LikeDTO insertLikeDTO = new LikeDTO();
            insertLikeDTO.setPostId(likeDTO.getPostId());
            insertLikeDTO.setUserId(likeDTO.getUserId());
            insertLikeDTO.setLikeStatus(1);
            Integer like = likeMapper.like(insertLikeDTO);
            if (like == 1){
                postById.setLikeCount(postById.getLikeCount()+1);
                int i = postMapper.updatePost(postById);
                return insertLikeDTO;
            }else{
                throw new MeExceptions(MeErrorCode.SYS_ERROR);
            }
        }else{
            if (getDTO.getLikeStatus()==0){
                getDTO.setLikeStatus(1);
                postById.setLikeCount(postById.getLikeCount()+1);
                int i = postMapper.updatePost(postById);
            }else {
                postById.setLikeCount(postById.getLikeCount()-1);
                int i = postMapper.updatePost(postById);
                getDTO.setLikeStatus(0);
            }
            Integer integer = likeMapper.updateLikeDTO(getDTO);
            if (integer == 1){
                return getDTO;
            }else {
                throw new MeExceptions(MeErrorCode.SYS_ERROR);
            }
        }
    }

    @Cacheable(/*key = "#likeDTO.id",*/unless="#result == null")
    @Override
    public LikeDTO getLikeDTO(LikeDTO likeDTO){
        LikeDTO getDTO = likeMapper.getLikeDTO(likeDTO);
        return getDTO;
    }
}
