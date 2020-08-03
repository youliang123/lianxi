package com.fh.order.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fh.address.model.AddRess;
import com.fh.common.SystemPlate;
import com.fh.order.mapper.OrderMapper;
import com.fh.order.model.Order;
import com.fh.order.model.OrderInfo;
import com.fh.product.model.Product;
import com.fh.product.service.ProductService;
import com.fh.shopping.model.Shopping;
import com.fh.user.model.User;
import com.fh.util.BigDecimalUtil;
import com.fh.util.IdUtil;
import com.fh.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.beans.Transient;
import java.math.BigDecimal;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService{

    @Resource
    private OrderMapper ordermapper;
    //商品接口
    @Autowired
    private ProductService proservice;

    @Autowired
    private OrderInfoService orderinfoservice;



    @Override
    @Transient
    public Map totalorder(String infolist, String addresslist, Integer paytype,HttpServletRequest request) {


        //订单中的商品集合
        List<Shopping> info=new ArrayList<>();
        if(infolist!=null){
            info=JSONObject.parseArray(infolist, Shopping.class);
        }else{
            //没有商品
        }
        //默认地址的集合
        List<AddRess> address=new ArrayList<>();
        if(addresslist!=null){
            address=JSONObject.parseArray(addresslist,AddRess.class);
        }else{
            //没有默认地址
        }

        Integer addressid=0;
        //取出默认地址的id
        for (int i = 0; i < address.size(); i++) {
            addressid=address.get(i).getId();
        }

        return totalcl(info,addressid,paytype,request);
    }

    @Transient
    public Map totalcl(List<Shopping> infolist,Integer addressid,Integer paytype,HttpServletRequest request){
        Map mm=new HashMap();

        String orderid = IdUtil.createId();
        //获取用户信息
        User user = (User) request.getSession().getAttribute(SystemPlate.SESSION_KEY);
        List<OrderInfo> orderinfolist=new ArrayList<>();

        BigDecimal countprice=new BigDecimal("0.00");
        //商品库存不够的名字
        List<String> NoStockName=new ArrayList<>();

        BigDecimal mul =null;

        for (int i = 0; i < infolist.size(); i++) {
            Product product = proservice.querybyid(infolist.get(i).getSid());
            if(product.getStock()<infolist.get(i).getScount()){
                //库存不足
                NoStockName.add(infolist.get(i).getSname());
            }
            //扣除该商品库存中的数量
            Long res=proservice.updatestock(infolist.get(i).getSid(),infolist.get(i).getScount());
            if(res==1){
                OrderInfo o=new OrderInfo();
                o.setCount(infolist.get(i).getScount());
                o.setFilepath(infolist.get(i).getFilepath());
                o.setPrice(infolist.get(i).getSprice());
                o.setOrderid(orderid);
                o.setId(infolist.get(i).getSid());
                o.setName(infolist.get(i).getSname());
                //计算订单总价钱
                 mul = BigDecimalUtil.mul(infolist.get(i).getSprice().toString(), infolist.get(i).getScount().toString());
                countprice=BigDecimalUtil.add(countprice,mul);

                //将购物车的商品清理
                //取出购物车的商品进行判断如果购物车里面的件数小于要购买的件数就从购物车中删除这个商品，如果不小那就相减然后放入购物车中
                String shopping = RedisUtil.hget(SystemPlate.SHOPPING_KEY + user.getUid(), infolist.get(i).getSid().toString());
                Shopping shopping1 = JSONObject.parseObject(shopping, Shopping.class);
                if(shopping1.getScount()-infolist.get(i).getScount()<=0){
                    RedisUtil.hdel(SystemPlate.SHOPPING_KEY+user.getUid(),infolist.get(i).getSid().toString());
                }else{
                    shopping1.setScount(shopping1.getScount()-infolist.get(i).getScount());
                    RedisUtil.hset(SystemPlate.SHOPPING_KEY+user.getUid(),infolist.get(i).getSid().toString(),JSONObject.toJSONString(shopping1));
                }

                //将对象生成订单详情
                orderinfolist.add(o);
            }else{
                //库存不足
                mm.put("code",201);
                mm.put("data",NoStockName);
               return mm;
            }

        }

        //生成订单
        if(orderinfolist!=null && orderinfolist.size()== infolist.size()){
            orderinfoservice.addorderinfo(orderinfolist);
            Order order=new Order();
            order.setCreateDate(new Date());
            order.setAddressid(addressid);
            order.setId(orderid);
            order.setOrderprice(countprice);
            order.setPaytype(paytype);
            order.setUserid(user.getUid());
            order.setStatus(SystemPlate.ORDER_STATUS_WAIT);
            ordermapper.addorder(order);
        }else{
            mm.put("code",201);
            mm.put("data",NoStockName);
            return mm;
        }



        mm.put("code",200);
        mm.put("price",mul);
        mm.put("shoppingddh",orderid);
        return mm;
    }



}

