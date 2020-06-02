package com.example.demo.study.testdemo.classinitializationsequence;

/**
 * @ClassName: SsClass
 * @Description: 最高级的父类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2018/11/19 01:14
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class SsClass {
    /**
     * 公共 的 静态 final 变量
     */
    public static final String tt = "没初始化";

    /**
     * 静态 属性
     */
    public static String t2 = "hhehe";

    /**
     * 静态 代码块
     */
    static {
        System.out.println("SSClass");
    }

    /**
     * 默认构造器
     */
    public SsClass() {
        System.out.println("ssclass构造器");
    }
}
