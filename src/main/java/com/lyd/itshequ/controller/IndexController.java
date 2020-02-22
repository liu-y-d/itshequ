package com.lyd.itshequ.controller;

import com.lyd.itshequ.mapper.UserMapper;
import com.lyd.itshequ.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName IndexController
 * @Description TODO
 * @Author Liuyunda
 * @Date 2020/2/22 16:00
 **/
@Controller
public class IndexController {
	@Autowired
	private UserMapper userMapper;

	@GetMapping("/")
	public String index(HttpServletRequest request){
		// 获取Cookies
		Cookie[] cookies = request.getCookies();
		if (cookies.length!=0){
			// 如果Cookies不为空则寻找cookies中名为token的cookie
			for (Cookie cookie : cookies){
				if ("token".equals(cookie.getName())){
					String token = cookie.getValue();
					// 和数据库中的用户信息进行比较
					User user = userMapper.findByToken(token);
					if (user!=null){
						// 将用户添加进session
						request.getSession().setAttribute("user",user);
					}
					break;
				}
			}
		}
		System.out.println("cookie为空");
		return "index";
	}
}
