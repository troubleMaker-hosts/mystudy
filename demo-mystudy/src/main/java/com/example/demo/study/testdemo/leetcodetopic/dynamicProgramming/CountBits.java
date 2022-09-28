package com.example.demo.study.testdemo.leetcodetopic.dynamicProgramming;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @ClassName: CountBits
 * @Description:    338. 比特位计数
 * 给你一个整数 n ，对于0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：[0,1,1]
 * 解释：
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 示例 2：
 *
 * 输入：n = 5
 * 输出：[0,1,1,2,1,2]
 * 解释：
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 *
 * 提示：
 *
 * 0 <= n <= 105
 *
 * @Author: wrl
 * @version: 1.0.0
 * @Date: 2022/9/27 20:06
 */
public class CountBits {
    public static void main(String[] args) {
        System.out.println("输入 : ");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] bits = brianKernighan(n);
        System.out.println(Arrays.toString(Arrays.stream(bits).toArray()));
        //动态规划——最高有效位
        bits = dynamicProgrammingByHighestSignificanceBit(n);
        System.out.println(Arrays.toString(Arrays.stream(bits).toArray()));
        bits = dynamicProgrammingByLowestSignificanceBit(n);
        System.out.println(Arrays.toString(Arrays.stream(bits).toArray()));

    }

    /**
     * 动态规划——最低有效位  计算 0 -- n 每个数字的 bit 数
     * @param n n
     * @return  0 -- n 每个数字的 bit 数
     */
    private static int[] dynamicProgrammingByLowestSignificanceBit(int n) {
        //bits[x]=bits[x>>1]+(x & 1)
        int[] bits = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            bits[i] = bits[i >> 1] + (i & 1);
        }
        return bits;
    }

    /**
     * 动态规划——最高有效位  计算 0 -- n 每个数字的 bit 数
     * @param n n
     * @return  0 -- n 每个数字的 bit 数
     */
    private static int[] dynamicProgrammingByHighestSignificanceBit(int n) {
        int[] bits = new int[n + 1];
        bits[0] = 0;
        int highBit = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) {
                highBit = i;
            }
            //对于正整数 xx，如果可以知道最大的正整数 yy，使得 y y≤x 且 yy 是 22 的整数次幂，
            //则 yy 的二进制表示中只有最高位是 11，其余都是 00，此时称 yy 为 xx 的「最高有效位」。
            //令 z=x-y，显然 0≤z<x，则 bits[x]=bits[z]+1。
            bits[i] = bits[i - highBit] + 1;
        }
        return bits;
    }

    /**
     * brianKernighan 方法 计算 0 -- n 每个数字的 bit 数
     * @param n n
     * @return   0 -- n 每个数字的 bit 数
     */
    private static int[] brianKernighan(int n) {
        int[] bits = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            bits[i] = countBit(i);
        }
        return bits;
    }

    /**
     * 计算 i 的 二进制 为 1 的 bit 数
     * @param i i
     * @return  i 的 二进制 为 1 的 bit 数
     */
    private static int countBit(int i) {
        int countBitOne = 0;
        //BrianKernighan 算法的原理是：对于任意整数 xx，
        //令 x=x~\&~(x-1)x=x&(x−1)，该运算将 xx 的二进制表示的最后一个 11 变成 00。
        //因此，对 xx 重复该操作，直到 xx 变成 00，则操作次数即为 xx 的「一比特数」。
        while (i > 0) {
            i &= (i - 1);
            countBitOne++;
        }
        return countBitOne;
    }
}
