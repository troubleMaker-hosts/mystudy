package com.example.demo.study.testdemo.classinitializationsequence;

/**
 * @ClassName: Foo
 * @Description: Bar 的  父类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2018/11/19 01:14
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class Foo {
    /**
     * 属性
     */
    private int i = 1;

    /**
     * 默认构造器
     */
    public Foo() {
        System.out.println(i);
        int x = getValue();
        System.out.println(x);
    }

    /**
     * 代码块
     */
    {
        i = 2;
    }

    /**
     * 获取 属性 i 的值
     * @return i
     */
    protected int getValue() {
        System.out.println("这是 Foo 的 getValue 方法");
        return i;
    }
}
