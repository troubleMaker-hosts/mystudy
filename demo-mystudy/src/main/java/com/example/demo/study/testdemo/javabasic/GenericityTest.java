package com.example.demo.study.testdemo.javabasic;

import com.example.demo.model.StudyUser;
import com.example.demo.study.entity.MyGenericity;

/**
 * @ClassName: GenericityTest
 * @Description: 泛型 测试类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2018/11/19 01:14
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class GenericityTest {

    public  static void   main(String[] args){
        System.out.println(11);
        StudyUser tUser = new StudyUser(11,"h1","safd","234546") ;

        MyGenericity<StudyUser> myGenericity = new MyGenericity(tUser) ;
        System.out.println(myGenericity.getData().getClass());
        System.out.println(myGenericity.getClass());
        //myGenericity.setData(1);
        StudyUser tUser1 = myGenericity.getData() ;
        System.out.println(myGenericity.getClass());
        System.out.println(myGenericity.getData().getClass());
        System.out.println(myGenericity.getData());
    }
}
