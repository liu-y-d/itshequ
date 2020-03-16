package com.lyd.itshequ.controller;

import com.lyd.itshequ.exception.MeErrorCode;
import com.lyd.itshequ.exception.MeExceptions;
import com.lyd.itshequ.mapper.AdminMapper;
import com.lyd.itshequ.mapper.UserMapper;
import com.lyd.itshequ.model.Admin;
import com.lyd.itshequ.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserMapper userMapper;
    @PostMapping("/adminLogin")
    public String adminLogin(Admin admin, HttpServletRequest request, Model model){
        Admin adminLogin = adminMapper.adminLogin(admin);
        if (adminLogin==null){
            throw new MeExceptions(MeErrorCode.ADMIN_LOGIN);
        }
        request.getSession().setAttribute("admin",adminLogin);
        List<User> allUser = adminMapper.getAllUser();
        model.addAttribute("users",allUser);
        return "AdminMain";
    }
    @GetMapping("/adminLoginout")
    public String logOut(HttpServletRequest request, HttpServletResponse response){
        request.getSession().removeAttribute("admin");
        return "redirect:/";
    }
    @GetMapping("/user/{id}")
    public String post(@PathVariable("id")Long id, Model model, HttpServletRequest request){
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin == null){
            throw new MeExceptions(MeErrorCode.NO_LOGIN);
        }
        User user = userMapper.findById(id);
        model.addAttribute("user",user);
        model.addAttribute("admin","1");
        return "UserInfo";
    }
    @ResponseBody
    @GetMapping("/blockUser/{userId}")
    public String blockUser(@PathVariable("userId")Long userId,HttpServletRequest request){
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin == null){
            throw new MeExceptions(MeErrorCode.NO_LOGIN);
        }
        User byId = userMapper.findById(userId);
        int i = 0;
        if (byId.getStatus()==1){
            i = userMapper.blockUser(userId, 0);
            if (i == 1){
                return "您以封停编号为："+userId+"的用户";
            }else {
                throw new MeExceptions(MeErrorCode.SYS_ERROR);
            }
        }else {
            i = userMapper.blockUser(userId, 1);
            if (i == 1){
                return "您以解封编号为："+userId+"的用户";
            }else {
                throw new MeExceptions(MeErrorCode.SYS_ERROR);
            }
        }

    }
    @PostMapping("/adminSearch")
    public String adminSearch(String name,Model model){
        List<User> users = userMapper.adminSearch(name);
        model.addAttribute("users",users);
        return "AdminMain";
    }
}
