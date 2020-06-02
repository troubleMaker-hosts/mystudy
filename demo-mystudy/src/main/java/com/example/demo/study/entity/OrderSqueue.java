package com.example.demo.study.entity;


import org.springframework.util.Assert;

/**
 * Created by dell on 2018/7/4.
 */
public class OrderSqueue {
    private static  final int dafaultSize = 10 ;
    private int size ;
    private int front ;
    private int rear ;
    private Object[] listArray ;

    public OrderSqueue(){
        setup(dafaultSize);
    }

    public  OrderSqueue(int sz){
        setup(sz);
    }

    private void setup(int sz){
        size = sz + 1 ;
        front = rear = 0 ;
        listArray = new Object[sz +1] ;
    }

    //清空列队
    public  void clear(){
        front = rear = 0 ;
    }

    //入队
    public void  enterQueue(Object o){
        Assert.isTrue((rear + 1 )%size != front,"queue full");
        rear = (rear + 1) % size ;
        listArray[rear] = o ;

    }

    //出队
    public Object deleteQueue(){
        Assert.isTrue(!isEmpty() , "queue is empty");
        front = (front + 1) % size ;
        return  listArray[front] ;
    }

    //获得队首元素
    public  Object firstValue(){
        Assert.isTrue(!isEmpty() , "queue is empty");
        return  listArray[(front + 1) % size] ;
    }

    //是否为空
    public  boolean isEmpty(){
        return  front == rear ;
    }

    //是否 满 列队
    public  boolean isFull(){
        return  (rear + 1 ) % size == front ;
    }
}
