package com.example.demo.study.testdemo.javabasic;

import java.util.*;

/**
 * @ClassName: ListTest
 * @Description: List 测试类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2018/11/19 01:14
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class ListTest {
    public  static  void main(String[] args){
        ListTest test = new ListTest();
        List<Integer> arrayList = test.initArrayList();
        System.out.println("list remove 之前");
        for (int i = 0; i < arrayList.size(); i++) {
            Integer integer = arrayList.get(i);
            System.out.println(i + " : " + integer);
        }
        arrayList.remove(0);
        arrayList.removeIf(integer -> integer > 30);
        System.out.println("list remove 之后");
        for (int i = 0; i < arrayList.size(); i++) {
            Integer integer =  arrayList.get(i);
            System.out.println(i + " : " + integer);
        }
        System.out.println(arrayList.get(0));

        //list 的 set 方法 测试
        test.listSetMethodTest(arrayList);
        //list 的 toArray 方法测试
        test.listToArrayMethodTest(arrayList);
        //list 排序测试
        test.listSortTest(arrayList);
        test.subListMethodTest(arrayList, 2, 4);


    }

    /**
     * list subString 方法测试
     * @param list  目标list
     * @param beginIndex    开始的下标
     * @param endIndex  结束下标
     */
    public void subListMethodTest(List<Integer> list, int beginIndex, int endIndex) {
        for (int i = 0; i < list.size(); i++) {
            Integer integer = list.get(i);
            System.out.println("subString 之前 : " + " i : " + integer);
        }
        if (beginIndex > list.size()) {
            beginIndex = list.size();
        }
        if (endIndex > list.size()) {
            endIndex = list.size();
        }
        System.out.println("遍历 截取 得到的 subLIst : ---------");
        List<Integer> subLIst = list.subList(beginIndex, endIndex);
        for (int i = 0; i < subLIst.size(); i++) {
            Integer integer = subLIst.get(i);
            System.out.println("subString 之后, 截取范围 : subLIst : " + beginIndex + "--" + endIndex + " : " + i + " : " + integer);
        }
        System.out.println("遍历 被 截取 之后的 list : ---------");
        for (int i = 0; i < list.size(); i++) {
            Integer integer = list.get(i);
            System.out.println("subString 之后, 截取范围 : list : " + beginIndex + "--" + endIndex + " : " + i + " : " + integer);
        }
    }

    /**
     * list 排序测试
     * @param arrayList 被排序的list
     */
    public void listSortTest(List<Integer> arrayList) {
        //未排序之前
        for (int i = 0; i < arrayList.size(); i++) {
            Integer integer = arrayList.get(i);
            System.out.println("未排序之前 : " + i + " : " + integer);
        }
        //Collections.sort(arrayList)  默认 为 升序
        Collections.sort(arrayList);
        for (int i = 0; i < arrayList.size(); i++) {
            Integer integer = arrayList.get(i);
            System.out.println("Collections.sort(arrayList) : 排序之后 : " + i + " : " + integer);
        }

        //arrayList.sort() 重写 compare 降序(根据自己需求)
        arrayList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        for (int i = 0; i < arrayList.size(); i++) {
            Integer integer = arrayList.get(i);
            System.out.println("arrayList.sort() 重写 compare 降序(根据自己需求) :  排序之后 : " + i + " : " + integer);
        }
    }

    /**
     * list 的 toArray 方法测试
     * @param arrayList 测试的list
     */
    public void listToArrayMethodTest(List<Integer> arrayList) {
        Integer[] ii =  new Integer[arrayList.size()];
        ii = arrayList.toArray(ii);
        for (Integer integer : ii) {
            System.out.println(integer);
        }
        System.out.println(arrayList.toArray());
        System.out.println(arrayList.get(2).getClass());
    }

    /**
     * list 的 set 方法 测试
     * @param arrayList 执行set方法的list
     */
    public void listSetMethodTest(List<Integer> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            Integer integer = arrayList.get(i);
            System.out.println("arrayList.set(1,32) 之前 : " + i + " : " + integer);
        }
        //arrayList 的 add() 和 set() 方法 区别 :
        //1. add(index, element) 方法 : 如果 index 已经 存在 会 使 index 之后的 往后 位移 一位
        //2. set(index, element) 方法 : 如果 index 已经 存在 只会使 index 当前值 改变
        arrayList.set(1,32);
        for (int i = 0; i < arrayList.size(); i++) {
            Integer integer = arrayList.get(i);
            System.out.println("arrayList.set(1,32) 之后 : " + i + " : " + integer);
        }
    }

    /**
     * ArrayList 初始化  包含 重复值 , 且 初始化的 时候 是乱序的
     * @return  初始化之后的 ArrayList
     */
    public ArrayList<Integer> initArrayList() {
        ArrayList<Integer> arrayList = new ArrayList() ;
        arrayList.add(0, 2);
        arrayList.add(1, 23);
        arrayList.add(2, 34);
        arrayList.add(3, 24);
        arrayList.add(4, 24);
        arrayList.add(1, 68);
        return arrayList;
    }
}
