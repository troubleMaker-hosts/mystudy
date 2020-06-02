package com.example.demo.study.testdemo.javabasic;

import com.example.demo.model.StudyUser;
import org.apache.commons.lang3.ArrayUtils;

import java.math.BigDecimal;

/**
 * @ClassName: ValuePropagationTest
 * @Description: java 值传递 test
 * 值传递（pass by value）是指在调用函数时将实际参数复制一份传递到函数中，这样在函数中如果对参数进行修改，将不会影响到实际参数。
 * 引用传递（pass by reference）是指在调用函数时将实际参数的地址直接传递到函数中，那么在函数中对参数所进行的修改，将影响到实际参数。
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/10/12 00:28
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class ValuePropagationTest {
    public static void main(String[] args) {
        String str = "strTest";
        //数组
        int[] ints = new int[]{1, 2, 3};
        StudyUser studyUser = new StudyUser(1, "userName1", "password1", "phone1");
        // 通过自动装箱的方式来生成一个Integer对象的时候，
        // 如果我们传的值在-128 ~ +127的话，都会从Integer.IntegerCache.cache这个数据里面去取，
        // 所以说通过自动装箱方式生成Integer对象的时候，赋值在-128 ~ +127之间的时候，生成的多个对象都是同一个，
        // 这就是所谓的“享元模式”。
        // 而赋值超出这个范围的时候，会通过new Integer()来生成对象
        Integer i = new Integer(0);
        ValuePropagationTest test = new ValuePropagationTest();
        BigDecimal bigDecimal = new BigDecimal(0);
        for (int j = 0; j < 6; j ++) {
            str = test.returnReferenceTypes(str, studyUser, i, bigDecimal, ints);
            System.out.println("第 " + j + " 次: str: " + str + " ,i: " + i + " ,bigDecimal: " + bigDecimal);
            System.out.println(studyUser.toString());
        }
        if (ints[0] == 1) {
            System.out.println("查看 数组 的 值 沒有变化: int[0]: " + ints[0]);
        } else {
            System.out.println("查看 数组 的 值 有变化: int[0]: " + ints[0]);
        }
    }

    /**
     * 返回引用类型测试
     * 测试结果: String 和 StudyUser 的 返回的值都 改变了, 但是 Integer 和 BigDecimal 的 值 没有变.
     * java 是 值传递
     * 注释 : Integer i 在使用的时候 发生拆箱操作，此时的到的值为基本类型，
     * 此时 的基本类型 已经和 Integer i 不是同一个变量了.
     * 所以 Integer i 的 值(堆区) 并没有改变.
     * 以后用到包装类就把它们当做基本类型来对待就成了，否则很多坑。
     * @param str 测试值传递String 类型
     * @param studyUser 测试值传递实例对象
     * @param i 测试值传递 Integer 类型
     * @param bigDecimal    测试值传递 bigDecimal 类型
     * @param ints  测试值传递 int[] 类型
     * @return  测试值传递 String 类型 的 值
     */
    public String returnReferenceTypes(String str, StudyUser studyUser, Integer i, BigDecimal bigDecimal, int[] ints) {
        //修改数组的 值
        if (ArrayUtils.isNotEmpty(ints)) {
            ints[0] = 222;
        }
        str = str + i;
        studyUser.setUserId(studyUser.getUserId() + 1);
        studyUser.setUserName(studyUser.getUserName() + i);
        studyUser.setPassword(studyUser.getPassword() + i);
        studyUser.setPhone(studyUser.getPhone() + i);
        i ++;
        bigDecimal = bigDecimal.add(new BigDecimal(2)) ;
        System.out.println(bigDecimal);
        System.out.println(i);
        System.out.println(i.getClass());
        return str;
    }
}

