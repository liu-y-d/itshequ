package com.lyd.itshequ.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName HelloController
 * @Description hello请求
 * @Author Liuyunda
 * @Date 2020/2/21 13:39
 **/
@Controller
public class HelloController {
	@GetMapping("/hello")
	public String hello(@RequestParam(name = "name")String name, Model model){
		model.addAttribute("name",name);
		return "hello";
	}
}
