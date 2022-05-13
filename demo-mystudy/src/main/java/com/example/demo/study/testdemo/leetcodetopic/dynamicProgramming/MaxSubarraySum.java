package com.example.demo.study.testdemo.leetcodetopic.dynamicProgramming;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @ClassName: MaxSubarraySum
 * @Description:    53. 最大子数组和
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分。
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * @Author: wrl
 * @version: 1.0.0
 * @Date: 2022/5/13 14:58
 */
public class MaxSubarraySum {
    public static void main(String[] args) {
        System.out.println("输入 : ");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        List<Integer> nums = Arrays.stream(str.split(",")).map(Integer::valueOf).collect(Collectors.toList());
        Integer maxSubarraySum = getMaxSubarraySum(nums);
        System.out.println(maxSubarraySum);
    }

    /**
     *  给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * @param nums  nums
     * @return  maxSubarraySum
     */
    private static Integer getMaxSubarraySum(List<Integer> nums) {
        if (CollectionUtils.isEmpty(nums)) {
            return 0;
        }
        Integer maxSubarraySum = nums.get(0);
        int currentSum = 0;
        for (Integer num : nums) {
            currentSum =  Math.max(currentSum + num, num);
            maxSubarraySum = Math.max(maxSubarraySum, currentSum);
        }
        return maxSubarraySum;
    }
}
