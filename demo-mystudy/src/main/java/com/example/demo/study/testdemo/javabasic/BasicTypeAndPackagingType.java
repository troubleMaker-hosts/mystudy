package com.example.demo.study.testdemo.javabasic;

/**
 * @ClassName: PrimitiveTypeAndPackagingType
 * @Description: 基本类型 和 包装类型 比较
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/10/14 00:38
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 * int（4字节）	Integer
 * byte（1字节）	Byte
 * short（2字节）	Short
 * long（8字节）	Long
 * float（4字节）	Float
 * double（8字节）	Double
 * char（2字节）	Character
 * boolean（未定）	Boolean
 */
public class BasicTypeAndPackagingType {
    public static void main(String[] args) {
        BasicTypeAndPackagingType test = new BasicTypeAndPackagingType();
        //int 和 Integer 区别
        test.intAndIntegerDistinction();
        //不同 类型(基本类型 和 包装类型) 在 使用过程中的 装箱 和 拆箱
        test.differentPrimitiveAndPackagingType();
    }

    /**
     * int 和 Integer 区别
     * 注意 : Integer、Short、Byte、Character、Long这几个类的valueOf方法的实现是类似的。
     * 　　　　Double、Float的valueOf方法的实现是类似的。
     */
    public void intAndIntegerDistinction() {
        int i = 0;
        Integer integer = new Integer(0);
        Integer integer2 = new Integer(0);
        Integer integer3 = Integer.valueOf(0);
        Integer integer4 = Integer.valueOf(0);
        // 如果值在-128 ~ +127的话，都会从Integer.IntegerCache.cache这个数据里面去取，
        // 所以说通过自动装箱方式生成Integer对象的时候，赋值在-128 ~ +127之间的时候，生成的多个对象都是同一个，
        // 这就是所谓的“享元模式”。
        // 而赋值超出这个范围的时候，会通过new Integer()来生成对象
        Integer integer5 = 0;
        Integer integer6 = 0;
        //==指引用是否相同， equals()指的是值是否相同
        System.out.println("Integer integer = new" +
                " Integer(0); Integer integer2 = new Integer(0); : integer == integer2 : " + (integer == integer2));
        System.out.println("Integer integer = new Integer(0); Integer integer2 = new Integer(0); : integer.equals(integer2)) : " + (integer.equals(integer2)));
        System.out.println("i = " + i + " 时 比较: i == integer : " + (i == integer));
        System.out.println("i = " + i + " 时 比较: i == integer2 : " + (i == integer2));
        //1.==是判断两个变量或实例是不是指向同一个内存空间，equals是判断两个变量或实例所指向的内存空间的值是不是相同
        //2.==是指对内存地址进行比较 ， equals()是对字符串的内容进行比较
        //3.==指引用是否相同， equals()指的是值是否相同
        System.out.println("i = " + i + " 时 比较: integer == integer2 : " + (integer == integer2));
        System.out.println("i = " + i + " 时 比较: integer.equals(integer3)) : " + (integer.equals(integer3)));
        System.out.println("i = " + i + " 时 比较: integer3 == integer4) : " + (integer3 == integer3));
        System.out.println("i = " + i + " 时 比较: integer3.equals(integer4)) : " + (integer3.equals(integer4)));
        System.out.println("i = " + i + " 时 比较: integer5 == integer6) : " + (integer5 == integer6));
        System.out.println("i = " + i + " 时 比较: integer5.equals(integer6)) : " + (integer5.equals(integer6)));
        i = 1280;
        integer = new Integer(1280);
        integer2 = new Integer(1280);
        integer3 = Integer.valueOf(1280);
        integer4 = Integer.valueOf(1280);
        integer5 = 1280;
        integer6 = 1280;
        System.out.println("i = " + i + " 时 比较: i == integer : " + (i == integer));
        System.out.println("i = " + i + " 时 比较: i == integer2 : " + (i == integer2));
        System.out.println("i = " + i + " 时 比较: integer == integer2 : " + (integer == integer2));
        System.out.println("i = " + i + " 时 比较: integer.equals(integer3)) : " + (integer.equals(integer3)));
        System.out.println("i = " + i + " 时 比较: integer3 == integer4) : " + (integer3 == integer3));
        System.out.println("i = " + i + " 时 比较: integer3.equals(integer4)) : " + (integer3.equals(integer4)));
        System.out.println("i = " + i + " 时 比较: integer5 == integer6 : " + (integer5 == integer6));
        System.out.println("i = " + i + " 时 比较: integer5.equals(integer6)) : " + (integer5.equals(integer6)));
    }

    /**
     * 不同 类型(基本类型 和 包装类型) 在 使用过程中的 装箱 和 拆箱
     */
    public void differentPrimitiveAndPackagingType() {
        Integer a = 1;
        Integer b = 127;
        Integer c = 128;
        Long g = 128L;
        Long h = 127L;
        //当 "=="运算符的两个操作数都是 包装器类型的引用，
        // 则是比较指向的是否是同一个对象，
        // 而如果其中有一个操作数是表达式（即包含算术运算）则比较的是数值（即会触发自动拆箱的过程）。
        // 另外，对于包装器类型，equals方法并不会进行类型转换。
        //此处 a + b = Integer.valueOf(a + b)
        System.out.println("Integer a = 1;  Integer b = 127;  Integer c = 128; : " + (c == (a + b)));
        System.out.println("Integer a = 1;  Integer b = 127;  Integer c = 128; : " +  c.equals(a + b));
        System.out.println("Integer a = 1;  Integer b = 127;  Long g = 128L; : " + (g == (a + b)));
        System.out.println("Integer a = 1;  Integer b = 127;  Long g = 128L; : " + g.equals(a + b));
        System.out.println("Integer a = 1;  Long h = 127L;  Long g = 128L; : " + g.equals(a + h));
    }
}
