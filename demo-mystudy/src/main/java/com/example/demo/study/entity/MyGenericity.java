package com.example.demo.study.entity;

import java.util.Arrays;

/**
 * @ClassName: MyGenericity
 * @Description: 泛型测试
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2018/10/15 01:41
 * @Copyright: Copyright(c)2018 kk All Rights Reserved
 */
public class MyGenericity<T> {
    private T data;

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

    public static void main(String[] args) {
        String[] ints = {"sd", "fsd", "sdfsk", "erw"};
        Arrays.sort(ints);
        System.out.println(Arrays.toString(ints));
    }

}
