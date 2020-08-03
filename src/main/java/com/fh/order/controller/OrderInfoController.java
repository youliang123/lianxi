package com.fh.order.controller;

import com.fh.order.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("orderinfo")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderinfoservice;

}
