package com.fh.order.mapper;

import com.fh.order.model.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderInfoMapper {

    void addorderinfo(List<OrderInfo> orderInfo);
}
