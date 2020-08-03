package com.fh.product.service;

import com.fh.product.model.Product;

import java.util.Map;

public interface ProductService {
    Map queryProduct();

    Map queryProductPage(long currentPage,long pageSize);

    Product querybyid(Integer sid);

    Long updatestock(Integer sid, Integer scount);
}
