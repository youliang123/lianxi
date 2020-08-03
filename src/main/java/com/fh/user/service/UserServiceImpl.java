package com.fh.user.service;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.fh.common.SystemPlate;
import com.fh.user.mapper.UserMapper;
import com.fh.user.model.User;
import com.fh.util.AliyunSmsUtils;
import com.fh.util.JwtTokenUtil;
import com.fh.util.RedisUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.fh.util.AliyunSmsUtils.sendSms;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper usermapper;


    @Override
    public Map queryaccount(String uaccount) {
        Map mm=new HashMap();
        User user=usermapper.queryaccount(uaccount);
        if(user==null){
            mm.put("code",200);
        }else if(user!=null){
            mm.put("code",201);
        }
        return mm;
    }

    @Override
    public Map queryphone(String uphone) {
        Map mm=new HashMap();
        User user=usermapper.queryphone(uphone);
        if(user==null){
            mm.put("code",200);
        }else if(user!=null){
            mm.put("code",201);
        }
        return mm;
    }

    @Override
    public Map fscode(String uphone) {
        Map mm=new HashMap();
        try {
            if(uphone=="" || uphone==null){
                mm.put("code",201);
                return mm;
            }
            //获取验证码
            AliyunSmsUtils.setNewcode();
            String code = Integer.toString(AliyunSmsUtils.getNewcode());
            //手机号作为key验证码作为value存入redis中
           RedisUtil.set(uphone,code);
            Date date1 = new Date();

            //设置失效时长
            RedisUtil.expire(uphone,SystemPlate.REDIS_SHIXIAO_UNTIME);
            //发短信
            SendSmsResponse response =sendSms(uphone,code);

            mm.put("code",200);
        } catch (ClientException e) {
            e.printStackTrace();
            mm.put("code",202);
        }
        return mm;
    }

    @Override
    public Map reguser(User user) {
        Map mm=new HashMap();
        if(RedisUtil.get(user.getUphone())==null || RedisUtil.get(user.getUphone())==""){
            mm.put("code",202);
            return mm;
        }
         String rediscode= RedisUtil.get(user.getUphone());
        String usercode=user.getCode();
        if(!usercode.equals(rediscode)){
            mm.put("code",201);
            return mm;
        }
        usermapper.reguser(user);
        mm.put("code",200);
        return mm;
    }

    @Override
    public Map logn(User user) {
        Map mm=new HashMap();
        //判断账号是否为空!
        if(user.getUaccount()==null || user.getUaccount()==""){
            mm.put("code",201);
            return mm;
        }
        //判断账号是否存在
        User u=usermapper.queryaccount(user.getUaccount());
        if(u==null){
            mm.put("code",201);
            return mm;
        }
        //判断密码是否正确
        if(!u.getUpassword().equals(user.getUpassword())){
            mm.put("code",201);
            return mm;
        }


        String jwtuser ="";
        String token ="";
        try {
            String stringuser = JSONObject.toJSONString(u);
            jwtuser = URLEncoder.encode(stringuser, "utf-8");
             token = JwtTokenUtil.sign(jwtuser);
             RedisUtil.set(token,token);
             RedisUtil.expire(token,SystemPlate.TOKEN_EXPIRE_TIME);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mm.put("data",token);
        mm.put("code",200);
        return mm;
    }

    @Override
    public Map joinlogin(HttpServletRequest request) {
        Map mm=new HashMap();
        User user = (User) request.getSession().getAttribute(SystemPlate.SESSION_KEY);
        if(user==null){
            mm.put("code",201);
            return mm;
        }
            mm.put("code",200);
        return mm;
    }

    @Override
    public Map out(HttpServletRequest request) {
        Map mm=new HashMap();
        String token = (String) request.getSession().getAttribute(SystemPlate.TOKEN_KEY);
        RedisUtil.del(token);
        request.getSession().removeAttribute(SystemPlate.SESSION_KEY);
        request.getSession().removeAttribute(SystemPlate.TOKEN_KEY);
        mm.put("code",200);
        return mm;
    }
}
