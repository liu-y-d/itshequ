package com.lyd.itshequ.controller;

import com.lyd.itshequ.bean.PageDTO;
import com.lyd.itshequ.bean.PostDTO;
import com.lyd.itshequ.enums.NotificationStatusEnum;
import com.lyd.itshequ.exception.MeErrorCode;
import com.lyd.itshequ.exception.MeExceptions;
import com.lyd.itshequ.mapper.PostMapper;
import com.lyd.itshequ.mapper.UserMapper;
import com.lyd.itshequ.model.Post;
import com.lyd.itshequ.model.User;
import com.lyd.itshequ.service.NotificationService;
import com.lyd.itshequ.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName IndexController
 * @Description TODO
 * @Author Liuyunda
 * @Date 2020/2/22 16:00
 **/
@Controller
public class IndexController {

	@Autowired
	private PostService postService;
	@Autowired
	private NotificationService notificationService;

	@GetMapping("/")
	public String index(HttpServletRequest request, Model model, @RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,@RequestParam(name = "search" ,required = false)String search) {

		// 拿到帖子列表
		PageDTO pageDTO = postService.getPostAll(search,page,pageSize);
		//查询通知数
		User user = (User) request.getSession().getAttribute("user");
		if(user == null){
			model.addAttribute("posts", pageDTO);
			return "Index";
		}
		Integer notifyNumber = notificationService.queryNotifyNumber(user.getId(), NotificationStatusEnum.UNREAD.getStatus());
		model.addAttribute("notifyNumber",notifyNumber);
		model.addAttribute("posts", pageDTO);
		return "Index";
	}
	@GetMapping("/toLogin")
	public String toLogin(){
		return "Login";
	}

}
