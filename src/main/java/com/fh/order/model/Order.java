package com.fh.order.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

    //订单号
    private String id;

    //用户id
    private Long userid;

    //默认使用的地址id
    private Integer addressid;

    //1，货到付款2，在线支付
    private Integer paytype;

    //是否支付 0，未支付 1，已支付
    private Integer status;

    //订单价格
    private BigDecimal orderprice;

    //订单创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    //订单信息集合
    private List<OrderInfo> infolist=new ArrayList<>();

    //==============


    public Order(String id, Long userid, Integer addressid, Integer paytype, Integer status, BigDecimal orderpricce, Date createDate, List<OrderInfo> infolist) {
        this.id = id;
        this.userid = userid;
        this.addressid = addressid;
        this.paytype = paytype;
        this.status = status;
        this.orderprice = orderprice;
        this.createDate = createDate;
        this.infolist = infolist;
    }

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Integer getAddressid() {
        return addressid;
    }

    public void setAddressid(Integer addressid) {
        this.addressid = addressid;
    }

    public Integer getPaytype() {
        return paytype;
    }

    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(BigDecimal orderprice) {
        this.orderprice = orderprice;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<OrderInfo> getInfolist() {
        return infolist;
    }

    public void setInfolist(List<OrderInfo> infolist) {
        this.infolist = infolist;
    }
}
