package com.fh.order.model;

import java.math.BigDecimal;

public class OrderInfo {

    private String orderid;

    //商品id
    private Integer id;

    //商品价格
    private BigDecimal price;

    //商品名字
    private String name;

    //商品图片路径
    private String filepath;

    //商品件数
    private Integer count;

//========================


    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
