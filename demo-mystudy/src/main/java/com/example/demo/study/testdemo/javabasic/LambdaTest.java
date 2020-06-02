package com.example.demo.study.testdemo.javabasic;

/**
 * @ClassName: LambdaTest
 * @Description: LambdaTest lambda 测试类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2018/11/19 01:14
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class LambdaTest {
    public  static  void main(String[] args) {
        Thread oldSchool = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0 ; i < 10000 ; i ++){
                    if (i % 1000 == 0) {
                        System.out.println(11111);
                    }
                }
                System.out.println("This is from an anonymous class.");
            }
        });
        Thread newSchool = new Thread(()->{
            for (int i = 0 ; i < 10000 ; i ++){
                if (i % 1000 == 0) {
                    System.out.println(2222);
                }
            }
            System.out.println("Lambda 1111");
        });

        oldSchool.start();
        newSchool.start();
    }
}
