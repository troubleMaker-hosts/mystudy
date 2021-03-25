package com.example.demo.mytest;

import com.example.demo.DemoMystudyApplication;
import com.example.demo.dao.secondary.EmployeesMapper;
import com.example.demo.model.Employees;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @ClassName: PageHelperTest
 * @Description: 分页插件 test
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2021/03/12 01:57
 * @Copyright: Copyright(c)2021 kk All Rights Reserved
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {DemoMystudyApplication.class})
public class PageHelperTest {
    @Autowired
    private EmployeesMapper employeesMapper;

    @Test
    public void pageHelperTest() {
        PageInfo<Employees> pageInfo = pageSearch(1, 5);
        pageInfo.getList().forEach(employees -> System.out.println(employees));
    }

    /**
     * 分页查询
     *
     * @param pageNum  页码
     * @param pageSize 每页记录数
     * @return PageInfo<Employees>
     */
    private PageInfo<Employees> pageSearch(int pageNum, int pageSize) {
        PageHelper.orderBy("SALARY desc, FIRST_NAME");
        PageHelper.startPage(pageNum, pageSize);
        List<Employees> employeesList = employeesMapper.selectByCondition(new Employees());
        return  new PageInfo<>(employeesList);
    }

}
