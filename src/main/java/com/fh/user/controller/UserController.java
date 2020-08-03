package com.fh.user.controller;


import com.fh.common.Ignore;
import com.fh.user.model.User;
import com.fh.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userservice;

    @RequestMapping("ck")
    public String ck(){
        return "成功!";
    }

    //注册时查看账号是否有重复的
    @RequestMapping("queryaccount")
    @Ignore
    public Map queryaccount(String uaccount){
        Map mm=userservice.queryaccount(uaccount);
        return mm;
    }

    //注册时查看手机号是否有重复的
    @RequestMapping("queryphone")
    @Ignore
    public Map queryphone(String uphone){
        Map mm=userservice.queryphone(uphone);
        return mm;
    }

    //注册时通过手机号发送验证码
    @RequestMapping("fscode")
    @Ignore
    public Map fscode(String uphone){
        Map mm=userservice.fscode(uphone);
        return mm;
    }

    //注册验证方法
    @RequestMapping("reguser")
    @Ignore
    public Map reguser(User user){
        Map mm=userservice.reguser(user);
        return mm;
    }

    //登录验证方法
    @RequestMapping("login")
    @Ignore
    public Map logn(User user){
        Map mm=userservice.logn(user);
        return mm;
    }

    //判断是否登录方法
    @RequestMapping("joinlogin")
    @Ignore
    public Map joinlogin(HttpServletRequest request){
        Map mm=userservice.joinlogin(request);
        return mm;
    }

    //注销登录方法
    @RequestMapping("out")
    public Map out(HttpServletRequest request){
        Map mm=userservice.out(request);
        return mm;
    }

}
