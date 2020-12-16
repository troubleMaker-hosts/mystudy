package com.example.demo.mytest;

import com.example.demo.DemoMystudyApplication;
import com.example.demo.scheduletask.scheduletest.SynAndAsynScheduleTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ExecutionException;

/**
 * @ClassName: AyncsTest
 * @Description:
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/12/10 15:02
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {DemoMystudyApplication.class})
public class AsyncTest {
    @Autowired
    private SynAndAsynScheduleTest synAndAsynScheduleTest;

    /**
     * 测试 @Async 注解, 异步返回resule, 调用 Future<>.get() 方法, 程序会等待 线程执行完成, 获取结果.
     *
     * synchronizationScheduleTest1() 使用 Future<>.get() 方法之后, synchronizationScheduleTest3() 方法会等 synchronizationScheduleTest1()执行完毕后, 再执行.
     * 而 不会等待 synchronizationScheduleTest2() 执行完毕.
     */
    @Test
    public void test() {
        synAndAsynScheduleTest.synchronizationScheduleTest2();
        try {
            System.out.println("AsyncTest : " + synAndAsynScheduleTest.synchronizationScheduleTest1().get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("AsyncTest : " + synAndAsynScheduleTest.synchronizationScheduleTest3());

    }
}
