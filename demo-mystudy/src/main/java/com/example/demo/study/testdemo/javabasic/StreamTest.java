package com.example.demo.study.testdemo.javabasic;

import com.example.demo.model.StudyUser;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName: StreamTest
 * @Description: Stream(流式编程) 测试类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2018/11/19 01:14
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class StreamTest {
    public static void main(String[] args) {
        List<StudyUser> list = ComparatorTest.getListStudyUser();
        Stream<StudyUser> stream = list.stream();
        list.forEach((StudyUser tuser) -> System.out.println(tuser.getUserId()));
        System.out.println("过滤之后");
        stream.filter(user -> user.getUserId() > 20)
                .forEach((StudyUser user) -> System.out.println(user.getUserId()));

        //String[] players = {"Rafael Nadal", "Novak Djokovic",
        //        "Stanislas Wawrinka", "David Ferrer",
        //        "Roger Federer", "Andy Murray",
        //        "Tomas Berdych", "Juan Martin Del Potro",
        //        "Richard Gasquet", "John Isner"};
        //Arrays.sort(players , (String s1 ,String s2) -> (s1.compareTo(s2)));
        //for (String player : players) {
        //    System.out.println(player);
        //}

        System.out.println("排序之后");
        List<StudyUser> sortList = list.stream()
                .sorted((StudyUser user1, StudyUser user2) -> (user2.getUserId() - user1.getUserId()))
                .limit(3)
                .collect(Collectors.toList());
        sortList.forEach(user -> System.out.println(user.getUserId()));
        System.out.println("id最小的user");
        StudyUser user = sortList
                .stream()
                //.sorted((user1 , user2) -> (user1.getUserId() - user2.getUserId()))
                .min((user1, user2) -> (user1.getUserId() - user2.getUserId()))
                .get();

        System.out.println(user.getUserId());


        //结合 map 方法,使用 collect 方法 来 将 结果集 放到一个 字符串,一个 Set 或一个TreeSet中
        System.out.println("用map方法 结合 collect");
        String myName = sortList.stream()
                .map(StudyUser::getUserName)
                .collect(Collectors.joining(";"));
        System.out.println(myName);

        TreeSet<String> userSet = sortList
                .stream()
                .map(StudyUser::getUserName)
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(userSet);


        System.out.println("计数");
        String[] strings = {"23", "234", "4", "45", "32", "234", "23", "234", "4", "45", "32", "234", "89", "70"};
        List<String> stringList = Arrays.asList(strings);
        stringList.stream()
                .map(str -> new Integer(str))
                .sorted()
                //.distinct()
                //.forEach(str -> System.out.println(str));
                .collect(Collectors.groupingBy(str -> str, Collectors.summingInt(str -> 1)))

                .forEach((Integer k, Integer v) -> System.out.println(k + "   " + v));
        //判断是否存在
        System.out.println("是否存在");
        long count = stringList.stream()
                .map(str -> new Integer(str))
                .filter(str -> str == 400)
                .count();
        System.out.println(count);
    }
}
