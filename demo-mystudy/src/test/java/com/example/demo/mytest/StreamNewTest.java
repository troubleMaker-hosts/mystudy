package com.example.demo.mytest;

import com.example.demo.DemoMystudyApplication;
import com.example.demo.dao.primary.StudentMapper;
import com.example.demo.model.Student;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: StreamNewTest
 * @Description:    streamTest
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2021/06/26 01:40
 * @Copyright: Copyright(c)2021 kk All Rights Reserved
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {DemoMystudyApplication.class})
public class StreamNewTest {
    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void test() {
        List<Student> studentList = initStudent();
        List<Student> students = initStudent();
        System.out.println("-----流 里面的赋值 操作 会修改流-----");
        students.stream().filter(stu -> "女".equals(stu.getSex())).collect(Collectors.toList()).forEach(student -> student.setUpdateUser("---updateUser--"));
        students.forEach(System.out::println);
        //max
        Student student = studentList.stream().filter(stu -> "女".equals(stu.getSex())).max(Comparator.comparing(Student::getCreateTime)).orElse(null);
        System.out.println("---------.max(Comparator.comparing(Student::getCreateTime))-----------");
        System.out.println(student);
        System.out.println("---------.max(Comparator.comparing(Student::getUpdateTime))-----------");
        student = studentList.stream().filter(stu -> "女".equals(stu.getSex())).max(Comparator.comparing(stu -> {
            try {
                return Objects.isNull(stu.getUpdateTime()) ? DateUtils.parseDate("2000-01-01", "yyyy-MM-dd") : stu.getUpdateTime();
            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("日期转换异常 : " + e.getMessage());
            }
            return new Date();
        })).orElse(null);
        System.out.println(student);
        System.out.println("---------.map(Student::getAge).reduce-----------");
        System.out.println(studentList.stream().filter(stu -> "女".equals(stu.getSex())).map(Student::getAge).reduce(0, Integer::sum));
        System.out.println("---------reduce-----------");
        System.out.println(studentList.stream().filter(stu -> "女".equals(stu.getSex())).reduce(0, (ageSum, stu) -> ageSum + stu.getAge(), Integer::sum));
        Set<String> stringSet = Sets.newHashSet();
        System.out.println(CollectionUtils.isEmpty(stringSet));
        System.out.println("---------时间 分割 去重-----------");
        studentList.stream()
                .filter(stu -> "女".equals(stu.getSex()))
                .map(stu -> DateFormatUtils.format(stu.getCreateTime(), "yyyy-MM-dd HH:mm:ss").split(" "))
                .forEach(strings -> stringSet.addAll(Lists.newArrayList(strings)));
        stringSet.forEach(System.out::println);
    }

    /**
     * 初始化 student
     */
    private List<Student> initStudent() {
        return studentMapper.findStudentByCondition("test", null, null);
    }
}
