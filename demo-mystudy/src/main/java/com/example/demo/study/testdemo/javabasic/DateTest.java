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

    @SneakyThrows
    public static void main(String[] args) {
        Date now = new Date();
        Date truncateDate = DateUtils.addHours(DateUtils.truncate(now, Calendar.HOUR), -1);
        //true
        System.out.println(truncateDate.before(now));
        System.out.println(DateFormatUtils.format(truncateDate, "yyyy-MM-dd HH:mm:ss"));


        stringThreadLocal.set("12345");
        System.out.println(stringThreadLocal.get() + " : " + Thread.currentThread().getName());
        Thread thread = new Thread(() -> System.out.println(stringThreadLocal.get() + " : " + Thread.currentThread().getName()));
        thread.start();
        Thread.sleep(1000);
        System.out.println(stringThreadLocal.get() + " : " + Thread.currentThread().getName());
    }
}
