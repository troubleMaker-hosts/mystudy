package com.example.demo.study.testdemo.datastructure;

import com.example.demo.study.entity.MyStack;

/**
 * @ClassName: MyStackTest
 * @Description: 栈 测试类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2018/11/19 01:14
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class MyStackTest {
    public static  void main(String[] args){
        MyStack myStack = new MyStack(3) ;
        System.out.println(myStack.isEmpty());
        myStack.push("1111");
        myStack.push("22222");
        myStack.push("3333333");
        System.out.println(myStack.height());
        System.out.println(myStack);
        //System.out.println(myStack.pop());
        //System.out.println(myStack.pop());
        //System.out.println(myStack.pop());
        //System.out.println(myStack.height());
        System.out.println(myStack.top());
        System.out.println(myStack.top());
        System.out.println(myStack.top());
        System.out.println(myStack.height());
        int t = 3 ;
        int x = 2 ;
        //System.out.println(x++);
        //System.out.println(++t);
        //System.out.println(x);
        //System.out.println(t);
        //int y =  t ++
        //        + x ++
        //        + ++ x;
        int y =  t ++

                //+ ++ x
                + x ++
                ;
        System.out.println("x    "+x);
        System.out.println(y);
        System.out.println(t);
        y =  ++ x ;
        System.out.println(x);
        System.out.println(y);
        System.out.println("..............");
        t = 3 ;
        x = 2 ;
        y =+ t ;
        System.out.println(y);
        System.out.println(t);
        y += t ;
        System.out.println(y);
        y = x+=t ;

        System.out.println(x);
        System.out.println(y);


        System.out.println("+++++++++++++++++++");
        System.out.println(t%y);

        y = t = 1 ;
        System.out.println(y);
        System.out.println(t);
    }
}
