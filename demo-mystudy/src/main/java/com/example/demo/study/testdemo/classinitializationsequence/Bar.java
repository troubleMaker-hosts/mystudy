package com.example.demo.study.testdemo.classinitializationsequence;

/**
 * @ClassName: Bar
 * @Description: Bar 继承 Foo
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2018/11/19 01:14
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class Bar extends  Foo {
    private int j = 1;

    /**
     * 默认构造器
     */
    public Bar() {
        j = 2;
    }

    /**
     * 代码块
     */
    {
        j = 3;
    }

    /**
     * 重写 父类的 getValue() 方法
     * @return j
     */
    @Override
    protected int getValue() {
        return j;
    }
}
