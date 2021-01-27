package com.example.demo.dao.seg;

import com.example.demo.model.OrderForm;

import java.util.List;

public interface OrderFormMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(OrderForm record);

    OrderForm selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderForm record);

    Integer batchInsert(List<OrderForm> orderForms);
}