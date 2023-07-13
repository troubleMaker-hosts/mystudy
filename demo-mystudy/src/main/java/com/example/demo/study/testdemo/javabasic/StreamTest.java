package com.example.demo.study.testdemo.javabasic;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.model.StudyUser;

import java.util.*;
import java.util.function.Function;
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
        StreamTest test = new StreamTest();
        //内容相同，时间不同，我要去重 保留时间最小的那条
        test.test();
        List<StudyUser> list = ComparatorTest.getListStudyUser();
        list.forEach(studyUser -> studyUser.setPassword("pedTest"));
        Stream<StudyUser> stream = list.stream();
        list.forEach((StudyUser tuser) -> System.out.println(tuser.toString()));
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
        System.out.println(JSONObject.toJSONString(sortList.stream().map(StudyUser::getUserName).collect(Collectors.toList())));
        System.out.println(sortList.stream().map(StudyUser::getUserName).collect(Collectors.joining(",")));
        System.out.println("id最小的user");
        StudyUser user = sortList
                .stream()
                //.sorted((user1 , user2) -> (user1.getUserId() - user2.getUserId()))
                .min((user1, user2) -> (user1.getUserId() - user2.getUserId()))
                .get();

        System.out.println(user.getUserId());


        //结合 map 方法,使用 collect 方法 来 将 结果集 放到一个 字符串,一个 Set 或一个TreeSet中
        System.out.println("用map方法 结合 collect");
        sortList.forEach(studyUser -> System.out.println(studyUser.toString()));
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

        System.out.println("Stream.iterate ---------- test --- start");
        Stream.iterate(1, i -> ++ i).limit(5).parallel().forEach(System.out::println);
        System.out.println("Stream.iterate ---------- test --- end");
    }

    /**
     * 内容相同，时间不同，我要去重 保留时间最小的那条
     */
    private void test() {
        StudyUser studyUser = new StudyUser();
        StudyUser studyUser4 = new StudyUser();
        StudyUser studyUser2 = new StudyUser();
        StudyUser studyUser3 = new StudyUser();
        StudyUser studyUser5 = new StudyUser();
        StudyUser studyUser6 = new StudyUser();
        studyUser.setUserName("快件已在【布吉珠宝营业部C】17304412633被揽件,揽件员是【雷代军】");
        studyUser2.setUserName("快件已到达【深圳快运分拨】,会尽快安排中转,请耐心等待");
        studyUser6.setUserName("快件已到达【深圳快运分拨】,会尽快安排中转,请耐心等待");
        studyUser3.setUserName("快件已从【深圳快运分拨】出发,下一站是【东莞快运分拨】,货物正在运输中");
        studyUser4.setUserName("快件已从【深圳快运分拨】出发,下一站是【东莞快运分拨】,货物正在运输中");
        studyUser5.setUserName("快件已从【深圳快运分拨】出发,下一站是【东莞快运分拨】,货物正在运输中");
        studyUser.setPassword("2020-12-13 11:59:49");
        studyUser2.setPassword("2020-12-13 12:01:17");
        studyUser6.setPassword("2020-12-13 12:01:16");
        studyUser3.setPassword("2020-12-13 12:01:21");
        studyUser4.setPassword("2020-12-13 13:22:22");
        studyUser5.setPassword("2020-12-13 13:22:22");
        List<StudyUser> list = new ArrayList<>();
        list.add(studyUser);
        list.add(studyUser4);
        list.add(studyUser2);
        list.add(studyUser3);
        list.add(studyUser5);
        list.add(studyUser6);
        list.stream().sorted((StudyUser user1, StudyUser user2) -> (user2.getPassword().compareTo(user1.getPassword()))).forEach(stu -> System.out.println(stu.toString()));
        //去重
        List list1 = list.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(StudyUser::getUserName))), ArrayList<StudyUser> :: new
        ));
        list1.forEach(stu -> System.out.println(stu.toString()));

        System.out.println("----------分组 取最值---表达式 1------");
        Map<String, StudyUser> studyUserMap = list.stream().collect(Collectors.toMap(StudyUser::getUserName, Function.identity(), (s1, s2) -> s1.getPassword().compareTo(s2.getPassword()) > 0 ? s1 : s2));

        studyUserMap.values().forEach(study -> System.out.println(study.toString()));

        System.out.println("----------分组 取最值-- 表达式 2-------");
        studyUserMap = list.stream().collect(Collectors.groupingBy(StudyUser::getUserName,
                Collectors.collectingAndThen(Collectors.reducing((s1, s2) -> s1.getPassword().compareTo(s2.getPassword()) > 0 ? s1 : s2), Optional :: get)));

        studyUserMap.values().forEach(study -> System.out.println(study.toString()));

    }
}
