package com.lyd.itshequ.controller;

import com.lyd.itshequ.bean.AccessTokenDTO;
import com.lyd.itshequ.bean.GithubUser;
import com.lyd.itshequ.commponent.GithubProvider;
import com.lyd.itshequ.mapper.UserMapper;
import com.lyd.itshequ.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @ClassName AuthorizeController
 * @Description TODO
 * @Author Liuyunda
 * @Date 2020/2/21 18:45
 **/
@Controller
public class AuthorizeController {
	@Autowired
	private GithubProvider githubProvider;
	@Value("${github.client.id}")
	private String clientId;

	@Value("${github.client.secret}")
	private String clientSecret;

	@Value("${github.redirect.uri}")
	private String redirectUri;

	@Autowired
	private UserMapper userMapper;
	@GetMapping("/callback")
	public String callback(@RequestParam(name = "code")String code,
	                       @RequestParam(name = "state")String state,
	                       HttpServletRequest request){

		AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
		accessTokenDTO.setRedirect_uri(redirectUri);
		accessTokenDTO.setClient_id(clientId);
		accessTokenDTO.setCode(code);
		accessTokenDTO.setState(state);
		accessTokenDTO.setClient_secret(clientSecret);

		String accessToken = githubProvider.getAccessToken(accessTokenDTO);
		GithubUser githubUser = githubProvider.getUser(accessToken);
		if(githubUser != null){
			User user = new User();
			user.setToken(UUID.randomUUID().toString());
			user.setName(githubUser.getName());
			user.setAcountId(String.valueOf(githubUser.getId()));
			user.setGmtCreate(System.currentTimeMillis());
			user.setGmtModified(user.getGmtCreate());
			userMapper.insert(user);
			//登录成功 写入cookie和session
			request.getSession().setAttribute("user",githubUser);
			return "redirect:/";
		}else{
			//登录失败  重新登录
			return "redirect:/";
		}
	}

}
