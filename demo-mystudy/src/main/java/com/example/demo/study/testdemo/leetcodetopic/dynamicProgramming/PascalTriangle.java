package com.example.demo.study.testdemo.leetcodetopic.dynamicProgramming;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Scanner;

/**
 * @ClassName: PascalTriangle
 * @Description:   杨辉三角
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 *
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 * 示例 1:
 *
 * 输入: numRows = 5
 * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * 示例2:
 *
 * 输入: numRows = 1
 * 输出: [[1]]
 *
 * @Author: wrl
 * @version: 1.0.0
 * @Date: 2022/9/26 16:35
 */
public class PascalTriangle {
    public static void main(String[] args) {
        System.out.println("输入 : ");
        Scanner scanner = new Scanner(System.in);
        int numRows = scanner.nextInt();
        List<List<Integer>> result = pascalTriangleGenerate(numRows);
        System.out.println(result);
    }

    /**
     * 生成 杨辉 三角 每行 的数组
     * @param numRows   行数
     * @return  杨辉 三角
     */
    private static List<List<Integer>> pascalTriangleGenerate(int numRows) {
        List<List<Integer>> result = Lists.newArrayList();
        for (int i = 0; i < numRows; i ++) {
            List<Integer> row = Lists.newArrayList();
            for (int j = 0; j <= i; j ++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
                }
            }
            result.add(row);
        }
        return result;
    }
}
