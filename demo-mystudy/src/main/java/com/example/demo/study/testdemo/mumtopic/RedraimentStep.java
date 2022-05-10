package com.example.demo.study.testdemo.mumtopic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @ClassName: Redraiment的走法
 * @Description:    Redraiment是走梅花桩的高手。Redraiment总是起点不限，从前到后，往高的桩子走，但走的步数最多，不知道为什么？你能替Redraiment研究他最多走的步数吗？
 *                  样例输入
 *                  6
 *                  2 5 1 5 4 5
 *
 *                  样例输出
 *                  3
 *
 *                  提示
 *
 *                  Example:
 *                  6个点的高度各为 2 5 1 5 4 5
 *                  如从第1格开始走,最多为3步, 2 4 5
 *                  从第2格开始走,最多只有1步,5
 *                  而从第3格开始走最多有3步,1 4 5
 *                  从第5格开始走最多有2步,4 5
 *
 *                  所以这个结果是3。
 *
 *                  接口说明
 *
 *                  原型：
 *
 *                    int GetResult(int num, int[] pInput, List  pResult);
 *
 *                  参数：
 *                   int num：整数，表示数组元素的个数（保证有效）。
 *                   int[] pInput: 数组，存放输入的数字。
 *
 *                  参数：
 *                   List pResult: 保证传入一个空的List，要求把结果放入第一个位置。
 *                  值：
 *                  正确返回1，错误返回0
 *
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/04/19 02:59
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
public class RedraimentStep {
    public static void main(String[] args) throws IOException {
        //Scanner scanner = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        //while (scanner.hasNext()) {
        while ((line = br.readLine()) != null) {
            int len = Integer.parseInt(line);
            String[] data = br.readLine().split(" ");
            int[] pInput = new int[len];
            for (int i = 0; i < data.length; i++) {
                pInput[i] = Integer.parseInt(data[i]);
            }
            System.out.println(getResult(len, pInput));
        }
    }

    private static int getResult(int num, int[] pInput) {
        int step = 0;
        int[] brr = new int[num];
        // 转化成求最长递增子序列
        for (int i = 0; i < pInput.length; i++) {
            brr[i] = 1;
            for (int j = 0; j < i; j++) {
                if (pInput[j] < pInput[i]) {
                    brr[i] = Math.max(brr[i], brr[j] + 1);
                }
            }
        }

        for (int aBrr : brr) {
            System.out.println(aBrr);
            if (step < aBrr) {
                step = aBrr;
            }
        }
        return step;
    }
}
