package com.example.demo.study.entity;

import org.springframework.util.Assert;

import java.util.NoSuchElementException;

/**
 * Created by dell on 2018/7/3.
 */
public class MyStack<T> {
    private  static  final int defaultSize = 10 ;
    private  int size  ;
    private  int top ;
    private  Object[] listArray ;

    //定义堆栈的空间大小

    public MyStack() {
        setup(defaultSize);
    }
    public MyStack(int sz){
        setup(sz);
    }
    //堆栈初始化
    public void setup(int sz){
        this.size = sz ;
        this.top = 0 ;
        this.listArray = new Object[sz] ;
    }
    public int height() {
        return size;
    }

    public Object pop() throws NoSuchElementException {
        Assert.isTrue(!isEmpty() , "empty stack");
        return listArray[-- top];
    }

    public void push(Object o) {
        Assert.isTrue(top < size , "stack overoverflow");
        listArray[top ++ ] = o ;
    }

    public Object top() throws NoSuchElementException {
        Assert.isTrue(!isEmpty() , "empty stack");
        return listArray[top - 1 ];
    }
    //清空堆栈
    public void clear(){
        top = 0 ;
    }
    //堆栈是否为空
    public boolean isEmpty(){
        return top == 0 ;
    }
}
