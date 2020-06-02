package com.example.demo.study.entity;

import java.util.concurrent.Callable;

/**
 * @ClassName: ThreadCallableTest
 * @Description: 实现了 Callable 接口的 线程类 test
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/09/18 02:44
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class ThreadCallableTest implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("Callable 线程 sleep 5s 前");
        Thread.sleep(5 * 1000);
        System.out.println("实现了 Callable 接口的 线程类 test------- 进来了");
        return "ThreadCallableTest";
    }
}
