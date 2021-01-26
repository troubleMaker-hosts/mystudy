package com.example.demo.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Order {
    private Long id;

    private Integer userId;

    private BigDecimal total;

    private Short currency;

    private String payType;

    private Short payStatus;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    private Short status;
}