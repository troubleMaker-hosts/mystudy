package com.example.demo.study.entity;

import java.util.Arrays;

/**
 * Created by dell on 2018/6/28.
 */
public class MyGenericity<T>  {
    private T data  ;

    public MyGenericity() {
    }

    public MyGenericity(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public  static void main(String[] args){
        String[] ints = {"sd","fsd","sdfsk" ,"erw"} ;
        Arrays.sort(ints);
        System.out.println(Arrays.toString(ints));
    }

}
