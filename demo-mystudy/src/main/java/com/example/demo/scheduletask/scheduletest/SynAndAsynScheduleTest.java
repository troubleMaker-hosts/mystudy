package com.example.demo.scheduletask.scheduletest;

import com.example.demo.dao.StudentMapper;
import com.example.demo.model.Student;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * @ClassName: SynchronizationScheduleTest
 * @Description: 同步(普通)定时器测试
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/10/15 01:41
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
//Schedule 测试 时 才打开 此注释(@Component)
//@Component
public class SynAndAsynScheduleTest {
    @Value("${schedule.synchronization.test1}")
    private String scheduleSynchronizationTest1;

    @Value("${schedule.synchronization.test2}")
    private String scheduleSynchronizationTest2;

    @Value("${schedule.synchronization.test3}")
    private String scheduleSynchronizationTest3;

    @Autowired
    private StudentMapper studentMapper;

    /**
     * synchronization 定时器测试 1 : 5s 一次
     *
     * 注解 解释: @Async调用中的事务处理机制
     *
     * 在@Async标注的方法，同时也适用了@Transactional进行了标注；
     *  在其调用数据库操作之时，将无法产生事务管理的控制，原因就在于其是基于异步处理的操作。
     *
     * 那该如何给这些操作添加事务管理呢？可以将需要事务管理操作的方法放置到异步方法内部，在内部被调用的方法上添加@Transactional.
     *         例如：  方法A，使用了@Async/@Transactional来标注，但是无法产生事务控制的目的。
     * 方法B，使用了@Async来标注，  B中调用了C、D，C/D分别使用@Transactional做了标注，则可实现事务控制的目的。
     */
    @Async("threadPool")
    @Scheduled(cron = "${schedule.synchronization.test1}")
    public void synchronizationScheduleTest1() {
        System.out.println(scheduleSynchronizationTest1 +  " : schedule.synchronization.test1 : Thread.sleep(6000) 之前: "
                + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        System.out.println(Thread.currentThread().getName() + " ------schedule.synchronization.test1  正在使用");
        //sleep()和wait()的区别
        // sleep()和wait()来自不同的类,分别是Thread和Object
        //最主要是sleep方法没有释放锁，而wait方法释放了锁，使得其他线程可以使用同步控制块或者方法(锁代码块和方法锁)。
        //wait，notify和notifyAll只能在同步控制方法或者同步控制块里面使用，而sleep可以在任何地方使用(使用范围)
        //sleep必须捕获异常，而wait，notify和notifyAll不需要捕获异常
        try {
            //此处 sleep() 方法 由于没有释放锁 会导致 定时器 test2 和 test3 延后执行(在sleep() 之后 会马上执行), 但是 执行频率 不会变, 如下:
            /**
             * 0/5 * * * * ? : schedule.synchronization.test1 : Thread.sleep(6000) 之前: 2019-10-15 02:31:25
             * 0/5 * * * * ? : schedule.synchronization.test1 : Thread.sleep(6000) 之后: 2019-10-15 02:31:31
             * 0/10 * * * * ? : schedule.synchronization.test2 : 2019-10-15 02:31:31
             * 0/15 * * * * ? : schedule.synchronization.test3 : 2019-10-15 02:31:31                        ------ 此处 test2 和 test3 在 sleep 之后执行, 然后设置 各自的 执行频率
             * 0/5 * * * * ? : schedule.synchronization.test1 : Thread.sleep(6000) 之前: 2019-10-15 02:31:35
             * 0/5 * * * * ? : schedule.synchronization.test1 : Thread.sleep(6000) 之后: 2019-10-15 02:31:41
             * 0/10 * * * * ? : schedule.synchronization.test2 : 2019-10-15 02:31:41
             * 0/5 * * * * ? : schedule.synchronization.test1 : Thread.sleep(6000) 之前: 2019-10-15 02:31:45
             * 0/5 * * * * ? : schedule.synchronization.test1 : Thread.sleep(6000) 之后: 2019-10-15 02:31:51
             * 0/15 * * * * ? : schedule.synchronization.test3 : 2019-10-15 02:31:51
             * 0/10 * * * * ? : schedule.synchronization.test2 : 2019-10-15 02:31:51                        ----- 此处 test3 由于 test1 中的 sleep方法, 延后 5s 执行(本应该执行时间: 2019-10-15 02:31:46), 但是下次执行 没有 sleep 干扰 会提前 5 秒执行(下次执行时间 : 2019-10-15 02:32:01)
             * 0/5 * * * * ? : schedule.synchronization.test1 : Thread.sleep(6000) 之前: 2019-10-15 02:31:55
             * 0/5 * * * * ? : schedule.synchronization.test1 : Thread.sleep(6000) 之后: 2019-10-15 02:32:01
             * 0/15 * * * * ? : schedule.synchronization.test3 : 2019-10-15 02:32:01
             * 0/10 * * * * ? : schedule.synchronization.test2 : 2019-10-15 02:32:01
             * 0/5 * * * * ? : schedule.synchronization.test1 : Thread.sleep(6000) 之前: 2019-10-15 02:32:05
             * 0/5 * * * * ? : schedule.synchronization.test1 : Thread.sleep(6000) 之后: 2019-10-15 02:32:11
             * 0/10 * * * * ? : schedule.synchronization.test2 : 2019-10-15 02:32:11
             * 0/15 * * * * ? : schedule.synchronization.test3 : 2019-10-15 02:32:15
             * 0/5 * * * * ? : schedule.synchronization.test1 : Thread.sleep(6000) 之前: 2019-10-15 02:32:15
             * 0/5 * * * * ? : schedule.synchronization.test1 : Thread.sleep(6000) 之后: 2019-10-15 02:32:21
             * 0/10 * * * * ? : schedule.synchronization.test2 : 2019-10-15 02:32:21
             * 0/5 * * * * ? : schedule.synchronization.test1 : Thread.sleep(6000) 之前: 2019-10-15 02:32:25
             * 0/5 * * * * ? : schedule.synchronization.test1 : Thread.sleep(6000) 之后: 2019-10-15 02:32:31
             * 0/15 * * * * ? : schedule.synchronization.test3 : 2019-10-15 02:32:31
             * 0/10 * * * * ? : schedule.synchronization.test2 : 2019-10-15 02:32:31
             */
            Thread.sleep(6000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " ------schedule.synchronization.test1  使用完毕");
        System.out.println(scheduleSynchronizationTest1 +  " : schedule.synchronization.test1 : Thread.sleep(6000) 之后: "
                + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * synchronization 定时器测试 2 : 5s 一次
     */
    @Async("threadPool")
    @Scheduled(cron = "${schedule.synchronization.test2}")
    public void synchronizationScheduleTest2() {
        System.out.println(scheduleSynchronizationTest2 +  " : schedule.synchronization.test2 : 消耗时间 之前"
                + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        System.out.println(Thread.currentThread().getName() + " ------schedule.synchronization.test2  正在使用");
        //消耗时间 大于 3 s , 大概 10s
        for (int i = 0; i < 100000; i ++) {
            Student student = studentMapper.selectByPrimaryKey(1);
        }
        System.out.println(Thread.currentThread().getName() + " ------schedule.synchronization.test2  使用完毕");
        System.out.println(scheduleSynchronizationTest2 +  " : schedule.synchronization.test2 : 消耗时间 之后"
                + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * synchronization 定时器测试 3 : 8s 一次
     */
    @Scheduled(cron = "${schedule.synchronization.test3}")
    public void synchronizationScheduleTest3() {
        System.out.println(scheduleSynchronizationTest3 +  " : schedule.synchronization.test3 : "
                + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
    }
}
