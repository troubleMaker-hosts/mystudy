package com.example.demo.study.testdemo.javabasic;

/**
 * @ClassName: BasicsTest
 * @Description: java基础 测试类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2018/11/19 01:14
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class BasicsTest {
    public static void main(String[] args) {
        BasicsTest test = new BasicsTest();
        //测试 基本类型取值 范围
        test.rangeOfValuesForBasicTypes();
        //测试 i ++ 和 ++ i
        test.autofrettageAdd(10);

        //integer 和 String 的 == 和 equals 的 比較
        test.integerAndStringEqualsCompare();

        //测试 小数(float 和 double) 计算 时 精度缺失 问题
        test.precisionDeficiency(1.1, 20);

        //测试 当 两个数 相等 时  返回 什么
        //max 实现 : return (a >= b) ? a : b;
        System.out.println("Math.max(10, 10) :　" + Math.max(10, 10));
        System.out.println("Math.min(10, 10) :　" + Math.min(10, 10));
        //测试 Math 的 round,floor,ceil 方法
        System.out.println("Math.round(3.4) : " + Math.round(3.4));
        System.out.println("Math.round(3.5) : " + Math.round(3.5));
        System.out.println("Math.floor(3.4) : " + Math.floor(3.4));
        System.out.println("Math.floor(3.5) : " + Math.floor(3.5));
        System.out.println("Math.ceil(3.4)) : " + Math.ceil(3.4));
        System.out.println("Math.ceil(3.5)) : " + Math.ceil(3.5));
        double num = 1.0;
        Integer intNum = 1;
        System.out.println(num == intNum);
        //小数 在 存储的 时候 精度是能保证的,
        // 但是 在计算的 时候, 精度会有 偏差
        double num2 = 1.3;
        double num3 = -1.4;
        double num4 = num2 + num3;
        System.out.println("double num2 = " + num2 + "; : " + num2);
        System.out.println("double num3 = " + num3 + "; : " + num3);
        System.out.println("double num4 = num2 + num3; : " + num4);
        //java 强转 时 会丢失精度
        //原因: 不同类型长度 不同, 强转时 相当于 截取 一部分数据
        System.out.println("(int)num2 : (int)" + num2 + " : " + (int) num2);
        System.out.println("(int)num3 : (int)" + num3 + " : " + (int) num3);

        //字符传 转 double, 和 equals 方法 的使用
        String numStr = "0.0000";
        System.out.println("Double.valueOf(" + numStr + ") : " + (Double.valueOf(numStr)));
        System.out.println("Double.valueOf(" + numStr + ") == 0) : " + (Double.valueOf(numStr) == 0));
        System.out.println("Double.valueOf(" + numStr + ").equals(0) : " + (Double.valueOf(numStr).equals(0)));
        System.out.println("Double.valueOf(" + numStr + ").equals(0.0) : " + (Double.valueOf(numStr).equals(0.0)));
    }

    /**
     * 测试 小数(float 和 double) 计算 时 精度缺失 问题
     *
     * @param num   被计算number
     * @param count 计算次数
     */
    public void precisionDeficiency(double num, int count) {
        for (int i = 0; i < count; i++) {
            num -= 0.1;
            System.out.println("num += 0.1 : 第" + (i + 1) + " 次: " + num);
        }
    }

    /**
     * integer 和 String 的 == 和 equals 的 比較
     */
    public void integerAndStringEqualsCompare() {
        //String 類型 的  == 和 equals 的 區別
        String s = new String("ss");
        String ss = "ss";
        System.out.println("s.equals(ss) : " + s.equals(ss));
        System.out.println("s==ss : " + (s == ss));

        //比較 Integer 和 int 的 ==  ; Integer 和 Integer 的 == 和 equals
        Integer n = null;
        Integer i = 11;
        Integer j = Integer.valueOf(11);
        int k = 11;
        System.out.println(i.getClass());
        System.out.println("n == i : " + (n == i));
        System.out.println("i== k : " + (i == k));
        System.out.println("i== j : " + (i == j));
        System.out.println("i.equals(j) : " + (i.equals(j)));
    }

    /**
     * 测试  基本类型取值 范围
     */
    public void rangeOfValuesForBasicTypes() {
        int i = 70000;
        char c = 'a';
        System.out.println((int) c);
        c = (char) i;
        System.out.println((int) c);
        i = c - 32;

        System.out.println(i);
        System.out.println("g");
        int x = 10, y = 3;
        //System.out.println(x %= y + 2);
        //System.out.println(y+++x);
        System.out.println(y + ++x);
        int a = 12;
        a += a -= a * a;
        System.out.println(a);
        System.out.println();
    }

    /**
     * i ++ 和 ++ i 测试
     *
     * @param num 被计算number
     */
    public void autofrettageAdd(Integer num) {
        System.out.println("当前值 : " + num);
        System.out.println("num ++ : " + (num++));
        System.out.println("当前值 : " + num);
        System.out.println("++ num : " + (++num));
        System.out.println("当前值 : " + num);
    }
}
