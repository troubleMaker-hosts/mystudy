package com.example.demo.study.testdemo.classinitializationsequence;

/**
 * @ClassName: SubClass extends Sclass
 * @Description: 子类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2018/11/19 01:14
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class SubClass extends Sclass {
    /**
     * 静态代码块
     */
    static{
        System.out.println("SubClass init");
    }

    /**
     * 静态 属性 (还没有 赋值)
     */
    private static int a;

    /**
     * 默认 构造器
     */
    public SubClass(){
        System.out.println("init SubClass");
    }
}
