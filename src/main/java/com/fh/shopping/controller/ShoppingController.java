package com.fh.shopping.controller;
import com.fh.common.Ignore;
import com.fh.shopping.model.Shopping;
import com.fh.shopping.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequestMapping("shopping")
@RestController
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingservice;

    //判断是否可以添加到购物车
    @RequestMapping("gotoshoppingyz")
    public Map gotoshoppingyz(Shopping shopping, HttpServletRequest request){
        Map mm=shoppingservice.gotoshoppingyz(shopping,request);
        return mm;
    }
    //查询购物车商品总共有多少件
    @RequestMapping("querycount")
    public Map querycount(HttpServletRequest request){
        Map mm=shoppingservice.querycount(request);
        return mm;
    }

    //查询购物车商品
    @RequestMapping("queryList")
    public Map queryList(HttpServletRequest request){
        Map mm=shoppingservice.queryList(request);
        return mm;
    }

    //删除商品
    @RequestMapping("deleteshopping")
    public Map deleteshopping(HttpServletRequest request,@RequestParam("sid") Integer sid){
        Map mm=shoppingservice.deleteshopping(request,sid);
        return mm;
    }

}
