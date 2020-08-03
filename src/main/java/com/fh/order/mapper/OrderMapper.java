package com.fh.order.mapper;

import com.fh.order.model.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    void addorder(Order order);
}
