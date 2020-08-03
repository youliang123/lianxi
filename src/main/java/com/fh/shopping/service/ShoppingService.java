package com.fh.shopping.service;

import com.fh.shopping.model.Shopping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface ShoppingService {
    Map gotoshoppingyz(Shopping shopping, HttpServletRequest request);

    Map querycount(HttpServletRequest request);

    Map queryList(HttpServletRequest request);

    Map deleteshopping(HttpServletRequest request, Integer sid);
}
