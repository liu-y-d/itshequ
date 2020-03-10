package com.lyd.itshequ.controller;

import com.lyd.itshequ.bean.NotificationDTO;
import com.lyd.itshequ.bean.PageDTO;
import com.lyd.itshequ.enums.NotificationEnum;
import com.lyd.itshequ.enums.NotificationStatusEnum;
import com.lyd.itshequ.model.User;
import com.lyd.itshequ.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @GetMapping("/notification/{id}")
    public String toMyPost(@PathVariable("id") Long id, HttpServletRequest request, Model model ) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("errormsg", "未获取到登录信息");
            return "errorPage";
        }
        NotificationDTO notificationDTO = notificationService.read(id,user);
        Integer notifyNumber = notificationService.queryNotifyNumber(user.getId(), NotificationStatusEnum.UNREAD.getStatus());
        model.addAttribute("notifyNumber",notifyNumber);
        if (NotificationEnum.REPLY_POST.getType() == notificationDTO.getType()||NotificationEnum.REPLY_COMMENT.getType() == notificationDTO.getType()){
            return "redirect:/post/"+notificationDTO.getOuterId();
        }else{
            return "redirect:/";
        }
    }
}
