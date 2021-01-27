package com.example.demo.dao.seg;

import com.example.demo.model.OrderItem;

public interface OrderItemMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderItem record);
}