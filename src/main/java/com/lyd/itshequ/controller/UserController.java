package com.lyd.itshequ.controller;

import com.lyd.itshequ.exception.MeErrorCode;
import com.lyd.itshequ.exception.MeExceptions;
import com.lyd.itshequ.mapper.UserMapper;
import com.lyd.itshequ.model.User;
import com.lyd.itshequ.util.QiNiuUpload;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/registUser")
    public String toRegistUser(@RequestParam("name") String name, @RequestParam("password") String password, MultipartFile file) {
        System.out.println(name + password + file.getOriginalFilename());
        User user = new User();
        if (file!=null){
            try {
                String s = QiNiuUpload.updateFile(file, file.getOriginalFilename());
                user.setAvatarUrl(s);
            } catch (Exception e) {
                throw new MeExceptions(MeErrorCode.UPLOAD_ERROR);
            }
        }
        User queryByName = userMapper.queryByName(name);
        if (queryByName!=null){
            throw new MeExceptions(MeErrorCode.USERNAME_REPEAT);
        }
        user.setName(name);
        user.setPassword(password);
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtModified(System.currentTimeMillis());
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        userMapper.insertLocalUser(user);
        return "Login";
    }
    @PostMapping("/Login")
    public String Login(User user, Model model, HttpServletRequest request, HttpServletResponse response){
        if (StringUtils.isBlank(user.getName())||StringUtils.isBlank(user.getPassword())){
            throw new MeExceptions(MeErrorCode.NOT_NULL);
        }
        User login = userMapper.Login(user);
        if (login==null){
            model.addAttribute("errorMessage","用户名或密码错误！");
            return "Login";
        }
        request.getSession().setAttribute("user",login);
        response.addCookie(new Cookie("token",login.getToken()));
        return "redirect:/";
    }
}
