package com.example.demo.study.testdemo.javabasic;

/**
 * @ClassName: BitOperationTest
 * @Description: 位操作 测试
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2018/11/19 01:14
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class BitOperationTest {
    public static void main(String[] args) {
        int i = 23;
        int b = 20;
        i = i ^ b;
        b = i ^ b;
        i = b ^ i;
        System.out.println("互换位置");
        System.out.println(i);
        System.out.println(b);
        i = i + b;
        b = i - b;
        i = i - b;
        System.out.println("第二次互换位置");
        System.out.println(i);
        System.out.println(b);

        System.out.println("位运算");
        //二进制
        System.out.println("二进制 : 0b11<<2 : " + (0b11 << 2));
        //八进制
        System.out.println("八进制 : 011<<4 : " + (011 << 4));
        //16进制
        System.out.println("16进制 : 0xf<<3 : " + (0xf << 3));

        System.out.println("输出格式");
        //0000000007   按10位十六进制输出，向右靠齐，左边用 空格 补齐
        System.out.printf("%10x\n",7);
        //0000000015    按10位八进制输出，向右靠齐，左边用0补齐
        System.out.printf("%010o\n",13);
    }
}
