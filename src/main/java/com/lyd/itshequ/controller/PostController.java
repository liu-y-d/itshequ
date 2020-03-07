package com.lyd.itshequ.controller;

import com.lyd.itshequ.bean.CommentDTO;
import com.lyd.itshequ.bean.PostDTO;
import com.lyd.itshequ.enums.CommentTypeEnum;
import com.lyd.itshequ.service.CommentService;
import com.lyd.itshequ.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @ClassName PostController
 * @Description TODO
 * @Author Liuyunda
 * @Date 2020/2/23 23:11
 **/
@Controller
public class PostController {
	@Autowired
	private PostService postService;
	@Autowired
	private CommentService commentService;
	@GetMapping("/post/{id}")
	public String post(@PathVariable("id")Long id, Model model){
		List<CommentDTO> commentDTOList = commentService.listByTargetId(id, CommentTypeEnum.POST);
		//累加阅读数
		PostDTO postDTO = postService.incView(id);
		model.addAttribute("comments",commentDTOList);
		model.addAttribute("postDTO",postDTO);
		return "Post";
	}
}
