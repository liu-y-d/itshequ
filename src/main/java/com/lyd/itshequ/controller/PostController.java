package com.lyd.itshequ.controller;

import com.lyd.itshequ.bean.PostDTO;
import com.lyd.itshequ.mapper.PostMapper;
import com.lyd.itshequ.model.Post;
import com.lyd.itshequ.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
	@GetMapping("/post/{id}")
	public String post(@PathVariable("id")Integer id, Model model){
		PostDTO postDTO = postService.getPostById(id);
		model.addAttribute("postDTO",postDTO);
		return "Post";
	}
}
