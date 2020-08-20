package com.example.demo.study.testdemo.javabasic;

/**
 * @ClassName: InteriorTest
 * @Description:    内部类 测试
 * @Author: willie
 * @version: 1.0.0
 * @Date: 2020/08/20 10:42
 * @Copyright: Copyright(c)2020 willie All Rights Reserved
 */
public class InteriorTest {
    int p = 2;
    public class InnerClass {
        int p = 3;
        void inCall () {
            InteriorTest.this.p = 4;
            System.out.println("InnerClass.inCall : " + InteriorTest.this.p);
        }
    }

    private static class AnotherInnerClass {
        int p = 5;
    }

    public static void main(String[] args) {
        InteriorTest interiorTest = new InteriorTest();
        //InnerClass innerClass = new InnerClass();
        //成员内部类是依附外部类而存在的，也就是说，如果要创建成员内部类的对象，前提是必须存在一个外部类的对象。
        InnerClass innerClass = interiorTest.new InnerClass();
        //静态内部类是不需要依赖于外部类的，这点和类的静态成员属性有点类似，
        // 并且它不能使用外部类的非static成员变量或者方法，这点很好理解，因为在没有外部类的对象的情况下，
        // 可以创建静态内部类的对象，如果允许访问外部类的非static成员就会产生矛盾，因为外部类的非static成员必须依附于具体的对象。
        AnotherInnerClass anotherInnerClass1 = new AnotherInnerClass();
        AnotherInnerClass anotherInnerClass2 = new InteriorTest.AnotherInnerClass();

        innerClass.inCall();
        System.out.println("interiorTest.p : " + interiorTest.p);
    }
}
