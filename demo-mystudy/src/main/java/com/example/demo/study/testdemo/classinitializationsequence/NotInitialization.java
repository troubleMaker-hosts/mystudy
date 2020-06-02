package com.example.demo.study.testdemo.classinitializationsequence;

/**
 * @ClassName: NotInitialization
 * @Description: 不 初始化 测试类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2018/11/19 01:14
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class NotInitialization {
    public static void main(String[] args){
        System.out.println(SubClass.value);
        SsClass ss = new SsClass() ;
        System.out.println(SsClass.t2);
    }
}
