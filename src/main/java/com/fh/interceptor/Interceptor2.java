package com.fh.interceptor;

import com.fh.common.Idempotent;
import com.fh.common.Ignore;
import com.fh.common.MyException;
import com.fh.util.RedisUtil;
import com.opensymphony.oscache.util.StringUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class Interceptor2  implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //处理客户端传过来的自定义头信息
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,"x-auth,mtoken,content-type");
        //处理客户端发过来的put,delete
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS,"PUT,POST,DELETE,GET");

        System.out.println("==========================拦截器2================================");

        HandlerMethod handlerMethod=(HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if(!method.isAnnotationPresent(Idempotent.class)){
            return true;
        }
        //接受前端是否发送token
        String token = request.getHeader("mintoken");
        //如果没发送就报异常
        if(StringUtil.isEmpty(token)){
            throw new MyException("没有token");
        }
        //如果redis中没有token那就证明这不是第一次点击
        Boolean exist = RedisUtil.exist(token);
        if(!exist){
            throw new MyException("token失效");
        }

        Long del = RedisUtil.del(token);
        if(del==0){
            throw new MyException("重复下单");
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
