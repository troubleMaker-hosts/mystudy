package com.example.demo.mytest;

import com.example.demo.DemoMystudyApplication;
import com.example.demo.convert.StudentConverter;
import com.example.demo.dao.primary.StudentMapper;
import com.example.demo.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        List<Student> students = studentMapper.findStudentByCondition("admin", null, null);
        students.forEach(System.out::println);
        System.out.println("---------------------------");
        StudentConverter.INSTANCE.studentListToStudyUserList(students).forEach(studyUser -> System.out.println(studyUser));
    }

}
