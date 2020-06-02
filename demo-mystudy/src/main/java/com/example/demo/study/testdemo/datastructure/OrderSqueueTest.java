package com.example.demo.study.testdemo.datastructure;

import com.example.demo.study.entity.OrderSqueue;

/**
 * @ClassName: OrderSqueueTest
 * @Description: 队列 测试类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2018/11/19 01:14
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class OrderSqueueTest {
    public static void main(String[] args) {
        OrderSqueue orderSqueue = new OrderSqueue() ;
        orderSqueue.enterQueue("121212");
        orderSqueue.enterQueue("2222");
        orderSqueue.enterQueue("33333");
        orderSqueue.enterQueue("44444");
        orderSqueue.enterQueue(2020);
        orderSqueue.enterQueue(920);
        orderSqueue.enterQueue(1020);
        orderSqueue.enterQueue(1120);
        orderSqueue.enterQueue(1220);
        orderSqueue.enterQueue(1420);
        System.out.println(orderSqueue.toString());
        System.out.println(orderSqueue.firstValue());
        System.out.println(orderSqueue.firstValue());
        System.out.println(orderSqueue.deleteQueue() );
        System.out.println(orderSqueue.deleteQueue() );
        System.out.println(orderSqueue.deleteQueue() );
        orderSqueue.enterQueue(1420);
        System.out.println(orderSqueue.firstValue());
        System.out.println(orderSqueue.isEmpty());
    }
}
