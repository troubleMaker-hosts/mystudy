package com.example.demo.study.testdemo.javabasic;

/**
 * @ClassName: FinallyTest
 * @Description:
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/04/29 01:48
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
public class FinallyTest {
    public static final String STR = "FINAL";

    public static void main(String[] args) {
        System.out.println(STR);
        //final修饰一个成员变量（属性），必须要显示初始化。
        //有两种初始化方式: 一种是在变量声明的时候初始化；
        //                第二种方法是在声明变量的时候不赋初值，但是要在这个变量所在的类的所有的构造函数中对这个变量赋初值。
        //当函数的参数类型声明为final时，说明该参数是只读型的。即你可以读取使用该参数，但是无法改变该参数的值。
        //STR = "UPDATE_FINAL";
        System.out.println(finallyTest());
    }

    /**
     * finally 关键字 测试
     * @return finally中 return 结果的值
     */
    private static String finallyTest() {
        String s = STR + 1;
        try {
            if (s.isBlank()) {
                throw new RuntimeException("s 在 try 中 return 之前的 值 : " + s);
            }
            return s + 2;
        } catch (Exception e) {
            return s + 3;
        } finally {
            //因为finally用法特殊，所以会撤销之前的return语句，继续执行最后的finally块中的代码。
            return s + 4;
        }
    }
}
