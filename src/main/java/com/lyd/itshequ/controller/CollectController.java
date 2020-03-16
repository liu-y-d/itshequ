package com.lyd.itshequ.controller;

import com.alibaba.fastjson.JSON;
import com.lyd.itshequ.bean.CollectDTO;
import com.lyd.itshequ.bean.LikeDTO;
import com.lyd.itshequ.exception.MeErrorCode;
import com.lyd.itshequ.exception.MeExceptions;
import com.lyd.itshequ.mapper.CollectMapper;
import com.lyd.itshequ.model.User;
import com.lyd.itshequ.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CollectController {
    @Autowired
    private CollectService collectService;
    @GetMapping("/collect")
    public String toCollectPage(HttpServletRequest request, Model model){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            throw new MeExceptions(MeErrorCode.NO_LOGIN);
        }else {
            List<CollectDTO> collectDTOByUserId = collectService.getCollectByUserId(user.getId());
            model.addAttribute("collects",collectDTOByUserId);
        }
        return "MyCollect";
    }
    @ResponseBody
    @GetMapping("/collect/{postId}")
    public String collect(@PathVariable("postId")Long postId, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        //信息校验
        if (user==null){
            throw new MeExceptions(MeErrorCode.NO_LOGIN);
        }
        if (postId==null){
            throw new MeExceptions(MeErrorCode.READ_ERROR);
        }
        //根据帖子id和用户id查询点赞信息
        CollectDTO collectDTO = new CollectDTO();
        collectDTO.setPostId(postId);
        collectDTO.setUserId(user.getId());
        CollectDTO dto = collectService.collect(collectDTO);
        return JSON.toJSONString(dto);
    }
}
