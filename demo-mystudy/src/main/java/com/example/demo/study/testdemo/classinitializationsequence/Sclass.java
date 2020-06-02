package com.example.demo.study.testdemo.classinitializationsequence;

/**
 * @ClassName: SClass extends SSClass
 * @Description: 中间(中级)父类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2018/11/19 01:14
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class Sclass extends SsClass {
    /**
     * 静态代码块
     */
    static{
        System.out.println("SClass init!");
    }

    /**
     * 静态变量 (以赋值)
     */
    public static int value = 123;

    /**
     * 默认构造器
     */
    public Sclass(){
        System.out.println("init SClass");
    }
}
