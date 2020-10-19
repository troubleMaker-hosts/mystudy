package com.example.demo.study.testdemo.javabasic;

import java.util.Stack;

/**
 * @ClassName: StackTest
 * @Description:    stack(栈) api test
 * @Author: willie
 * @version: 1.0.0
 * @Date: 2020/10/19 16:44
 * @Copyright: Copyright(c)2020 willie All Rights Reserved
 */
public class StackTest {
    public static void main(String[] args) {
        StackTest test = new StackTest();
        Stack<String> stringStack = test.initStack(20);
        //获取 stack 最上面一个元素
        System.out.println("获取 stack 最上面一个元素 : " + stringStack.peek());
        //添加 一个元素 给 stack
        System.out.println("添加 一个元素 给 stack : string_21");
        stringStack.push("string_21");
        //取出 stack 最上面一个 元素
        System.out.println("取出 stack 最上面一个 元素 : " + stringStack.pop());
        //获取 stack 最上面一个元素
        System.out.println("获取 stack 最上面一个元素 : " + stringStack.peek());
        System.out.println("获取 stack 最上面一个元素 : " + stringStack.peek());
        System.out.println("判断 stack 是否 为 empty : " + stringStack.empty());
        System.out.println("查找 该元素 在 stack 中 的位置(从stack 顶部(1) 开始) : stringStack_19 : " + stringStack.search("stringStack_19"));
        System.out.println("查找 该元素 在 stack 中 的位置(从stack 顶部(1) 开始) : stringStack_12 : " + stringStack.search("stringStack_12"));
        System.out.println("查找 该元素 在 stack 中 的位置(从stack 顶部(1) 开始) : string_21 : " + stringStack.search("string_21"));
    }

    /**
     * 初始化 stack
     * @param num   stack初始化大小
     * @return  Stack<String>
     */
    private Stack<String> initStack(int num) {
        Stack<String> stringStack = new Stack<>();
        for (int i = 0; i < num; i++) {
            stringStack.push("stringStack_" + i);
        }
        return stringStack;
    }
}
