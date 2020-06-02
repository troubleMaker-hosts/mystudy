package com.example.demo.study.testdemo.datastructure;

/**
 * @ClassName: BinarySearch
 * @Description: 二分搜索(查找)  测试
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2018/11/19 01:14
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class BinarySearch {
    public  static void main(String[] args){
        int[] ints = {2,3,4,5,6,7,8,9} ;
        int k = 5 ;
        int high = ints.length;
        System.out.println(high+"    high");
        int low = 0 ;
        int mid  ;
        while (low <= high){
            mid = (high+low) / 2 ;
            System.out.println(mid);
            if(k == ints[mid]){
                System.out.println(ints[mid]+"       "+mid);
                break;
            }else if(k > ints[mid]){
                low = mid + 1 ;

            }else if(k < ints[mid]){
                high = mid - 1 ;
            }
        }
    }
}
