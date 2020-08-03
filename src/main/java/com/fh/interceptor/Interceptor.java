package com.fh.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.fh.common.Ignore;
import com.fh.common.LoginException;
import com.fh.common.SystemPlate;
import com.fh.user.model.User;
import com.fh.util.JwtTokenUtil;
import com.fh.util.RedisUtil;
import com.opensymphony.oscache.util.StringUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.net.URLDecoder;

public class Interceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //处理客户端传过来的自定义头信息
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,"x-auth,mtoken,content-type");
        //处理客户端发过来的put,delete
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS,"PUT,POST,DELETE,GET");

        System.out.println("==========================拦截器================================");

        HandlerMethod handlerMethod=(HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if(method.isAnnotationPresent(Ignore.class)){
            return true;
        }
        //判断前端发送的"x-auth"是否为空
        String token = request.getHeader("x-auth");
        if(StringUtil.isEmpty(token)){
            throw new LoginException();
        }
        //验证token是否失效
        Boolean exist = RedisUtil.exist(token);
        if(!exist){
            throw new LoginException();
        }
        boolean res = JwtTokenUtil.verify(token);
        if(res){
            String stringuser = JwtTokenUtil.getUser(token);
            String jsonuser = URLDecoder.decode(stringuser, "utf-8");
            User user = JSONObject.parseObject(jsonuser, User.class);
            request.getSession().setAttribute(SystemPlate.SESSION_KEY,user);
            request.getSession().setAttribute(SystemPlate.TOKEN_KEY,token);
        }else{
            throw new LoginException();
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
