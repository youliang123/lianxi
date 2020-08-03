package com.fh.product.controller;

import com.fh.common.Ignore;
import com.fh.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService proservice;

    @RequestMapping("queryProduct")
    @Ignore
    public Map queryProduct(){
        Map mm=proservice.queryProduct();
        return mm;
    }

    //查询商品无线下拉分页
    @RequestMapping("queryProductPage")
    @Ignore
    public Map queryProductPage(long currentPage,long pageSize){
        Map mm=proservice.queryProductPage(currentPage,pageSize);
        return mm;
    }


}
