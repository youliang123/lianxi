package com.fh.order.service;

import com.fh.order.mapper.OrderInfoMapper;
import com.fh.order.model.OrderInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.beans.Transient;
import java.util.List;

@Service

public class OrderInfoServiceImpl implements OrderInfoService{

    @Resource
    private OrderInfoMapper orderinfomapper;


    @Override
    @Transient
    public void addorderinfo(List<OrderInfo> orderInfo) {
        orderinfomapper.addorderinfo(orderInfo);
    }
}

