package com.fh.product.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Product {

    private Integer proid; //商品id

    private String proname;//商品名称

    private BigDecimal proprice;//商品价格

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date prodate;//生产日期

    private String filepath;//图片

    private Integer bid;//品牌id

    private Integer upsj;//是否上架

    private Integer name1;

    private Integer name2;

    private Integer name3;

    private Integer iscake;

    private Long stock;

    //============================

    public Integer getProid() {
        return proid;
    }

    public void setProid(Integer proid) {
        this.proid = proid;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public BigDecimal getProprice() {
        return proprice;
    }

    public void setProprice(BigDecimal proprice) {
        this.proprice = proprice;
    }

    public Date getProdate() {
        return prodate;
    }

    public void setProdate(Date prodate) {
        this.prodate = prodate;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getUpsj() {
        return upsj;
    }

    public void setUpsj(Integer upsj) {
        this.upsj = upsj;
    }

    public Integer getName1() {
        return name1;
    }

    public void setName1(Integer name1) {
        this.name1 = name1;
    }

    public Integer getName2() {
        return name2;
    }

    public void setName2(Integer name2) {
        this.name2 = name2;
    }

    public Integer getName3() {
        return name3;
    }

    public void setName3(Integer name3) {
        this.name3 = name3;
    }

    public Integer getIscake() {
        return iscake;
    }

    public void setIscake(Integer iscake) {
        this.iscake = iscake;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }
}
