package com.example.demo.mytest;

import com.example.demo.DemoMystudyApplication;
import com.example.demo.convert.StudentConverter;
import com.example.demo.dao.primary.StudentMapper;
import com.example.demo.model.Student;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
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
        //max
        Student student = studentList.stream().filter(stu -> "女".equals(stu.getSex())).max(Comparator.comparing(Student::getCreateTime)).get();
        System.out.println(student);
        System.out.println("---------.map(Student::getAge).reduce-----------");
        System.out.println(studentList.stream().filter(stu -> "女".equals(stu.getSex())).map(Student::getAge).reduce(0, Integer::sum));
        System.out.println("---------reduce-----------");
        System.out.println(studentList.stream().filter(stu -> "女".equals(stu.getSex())).reduce(0, (ageSum, stu) -> ageSum + stu.getAge(), Integer::sum));
        Set<String> stringSet = Sets.newHashSet();
        System.out.println(CollectionUtils.isEmpty(stringSet));
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
