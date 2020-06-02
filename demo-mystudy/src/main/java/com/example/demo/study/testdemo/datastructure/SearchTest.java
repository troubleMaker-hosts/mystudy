package com.example.demo.study.testdemo.datastructure;

import com.example.demo.study.entity.BinarySearchTree;

import java.util.Arrays;

/**
 * @ClassName: SearchTest
 * @Description: 查找 测试类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2018/11/19 01:14
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class SearchTest {
    public static void main(String[] args) {
        // 16 个数字
        int[] ints = {12, 3, 43, 53, 5242, 123, 534, 1, 44, 241, 5, 8, 456, 89, 88, 203};
        //int[] ints = {2,1};
        //int[] ints = {1,2};
        System.out.println(Arrays.toString(ints));
        System.out.println(ints.length);
        int key = 5242;
        SortTest.mergeSort(ints, 0, ints.length - 1);
        System.out.println("排序后");
        System.out.println(Arrays.toString(ints));
        binarySearch(ints, 0, ints.length - 1, key);

        //二叉搜索树
        BinarySearchTree binarySearchTree = new BinarySearchTree(ints.length);
        for (int anInt : ints) {
            binarySearchTree.createBinarySearchTree(anInt);
        }
        binarySearchTree.binaryTreeSearch(key);
    }

    /**
     * 二分查找（迭代）
     * @param ints 目标数组
     * @param left  左游标
     * @param right 右游标
     * @param key   key
     */
    public static void binarySearch(int[] ints, int left, int right, int key) {
        int mid = (left + right) / 2;
        System.out.println("11111111111        " + left + "     " + right + "         " + mid);
        if (left <= right) {
            if (ints[mid] < key) {
                binarySearch(ints, mid + 1, right, key);
            } else if (ints[mid] > key) {

                binarySearch(ints, left, mid - 1, key);
            } else {
                System.out.println(mid + "::::::" + ints[mid]);
                //return;
            }
        } else {
            System.out.println("没有找到");
        }


    }
}
