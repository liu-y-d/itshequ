package com.lyd.itshequ.advice;

import com.lyd.itshequ.exception.MeExceptions;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class MeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handler(Model model,Throwable e){
        System.out.println("拦截了错误");
        if (e instanceof MeExceptions){
            model.addAttribute("message",e.getMessage());
        }else{
            model.addAttribute("message","系统出现错误");
        }
        return new ModelAndView("error");
    }
}
