package com.example.demo.study.testdemo.leetcodetopic.dynamicProgramming;

import java.util.Scanner;

/**
 * @ClassName: PalindromicNumber
 * @Description: 找出一个字符串 的 最长 回文数 子串
 * @Author: wrl
 * @version: 1.0.0
 * @Date: 2022/5/9 10:48
 */
public class PalindromicNumber {
    public static void main(String[] args) {
        System.out.println("输入 str : ");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String result = "";
        int len = str.length();
        if (len < 2) {
            result = str;
            System.out.println(result);
            return;
        }
        char[] charArray = str.toCharArray();
        boolean[][] pnFlag = new boolean[len][len];
        int maxLen = 1;
        int index = 0;
        //初始化 所有 长度 为 1 的 子串  都是 回文数
        for (int i = 0; i < len; i ++) {
            pnFlag[i][i] = true;
        }
        //子字符串 长度
        for (int l = 2; l <= len; l ++) {
            //左边界
            for (int j = 0; j < len; j ++) {
                //由 l 和 j 可以确定右边界
                int r = l + j - 1;
                //超出 下标 break
                if (r >= len) {
                    break;
                }
                if (charArray[j] == charArray[r]) {
                    //r - 1 - (j + 1) > 1  => r -j > 3 才构成 区间
                    if (r -j < 3) {
                        pnFlag[j][r] = true;
                    } else {
                        pnFlag[j][r] = pnFlag[j + 1][r - 1];
                    }
                } else {
                    pnFlag[j][r] = false;
                }
                // 只要 dp[j][r] == true 成立，就表示子串 s[j..r] 是回文，此时记录回文长度和起始位置
                if (pnFlag[j][r] && r - j + 1 > maxLen) {
                    index = j;
                    maxLen = r - j + 1;
                }
            }
        }
        result = str.substring(index, maxLen + index);
        System.out.println(result);
    }
}
