package com.example.demo.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderItem {
    private Long id;

    private Long orderId;

    private Integer commodityId;

    private Integer num;

    private BigDecimal unitPrice;

    private BigDecimal totalAmount;

    private String couponIds;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    private Short status;

}