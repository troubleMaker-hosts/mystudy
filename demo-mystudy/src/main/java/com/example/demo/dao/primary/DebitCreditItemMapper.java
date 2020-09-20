package com.example.demo.dao.primary;

import com.example.demo.model.DebitCreditItem;

public interface DebitCreditItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DebitCreditItem record);

    int insertSelective(DebitCreditItem record);

    DebitCreditItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DebitCreditItem record);

    int updateByPrimaryKey(DebitCreditItem record);
}