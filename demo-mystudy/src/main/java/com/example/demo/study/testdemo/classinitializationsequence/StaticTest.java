package com.example.demo.study.testdemo.classinitializationsequence;

/**
 * @ClassName: 被 static 关键 修饰的 属性(变量) ,方法 测试类
 * @Description: StaticTest
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2018/11/19 01:14
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class StaticTest {
    /**
     *  测试方法
     * @param args  String[] args
     */
    public static void main(String[] args) {
        staticFunction();
    }

    private static StaticTest st = new StaticTest();

    /**
     * 静态代码块
     */
    static {
        System.out.println("1");
    }

    /**
     * 实例代码块
     */
    {
        System.out.println("2");
    }

    /**
     * 默认构造器
     */
    private StaticTest() {
        System.out.println("3");
        System.out.println("a=" + a + ",b=" + b);
    }

    /**
     * 静态 方法测试
     */
    private static void staticFunction() {
        System.out.println(b);
        System.out.println("4");
    }

    /**
     * 实例变量
     */
    private int a = 110;

    /**
     * 静态变量
     */
    private static int b = 112;
}
