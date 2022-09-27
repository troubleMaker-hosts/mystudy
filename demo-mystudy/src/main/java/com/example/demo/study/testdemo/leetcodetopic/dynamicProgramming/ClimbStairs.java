package com.example.demo.study.testdemo.leetcodetopic.dynamicProgramming;

import java.util.Scanner;

/**
 * @ClassName: ClimeStairs
 * @Description: 70.爬楼梯
 * 假设你正在爬楼梯。需要 n阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 * 示例 2：
 *
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 *
 * 提示：
 *
 * 1 <= n <= 45
 * @Author: wrl
 * @version: 1.0.0
 * @Date: 2022/9/26 15:37
 */
public class ClimbStairs {
    public static void main(String[] args) {
        System.out.println("输入 : ");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int count = recursionCalculate(n);
        System.out.println(n + " 阶 楼梯走法数量 有 " + count);

        count = dynamicProgrammingCalculate(n);
        System.out.println(n + " 阶 楼梯走法数量 有 " + count);
    }

    /**
     * 动态规划  n 阶 楼梯 有 多少种 走法
     * @param n 楼梯 阶数
     * @return  走法 数量
     */
    private static int dynamicProgrammingCalculate(int n) {
        //n - 1  阶 走法
        int fn1 = 0;
        //n - 2  阶 走法
        int fn2 = 0;
        //n  阶 走法
        int fn = 1;
        for (int i = 1; i <= n; i ++) {
            fn2 = fn1;
            fn1 = fn;
            //公式
            fn = fn1 + fn2;
        }
        return fn;

    }

    /**
     * 递归计算  n 阶 楼梯 有 多少种 走法
     * @param n 楼梯 阶数
     * @return  走法 数量
     */
    private static int recursionCalculate(int n) {
        //1 阶 楼梯 有 1 种 走法
        if (n == 1) {
            return 1;
        }
        //2 阶 楼梯 有 2 种 走法
        if (n == 2) {
            return 2;
        }
        //公式
        return recursionCalculate(n - 1) + recursionCalculate(n - 2);
    }
}
