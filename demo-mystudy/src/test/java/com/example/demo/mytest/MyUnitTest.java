package com.example.demo.mytest;

import com.example.demo.DemoMystudyApplication;
import com.example.demo.convert.StudentConverter;
import com.example.demo.dao.primary.StudentMapper;
import com.example.demo.model.Student;
import com.example.demo.model.StudyUser;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MyUnitTest
 * @Description: 单元测试
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/09/17 01:31
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {DemoMystudyApplication.class})
public class MyUnitTest {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentConverter studentConverter;

    @Test
    public void test() {
        //mysql 不支持 || 拼接字符串, 只支持 concat()
        studentMapper.selectByNameTest("student", "test12").forEach(student -> System.out.println(student.toString()));
    }

    /**
     * 测试 mapstruct converter
     */
    @Test
    public void converterTest() {
        List<Student> students = getStudentByName("admin");
        System.out.println("---------------------------");
        //studentListToStudyUserList
        //StudentConverter.INSTANCE.studentListToStudyUserList(students).forEach(studyUser -> System.out.println(studyUser));
        List<StudyUser> studyUsers = studentConverter.studentListToStudyUserList(students);
        studyUsers.forEach(System.out::println);

        //studyUserListToStudentList
        studentConverter.studyUserListToStudentList(studyUsers).forEach(System.out::println);

    }

    /**
     * BeanUtils.CopyProperties 方法 测试
     */
    @Test
    public void beanUtilsCopyProperties() {
        List<Student> students = getStudentByName("admin");
        StudyUser<String> studyUser = new StudyUser<>();
        students.forEach(student -> {
            BeanUtils.copyProperties(student, studyUser);
            System.out.println(studyUser);
        });

    }

    /**
     * 深拷贝 和 浅拷贝 test
     */
    @Test
    public void cloneTest() throws Exception {
        List<Student> students = getStudentByName("admin");
        students.get(0).setStudyUser(studentConverter.studentToStudyUser(students.get(0)));
        Student student = (Student) students.get(0).clone();
        student.setName("cloneTest");
        student.setTestGen(Lists.newArrayList("cloneTest1", "testClone2"));
        System.out.println(students.get(0));
        System.out.println(student);
        System.out.println("---------------------拷贝-----------------------");
        students.get(0).getStudyUser().setUserName("深拷贝 test");
        System.out.println(students.get(0));
        System.out.println(student);
    }

    /**
     * 根据 name 获取 student
     * @param name  name
     * @return  List<Student>
     */
    private List<Student> getStudentByName(String name) {
        List<Student> students = studentMapper.findStudentByCondition(name, null, null);
        //泛型 测试
        List<String> stringList = new ArrayList<>();
        students.forEach(student -> {
            stringList.add(student.getName());
            student.setTestGen(stringList);
            System.out.println(student);
        });
        return students;
    }
}
