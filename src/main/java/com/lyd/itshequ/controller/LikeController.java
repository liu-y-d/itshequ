package com.lyd.itshequ.controller;

import com.alibaba.fastjson.JSON;
import com.lyd.itshequ.bean.LikeDTO;
import com.lyd.itshequ.exception.MeErrorCode;
import com.lyd.itshequ.exception.MeExceptions;
import com.lyd.itshequ.mapper.LikeMapper;
import com.lyd.itshequ.model.User;
import com.lyd.itshequ.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LikeController {
    @Autowired
    private LikeMapper likeMapper;
    @Autowired
    private LikeService likeService;
    @ResponseBody
    @GetMapping("/like/{postId}")
    public String like(@PathVariable("postId")Long postId, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        //信息校验
        if (user==null){
            throw new MeExceptions(MeErrorCode.NO_LOGIN);
        }
        if (postId==null){
            throw new MeExceptions(MeErrorCode.READ_ERROR);
        }
        //根据帖子id和用户id查询点赞信息
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setPostId(postId);
        likeDTO.setUserId(user.getId());
        LikeDTO dto = likeService.like(likeDTO);
        return JSON.toJSONString(dto);
    }
}
