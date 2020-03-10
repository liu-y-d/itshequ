package com.lyd.itshequ.controller;

import com.lyd.itshequ.bean.NotificationDTO;
import com.lyd.itshequ.bean.PageDTO;
import com.lyd.itshequ.enums.NotificationStatusEnum;
import com.lyd.itshequ.model.User;
import com.lyd.itshequ.service.NotificationService;
import com.lyd.itshequ.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName ProfileController
 * @Description TODO
 * @Author Liuyunda
 * @Date 2020/2/23 18:08
 **/
@Controller
public class ProfileController {
	@Autowired
	private PostService postService;
	@Autowired
	private NotificationService notificationService;
	@GetMapping("/profile")
	public String toMyPost(HttpServletRequest request,String action, Model model, @RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize){
		User user = (User) request.getSession().getAttribute("user");
		if (user == null){
			model.addAttribute("errormsg","未获取到登录信息");
			return "errorPage";
		}
		if ("myPost".equals(action)){

			PageDTO pageDTO = postService.getPostByCreator(user.getId(),page,pageSize);
			model.addAttribute("posts", pageDTO);
			model.addAttribute("section","myPost");
			model.addAttribute("sectionName","我的帖子");

		}else if ("replies".equals(action)){
			PageDTO pageDTO = notificationService.list(user.getId(),page,pageSize);
			Long unReadCount = notificationService.unReadCount(user.getId());
			model.addAttribute("section","replies");
			model.addAttribute("posts", pageDTO);
			model.addAttribute("unReadCount", unReadCount);
			model.addAttribute("sectionName","我的回复");

		}else {
			model.addAttribute("errormsg","请勿修改URL");
			return "errorPage";
		}
		Integer notifyNumber = notificationService.queryNotifyNumber(user.getId(), NotificationStatusEnum.UNREAD.getStatus());
		model.addAttribute("notifyNumber",notifyNumber);
		return "Profile";
	}
}
