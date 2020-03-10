package com.lyd.itshequ.controller;

import com.lyd.itshequ.bean.CommentDTO;
import com.lyd.itshequ.bean.PostDTO;
import com.lyd.itshequ.enums.CommentTypeEnum;
import com.lyd.itshequ.enums.NotificationStatusEnum;
import com.lyd.itshequ.mapper.CommentMapper;
import com.lyd.itshequ.mapper.PostMapper;
import com.lyd.itshequ.model.User;
import com.lyd.itshequ.service.CommentService;
import com.lyd.itshequ.service.NotificationService;
import com.lyd.itshequ.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
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
	@Autowired
	private NotificationService notificationService;
	@GetMapping("/post/{id}")
	public String post(@PathVariable("id")Long id, Model model, HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");

		PostDTO postById = postService.getPostById(id);
		List<PostDTO> relatePosts = postService.selectByRelated(postById);
		List<CommentDTO> commentDTOList = commentService.listByTargetId(id, CommentTypeEnum.POST);
		//累加阅读数
		PostDTO postDTO = postService.incView(id);
		model.addAttribute("comments",commentDTOList);
		model.addAttribute("postDTO",postDTO);
		model.addAttribute("relatedPost",relatePosts);
		Integer notifyNumber = notificationService.queryNotifyNumber(user.getId(), NotificationStatusEnum.UNREAD.getStatus());
		model.addAttribute("notifyNumber",notifyNumber);
		return "Post";
	}
}
