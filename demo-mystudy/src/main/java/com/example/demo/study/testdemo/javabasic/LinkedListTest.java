package com.example.demo.study.testdemo.javabasic;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName: LinkedListTest
 * @Description: LinkedListTest 链表测试类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2018/11/19 01:14
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class LinkedListTest {
    public static void  main(String[] args) {
        LinkedList linkedList = new LinkedList() ;
        linkedList.add(11) ;
        linkedList.add(22) ;
        System.out.println(linkedList.size());
        System.out.println(linkedList.get(0));
        Iterator iterable = linkedList.iterator() ;

        System.out.println("遍历");
        while (iterable.hasNext()) {
            Object next =  iterable.next();
            System.out.println(next);

        }

        iteratorTest();
    }

    /**
     * 迭代器 测试
     */
    private static void iteratorTest(){
        String[] strings = {"11111","222222","333333","4444"} ;
        List list = Arrays.asList(strings) ;
        System.out.println("list="+list);
        Iterator it = list.iterator() ;
        while (it.hasNext()) {
            String next =  it.next().toString();
            System.out.println(next);
        }

    }
}
