package com.example.demo.mytest;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.demo.DemoMystudyApplication;
import com.example.demo.utils.SpringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * @ClassName: DruidPoolTest
 * @Description:    druid 数据库连接池 测试
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/12/16 01:34
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {DemoMystudyApplication.class})
public class DruidPoolTest {

    @Test
    public void poolPropertyTest() {
        Map<String, DruidDataSource> druidDataSourceMap = SpringUtils.getBeansByType(DruidDataSource.class);
        for (Map.Entry<String, DruidDataSource> stringDruidDataSourceEntry : druidDataSourceMap.entrySet()) {
            System.out.println(stringDruidDataSourceEntry.getKey());
        }
        DruidDataSource primaryDataSource = druidDataSourceMap.get("primaryDataSource");
        DruidDataSource secondaryDataSource = druidDataSourceMap.get("secondaryDataSource");
        System.out.println(primaryDataSource);
        System.out.println(secondaryDataSource);
        //每个 数据源 的配置参数可能不一样 (根据自己配置)
        System.out.println("primaryDataSource.getMaxActive() " + primaryDataSource.getMaxActive());
        System.out.println("secondaryDataSource.getMaxActive() " + secondaryDataSource.getMaxActive());
    }
}
