package com.example.demo.mytest;

import com.example.demo.DemoMystudyApplication;
import com.example.demo.dao.primary.AccountNumberMapper;
import com.example.demo.dao.primary.StudentMapper;
import com.example.demo.model.AccountNumber;
import com.example.demo.utils.EncryptUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
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
        System.out.println(studentMapper.selectByNameTest("student", "test12"));
    }

}
