package com.lyd.itshequ.controller;

import com.lyd.itshequ.mapper.PostMapper;
import com.lyd.itshequ.model.Post;
import com.lyd.itshequ.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName PublishController
 * @Description TODO
 * @Author Liuyunda
 * @Date 2020/2/22 17:15
 **/
@Controller
public class PublishController {

	@Autowired
	private PostMapper postMapper;

	@GetMapping("/publish")
	public String publish() {
		return "Publish";
	}

	@PostMapping("/publish")
	public String doPublish(Post post,Model model,HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null){
			model.addAttribute("errormsg","未获取到登录信息");
			return "error";
		}
		if (StringUtils.isBlank(post.getTitle())||StringUtils.isBlank(post.getDescription())||StringUtils.isBlank(post.getTag())){
			model.addAttribute("errormsg","填写内容不能为空");
			return "error";
		}
		post.setCreator(user.getId());
		post.setGmtCreate(user.getGmtCreate());
		post.setGmtModified(user.getGmtModified());
		postMapper.create(post);
		System.out.println(post.toString());
		return "redirect:/";

	}
}
