package com.fh.shopping.service;

import com.alibaba.fastjson.JSONObject;
import com.fh.common.SystemPlate;
import com.fh.product.model.Product;
import com.fh.product.service.ProductService;
import com.fh.shopping.model.Shopping;
import com.fh.user.model.User;
import com.fh.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShoppingServiceImpl implements ShoppingService{

    @Autowired
    private ProductService proservice;

    @Override
    public Map gotoshoppingyz(Shopping shopping, HttpServletRequest request) {
        Map mm=new HashMap();
        //验证商品是否存在
        Product pro=proservice.querybyid(shopping.getSid());
        if(pro==null){
            mm.put("code",201);
            return mm;
        }
        //验证商品是否上架
        if(pro.getUpsj()==0){
            mm.put("code",202);
            return mm;
        }
        //验证商品是否在购物车中
        //从sessiong中取出用户信息
        User User = (com.fh.user.model.User) request.getSession().getAttribute(SystemPlate.SESSION_KEY);
        //判断购物车中是否有这个商品的id
        Boolean exist = RedisUtil.exist(SystemPlate.SHOPPING_KEY+User.getUid().toString(), pro.getProid().toString());
        //没有的话就把这个商品放进去
        if(!exist){
            //如果不存在这个商品就把这个商品田间进去
            Shopping s=new Shopping();
            s.setSid(pro.getProid());
            s.setScount(shopping.getScount());
            s.setSname(pro.getProname());
            s.setFilepath(pro.getFilepath());
            s.setSprice(pro.getProprice());
            //将shopping转为string类型放入redis中
           String shoppingstring = JSONObject.toJSONString(s);
            Long count = RedisUtil.hset(SystemPlate.SHOPPING_KEY + User.getUid().toString(), pro.getProid().toString(), shoppingstring);
            System.out.println("该用户有:"+count+"个物品");
        }else{
            //redis中有这个商品进行修改
            //获取到该商品的string类型
            String stringshopping = RedisUtil.hget(SystemPlate.SHOPPING_KEY + User.getUid().toString(), pro.getProid().toString());
            //转换为shopping对象
            Shopping shopping1 = JSONObject.parseObject(stringshopping, Shopping.class);
            shopping1.setScount(shopping1.getScount()+shopping.getScount());
            //转换为string类型放入redis中
            RedisUtil.hset(SystemPlate.SHOPPING_KEY + User.getUid().toString(),pro.getProid().toString(),JSONObject.toJSONString(shopping1));
        }

        mm.put("code",200);
        return mm;
    }

    @Override
    public Map querycount(HttpServletRequest request) {
        Map mm=new HashMap();

        User User = (com.fh.user.model.User) request.getSession().getAttribute(SystemPlate.SESSION_KEY);

        List<String> list= RedisUtil.hvals(SystemPlate.SHOPPING_KEY + User.getUid());
        if(list!=null && list.size()>0){
            int count=0;
            for (int i = 0; i < list.size(); i++) {
                Shopping shopping = JSONObject.parseObject(list.get(i), Shopping.class);
                count+=shopping.getScount();
            }
            mm.put("code",200);
            mm.put("data",count);
        }else{
            mm.put("code",200);
            mm.put("data",0);
        }
        return mm;
    }

    @Override
    public Map queryList(HttpServletRequest request) {
        Map mm=new HashMap();
        User user = (User) request.getSession().getAttribute(SystemPlate.SESSION_KEY);

        List<String> shoppingList = RedisUtil.hvals(SystemPlate.SHOPPING_KEY + user.getUid());

        List<Shopping> list=new ArrayList<>();
        if(shoppingList!=null && shoppingList.size()>0){

            for (int i = 0; i < shoppingList.size(); i++) {
                Shopping shopping = JSONObject.parseObject(shoppingList.get(i), Shopping.class);
                list.add(shopping);
            }
        }else{
            mm.put("code",201);
            return mm;
        }

        mm.put("code",200);
        mm.put("data",list);
        return mm;
    }

    @Override
    public Map deleteshopping(HttpServletRequest request, Integer sid) {
        Map mm=new HashMap();
        User User = (User) request.getSession().getAttribute(SystemPlate.SESSION_KEY);

        RedisUtil.hdel(SystemPlate.SHOPPING_KEY+User.getUid(),sid.toString());

        mm.put("code",200);
        return mm;
    }
}
