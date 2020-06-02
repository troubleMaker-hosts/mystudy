package com.example.demo.study.testdemo.javabasic;

import com.example.demo.model.StudyUser;

import java.util.*;

/**
 * @ClassName: ComparatorTest
 * @Description: 比较器 测试类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2018/11/19 01:14
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class ComparatorTest {
    public static void main(String[] args) {
        List<StudyUser> list = ComparatorTest.getListStudyUser();
        String string = "hehe";
        String str = "hehe";
        System.out.println("string比较");
        System.out.println(str == string);
        System.out.println(str.equals(string));
        Collections.sort(list, new Comparator<StudyUser>() {
            @Override
            public int compare(StudyUser o1, StudyUser o2) {
                return o1.getUserId() - o2.getUserId();
            }
        });
        System.out.println("排序");
        for (StudyUser user : list) {
            System.out.println(user.getUserId() + "   :::::::  " + user.getUserName());
        }
        Collections.sort(list, new Comparator<StudyUser>() {
            @Override
            public int compare(StudyUser o1, StudyUser o2) {
                return o1.getUserName().compareTo(o2.getUserName());
            }
        });
        System.out.println("排序");
        for (StudyUser user : list) {
            System.out.println(user.getUserId() + "   :::::::  " + user.getUserName());
        }
        //System.out.println("byid分层");
        //List<List<StudyUser>> byId = divider(list, new Comparator<StudyUser>() {
        //    @Override
        //    public int compare(StudyUser o1, StudyUser o2) {
        //        return (o1.getUserId() / 10  == o2.getUserId() / 10) ? 0 : 1 ;
        //    }
        //});
        //for (List<StudyUser> tUsers : byId) {
        //    for (StudyUser user : tUsers) {
        //        System.out.println(user.getUserId()+"   :::::::  "+user.getUserName());
        //    }
        //}
        System.out.println("byname 分组");
        List<List<StudyUser>> byName = divider(list, new Comparator<StudyUser>() {
            @Override
            public int compare(StudyUser o1, StudyUser o2) {
                return o1.getUserName().compareTo(o2.getUserName());
            }
        });
        for (List<StudyUser> tUsers : byName) {
            for (StudyUser user : tUsers) {
                System.out.println(user.getUserId() + "   :::::::  " + user.getUserName());
            }
        }

    }

    /**
     *  给 一个集合 分组 : 分成多个集合
     * @param datas Collection<T> 集合数据
     * @param c 比较器
     * @param <T>   集合参数类型
     * @return  分组之后的 所有集合
     */
    private static <T> List<List<T>> divider(Collection<T> datas, Comparator<? super T> c) {
        List<List<T>> result = new ArrayList<>();
        for (T t : datas) {
            boolean isSameGroup = false;
            for (List<T> ts : result) {
                if (c.compare(t, ts.get(0)) == 0) {
                    isSameGroup = true;
                    ts.add(t);
                    break;
                }
            }
            if (!isSameGroup) {
                //创建
                List<T> innerList = new ArrayList<>();
                result.add(innerList);
                innerList.add(t);
            }
        }
        return result;
    }

    /**
     * 得到(初始化) ListStudyUser
     * @return StudyUser数组
     */
    public static List<StudyUser> getListStudyUser() {
        StudyUser tUser = new StudyUser(11, "h1", "safd", "234546");
        StudyUser tUser1 = new StudyUser(12, "h2", "safd", "234546");
        StudyUser tUser2 = new StudyUser(1, "h1", "safd", "234546");
        StudyUser tUser3 = new StudyUser(31, "h3", "safd", "234546");
        StudyUser tUser4 = new StudyUser(21, "h2", "safd", "234546");
        StudyUser tUser5 = new StudyUser(51, "h1", "safd", "234546");
        List<StudyUser> list = new ArrayList<>();
        list.add(tUser);
        list.add(tUser1);
        list.add(tUser2);
        list.add(tUser3);
        list.add(tUser4);
        list.add(tUser5);
        return list;
    }
}
