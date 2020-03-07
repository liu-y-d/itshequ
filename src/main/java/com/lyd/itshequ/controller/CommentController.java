package com.lyd.itshequ.controller;

import com.lyd.itshequ.bean.CommentCreateDTO;
import com.lyd.itshequ.bean.CommentDTO;
import com.lyd.itshequ.bean.ResultDTO;
import com.lyd.itshequ.enums.CommentTypeEnum;
import com.lyd.itshequ.exception.MeErrorCode;
import com.lyd.itshequ.model.Comment;
import com.lyd.itshequ.model.User;
import com.lyd.itshequ.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    //帖子评论
    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object comment(@RequestBody CommentCreateDTO commentCreateDTO, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return ResultDTO.errorOf(MeErrorCode.NO_LOGIN);
        }
        if (commentCreateDTO == null|| StringUtils.isBlank(commentCreateDTO.getCommentValue())){
            return ResultDTO.errorOf(MeErrorCode.NOT_NULL);
        }
        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setType(commentCreateDTO.getType());
        comment.setCommentValue(commentCreateDTO.getCommentValue());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setId(commentCreateDTO.getParentId());
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
    //二级评论
    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDTO<List> secondComment(@PathVariable("id")Long id){
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }

}
