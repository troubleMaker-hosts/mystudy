package com.example.demo.scheduletask.scheduletest;

import com.example.demo.dao.primary.StudentMapper;
import com.example.demo.utils.ScheduleTaskUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 * @ClassName: DynamicScheduleTest
 * @Description: 动态 定时任务 测试
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/11/18 01:44
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
@Component
public class DynamicScheduleTest {
    private Logger logger = LogManager.getLogger(ScheduleTaskUtils.class);

    @Autowired
    private StudentMapper studentMapper;

    /**
     * schedule 动态 定时任务 test1
     * @param parameterMap 参数Map
     */
    public void scheduleTest1(HashMap<String, Object> parameterMap) {
        logger.debug("scheduleTest1: " + "开始执行");
        //默认线程名
        String threadName = "scheduleThread1";
        defaultScheduleExecute(parameterMap, threadName);
        logger.debug("scheduleTest1: " + "结束执行");
    }

    /**
     * schedule 动态 定时任务 test2
     * @param parameterMap 参数Map
     */
    public void scheduleTest2(HashMap<String, Object> parameterMap) {
        logger.debug("scheduleTest2: " + "开始执行");
        //默认线程名
        String threadName = "scheduleThread2";
        defaultScheduleExecute(parameterMap, threadName);
        logger.debug("scheduleTest2: " + "结束执行");    }

    /**
     * schedule 动态 定时任务 test3
     * @param parameterMap 参数Map
     */
    public void scheduleTest3(HashMap<String, Object> parameterMap) {
        logger.debug("scheduleTest3: " + "开始执行");
        //默认线程名
        String threadName = "scheduleThread3";
        defaultScheduleExecute(parameterMap, threadName);
        logger.debug("scheduleTest3: " + "结束执行");
    }

    /**
     *  当定时器 参数 为 null 时, 默认执行 耗时操作 次数(20000)
     * @param parameterMap 定时任务参数
     * @param threadName    线程名
     */
    public void defaultScheduleExecute(HashMap<String, Object> parameterMap, String threadName) {
        if (CollectionUtils.isEmpty(parameterMap)) {
            System.out.println(timeConsuming(20000, threadName));
        } else {
            System.out.println(timeConsuming(Integer.valueOf(parameterMap.get("count").toString()), parameterMap.get("threadName").toString()));
        }
    }

    /**
     * 耗时操作
     * @param count 次数 (建议 count > 50000)
     * @param threadName 线程名
     * @return 耗时日志
     */
    public String timeConsuming(Integer count, String threadName) {
        count = (count == null ? 0 : count);
        LocalDateTime startTime = LocalDateTime.now();
        logger.debug("线程: " + threadName + "  : 开始耗时操作执行时间 : " +
                startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " 执行次数: " + count);
        //消耗时间 随 count 改变, 建议 count > 50000
        for (int i = 0; i < count; i ++) {
            studentMapper.selectByPrimaryKey(1);
        }
        LocalDateTime endTime = LocalDateTime.now();
        logger.debug("线程: " + threadName + "  : 结束耗时操作执行时间 : " +
                endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        String res = "线程: " + threadName + "  : 本次耗时操作执行时间(s) : " +
                Duration.between(startTime, endTime).getSeconds();
        logger.debug(res);
       return res;
    }
}
