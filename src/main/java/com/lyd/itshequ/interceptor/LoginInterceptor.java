package com.lyd.itshequ.interceptor;
import com.lyd.itshequ.mapper.UserMapper;
import com.lyd.itshequ.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName MeInterceptor
 * @Description TODO
 * @Author Liuyunda
 * @Date 2020/2/23 21:53
 **/
@Component
public class LoginInterceptor implements HandlerInterceptor {
	@Autowired
	private UserMapper userMapper;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 获取Cookies
		Cookie[] cookies = new Cookie[]{};
		cookies = request.getCookies();
		if (request.getCookies()!= null) {
			// 如果Cookies不为空则寻找cookies中名为token的cookie
			for (Cookie cookie : cookies) {
				if ("token".equals(cookie.getName())) {
					String token = cookie.getValue();
					// 和数据库中的用户信息进行比较
					User user = userMapper.findByToken(token);
					if (user != null) {
						// 将用户添加进session
						request.getSession().setAttribute("user", user);
					}else {
						// 未登录，返回登录界面
						// request.setAttribute("msg","没有权限，请先登录");
						request.getRequestDispatcher("/").forward(request,response);
						System.out.println("没有权限，请先登录");
						return false;
					}
					break;
				}
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}
}
