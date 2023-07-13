package com.example.demo.study.testdemo.javabasic;

import lombok.SneakyThrows;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName: DateTest
 * @Description:
 * @Author: wrl
 * @version: 1.0.0
 * @Date: 2022/7/14 19:22
 */
public class DateTest {
    private static final ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<String> stringThreadLocalTwo = new ThreadLocal<>();

    @SneakyThrows
    public static void main(String[] args) {
        Date now = new Date();
        Date truncateDate = DateUtils.addHours(DateUtils.truncate(now, Calendar.HOUR), -1);
        //true
        System.out.println(truncateDate.before(now));
        System.out.println(DateFormatUtils.format(truncateDate, "yyyy-MM-dd HH:mm:ss"));


        stringThreadLocal.set("111111");
        System.out.println(stringThreadLocal.get() + " : " + Thread.currentThread().getName());
        Thread thread = new Thread(() -> {
            System.out.println(stringThreadLocal.get() + " : " + Thread.currentThread().getName());
            stringThreadLocal.set("2222222");
            System.out.println(stringThreadLocal.get() + " : " + Thread.currentThread().getName());
            stringThreadLocal.remove();
        });
        thread.start();
        Thread.sleep(1000);
        stringThreadLocalTwo.set("333333");
        System.out.println(stringThreadLocalTwo.get() + " : " + Thread.currentThread().getName() + " : stringThreadLocalTwo");
        System.out.println(stringThreadLocal.get() + " : " + Thread.currentThread().getName());
        stringThreadLocal.set("333333");
        System.out.println(stringThreadLocal.get() + " : " + Thread.currentThread().getName());
        stringThreadLocal.remove();
        System.out.println(stringThreadLocal.get() + " : " + Thread.currentThread().getName());

    }
}
