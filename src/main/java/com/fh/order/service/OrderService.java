package com.fh.order.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface OrderService {
    Map totalorder(String infolist, String addresslist, Integer paytype, HttpServletRequest request);
}
