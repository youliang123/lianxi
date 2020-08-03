package com.fh.order.controller;


import com.fh.common.Idempotent;
import com.fh.order.service.OrderService;
import com.fh.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderservice;

    @RequestMapping("totalorder")
    @Idempotent
    public Map totalorder(String infolist, String addresslist, Integer paytype,HttpServletRequest request){
    return orderservice.totalorder(infolist,addresslist,paytype,request);
    }

    //生成token返回前端进行幂等性
    @RequestMapping("tomintoken")
    public Map tomintoken(){
        Map mm=new HashMap();
        String token2 = UUID.randomUUID().toString();
        RedisUtil.set(token2,token2);
        RedisUtil.expire(token2,60*30);
        mm.put("code",200);
        mm.put("data",token2);
        return mm;
    }
}


