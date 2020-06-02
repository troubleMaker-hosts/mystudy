package com.example.demo.study.testdemo.javabasic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: ArrayTest
 * @Description: ArrayTest 数组 Array 测试类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2018/11/19 01:14
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class ArrayTest {
    public static void main(String[] args) {
        Integer[] ints = {23, 432, 4, 42, 46, 6, 56, 7, 88, 90, 67};
        System.out.println(Arrays.toString(ints));
        System.out.println(ints.length);
        System.out.println(ints);
        int i =  8;
        System.out.println(i++ + "  " + i++ + "  " + i++);
        System.out.println(i);

        //两数相加
        System.out.println("两数相加");
        System.out.println(Arrays.toString(twoSum(ints , 46)));

        //Arrays.asList 和 list 测试
        ArrayTest test = new ArrayTest();
        test.arraysAsList(ints);

        //测试两个有序数组 中 交集 的 情况
        Integer[] sectionOne = {2, 7, 10, 11, 13, 19};
        Integer[] sectionTwo = {5, 7, 8, 10, 15, 18};
        List<Integer> list = test.sectionIntersection(sectionOne, sectionTwo);
        for (int j = 0; j < list.size(); j++) {
            System.out.println("交集为 : " + list.get(j) + " --- " + list.get(++j));

        }
    }

    /**
     *
     * Arrays.asList 测试,   list.addAll(Arrays.asList(ints)); 可能会出现异常信息:
     *
     * 【强制】使用工具类Arrays.asList()把数组转换成集合时，不能使用其修改集合相关的方法，
     * 它的add/remove/clear方法会抛出UnsupportedOperationException异常。
     * 说明：asList的返回对象是一个Arrays内部类，并没有实现集合的修改方法。
     * Arrays.asList体现的是适配器模式，只是转换接口，后台的数据仍是数组。
     * 列子 :
     * String[] str = new String[] { "you", "wu" };
     * List list = Arrays.asList(str);
     * 第一种情况：list.add("yangguanbao"); 运行时异常。
     * 第二种情况：str[0] = "gujin"; 那么list.get(0)也会随之修改。
     * st<Integer> list = new ArrayList<>();
     * @param ints  测试数组
     */
    private void arraysAsList(Integer[] ints) {
        //注意此方法的注释
        List<Integer> list = new ArrayList<>();
        //将 Arrays.asList(ints) 添加(addAll) 到 list 中 之后, list 不会 随着 ints 的改变而改变 (上面 注释中的 问题 不再存在(解决))
        list.addAll(Arrays.asList(ints));
        list.forEach(System.out::println);
        list.add(0);
        System.out.println("list.add(0) 之后 : ");
        list.forEach(System.out::println);
        ints[0] = -1;
        System.out.println("ints[0] = -1 之后 : ");
        list.forEach(System.out::println);
        for (Integer anInt : ints) {
            System.out.println("ints 数组 :" + anInt);
        }
    }


    /**
     * 两数相加 : 找出数组中 两数 相加为 target 的 数字
     * @param nums  int数组
     * @param target    两数相加的和
     * @return  数组中 两书 相加为 target 的 数组
     */
    public static Integer[] twoSum(Integer[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int minus = target - nums[i] ;
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[j] == minus) {
                    Integer[] ii = new Integer[2];
                    ii[0] = i;
                    ii[1] = j;
                    return ii;
                }
            }
        }
        return  nums;
    }

    /**
     * 求 两个 区间(有序) 的 交集
     * @param sectionOne    有序区间数组一 (每两个看作是一个区间, 一个数组 多个区间)
     * @param sectionTwo    有序区间数组二 (每两个看作是一个区间, 一个数组 多个区间)
     */
    private List<Integer> sectionIntersection(Integer[] sectionOne, Integer[] sectionTwo) {
        //返回 交集 的 集合
        List<Integer> result = new ArrayList<>();
        int startPoint = 0;
        int endPoint = 0;
        boolean flag = false;
        for (int i = 0, j = 0; i < sectionOne.length && j < sectionTwo.length; ) {
            endPoint = startPoint;
            //判断是否 同时 到达 右边界
            if ((i % 2 == 1) && (j % 2 == 1)) {
                flag = true;
            } else {
                flag = false;
            }

            if (sectionOne[i] <= sectionTwo[j]) {
                startPoint = sectionOne[i ++];
            } else {
                startPoint = sectionTwo[j ++];
            }
            if (flag && ((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0))) {
                result.add(endPoint);
                result.add(startPoint);
            }
        }
        return result;
    }
}
