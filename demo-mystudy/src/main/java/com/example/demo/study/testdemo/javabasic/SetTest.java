package com.example.demo.study.testdemo.javabasic;
import java.util.*;

import com.example.demo.model.Student;

/**
 * @ClassName: SetTest
 * @Description: Set 测试类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2018/11/19 01:14
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class SetTest {
    public static void main(String[] args) {
        SetTest test = new SetTest();
        List<String> arrayList = test.initArrayList();
        //通过 set 给 list 去重
        test.removeRepetitiveBySet(arrayList);

        //重写 Student 的 比较器(Comparator)

        Set<Student> treeSet = new TreeSet<>(new Comparator<Student>() {
            //return -1; //-1表示放在红黑树的左边,即逆序输出
            //return 1;  //1表示放在红黑树的右边，即顺序输出
            //return 0;  //表示元素相同，仅存放第一个元素
            @Override
            public int compare(Student o1, Student o2) {
                //比較名字（按照降序排序）， 如果名字 相同 則不添加
                return o2.getName().compareTo(o1.getName());
            }
        });
        Student stu = new Student();
        stu.setName("treeSet" + 1);
        treeSet.add(stu);
        test.initTreeSetStudent(treeSet, 10);
        test.initTreeSetStudent(treeSet, 10);
        for (Student student : treeSet) {
            System.out.println("重写 Student 的 比较器(Comparator) : " + student);
        }
        //重写 String 的 Comparator
        Set<String> stringTreeSet = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        test.initTreeSet(stringTreeSet, 10);
        test.initTreeSet(stringTreeSet, 10);
        for (int i = 0; i < 10; i ++) {
            String str = "test";
            stringTreeSet.add(str + i);
        }
        for (String s : stringTreeSet) {
            System.out.println("重写 String 的 Comparator : " + s);
        }

        //普通的 treeSet
        Set<String> generalTreeSet = new TreeSet<>();
        test.initTreeSet(generalTreeSet, 10);
        for (String s : generalTreeSet) {
            System.out.println("普通的 treeSet : " + s);
        }



    }


    /**
     * 通过 set 给 list 去重
     * set 去重保留的是 第一个 add() 到 set 中的 value
     * @param arrayList 被去重的list
     */
    public void removeRepetitiveBySet(List<String> arrayList) {
        Set<String> hashSet = new HashSet() ;
        hashSet.addAll(arrayList);
        System.out.println("list遍历");
        for (Object item : arrayList) {
            System.out.println(item.toString());
        }
        System.out.println("set 遍历");
        for (Object next : hashSet) {
            System.out.println(next.toString());
        }

        //System.out.println("list遍历    去重后");
        //arrayList.clear();
        //arrayList.addAll(hashSet) ;
        //for (Object item : arrayList) {
        //    System.out.println(item.toString());
        //}

        //利用set的add方法  给 list 去重  并保持list中的顺序不变
        List<String> newList = new ArrayList() ;
        hashSet.clear();
        for (Object item : arrayList) {
            if(hashSet.add(item.toString())){
                newList.add(item.toString());
            }
        }
        System.out.println("利用set的add方法  给 list 去重  并保持list中的顺序不变");
        for (Object o : newList) {
            System.out.println(o.toString());
        }
    }

    /**
     * 初始化 arrayList(Sting)
     * @return  初始化 arrayList(Sting) 之后的 arrayList
     */
    public List<String> initArrayList() {
        List<String> arrayList = new ArrayList();
        arrayList.add("1111");
        arrayList.add("2222");
        arrayList.add("333");
        arrayList.add("44");
        arrayList.add("115511");
        arrayList.add("1111");
        return arrayList;
    }

    /**
     * 初始化 TreeSet(Student)
     * @param treeSet   有序set
     * @param initNum   初始化 TreeSet 的 大小
     */
    public void initTreeSetStudent(Set<Student> treeSet, int initNum) {
        for (int i = 0; i < initNum; i++) {
            Student student = new Student();
            student.setId(i);
            student.setName("treeSet" + i);
            student.setAge(i);
            student.setSex("男");
            student.setCreateTime(new Date());
            student.setCreateUser("treeSetCreate" + i);
            student.setUpdateTime(new Date());
            student.setUpdateUser("treeSetUpdate" + i);
            student.setStatus("1");
            treeSet.add(student);
        }
    }

    /**
     * 初始化 TreeSet(String)
     * @param treeSet   有序set
     * @param initNum   初始化 TreeSet 的 大小
     */
    public void initTreeSet(Set<String> treeSet, int initNum) {
        for (int i = 0; i < initNum; i++) {
            String str = "test" + i;
            treeSet.add(str);
        }
    }
}
