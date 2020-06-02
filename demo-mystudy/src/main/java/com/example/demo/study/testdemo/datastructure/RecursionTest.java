package com.example.demo.study.testdemo.datastructure;

/**
 * @ClassName: RecursionTest
 * @Description: 递归 测试
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2018/11/19 01:14
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class RecursionTest {
    public static void main(String[] args) {
        int n = 4 ;
        System.out.println(recursionMethod(n));
        System.out.println(n);
        System.out.println(ageRecursion(5));
    }

    /**
     * 累乘 : 递归 方法 实现
     * @param n  从 1 乘 到 n
     * @return 累乘 结果
     */
    private static int recursionMethod(int n){
        if(n <= 1){
            return  1 ;
        }else {
            return  n * recursionMethod(n-1) ;
        }
    }

    /**
     * 递归
     * @param n 加 次数
     * @return 结果
     */
    private static int ageRecursion(int n ){
        if(n == 1){
            return  10  ;
        }else {
            return ageRecursion(-- n) + 2 ;
        }

    }
}
