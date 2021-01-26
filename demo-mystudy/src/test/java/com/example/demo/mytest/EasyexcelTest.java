package com.example.demo.mytest;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.example.demo.DemoMystudyApplication;
import com.example.demo.config.EasyExcelListener;
import com.example.demo.dao.secondary.EmployeesMapper;
import com.example.demo.model.Employees;
import com.example.demo.utils.FileOperateUtils;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: EasyexcelTest
 * @Description: Easyexcel 测试类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/06/17 17:04
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {DemoMystudyApplication.class})
public class EasyexcelTest {
    @Autowired
    private EmployeesMapper employeesMapper;

    /**
     * easyExcel 导出 test (单 和 多 sheet)
     */
    @Test
    public void easyExcelExportTest() throws RuntimeException {
        List<Employees> employeesList = getEmployees(new Employees());
        String pathName = getDefaultPath("employees", 0);
        System.out.println(new Date());
        //不使用 model.class, 没有表头
        //注意 : 当文件 已经存在时 会报异常

        //此种方法 只能生成 单 sheet, 后面的 会覆盖 前面的.
        //原因 : .doWrite() 方法 中 执行了 finish() 方法
        //EasyExcel.write(pathName, Employees.class).sheet(1,"员工列表").doWrite(employeesList);
        //EasyExcel.write(pathName, Employees.class).sheet(2, "员工列表2").doWrite(employeesList.subList(0, 10));

        //生成 多sheet 的excel
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(pathName, Employees.class).build();
            excelWriter.write(employeesList, EasyExcel.write(pathName, Employees.class).sheet(0, "员工列表").build());
            excelWriter.write(employeesList.subList(0, 10), EasyExcel.write(pathName, Employees.class).sheet(1, "员工列表2").build());
        } catch (Exception e) {
            throw new RuntimeException("easyExcel 导出数据失败 : " + Employees.class + ", pathName : " + pathName + " exceptionInfo : " + e.getMessage());
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
        System.out.println(new Date());
    }

    /**
     * easyExcel 导入 test (单 和 多 sheet)
     */
    @Test
    public void easytExcelImportTest() {
        String pathName = getDefaultPath("employees", 1);
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(pathName, Employees.class, new EasyExcelListener(employeesMapper)).build();
            //不会读 excel 的 第一行
            excelReader.read(EasyExcel.readSheet(1).build());
            System.out.println(excelReader);
        } catch (Exception e) {
            throw new RuntimeException("easyExcel 导入数据失败 : " + Employees.class + ", pathName : " + pathName + " exceptionInfo : " + e.getMessage());
        } finally {
            if (excelReader != null) {
                excelReader.finish();
            }
        }
    }

    /**
     * mergeIntoTest
     * 批量导入, 有则修改, 无则添加
     */
    @Test
    public void mergeIntoTest() {
        Employees  employees = new Employees();
        employees.setManagerId(205);
        int i = 1;
       List<Employees> employeesList = getEmployees(employees).subList(0, 2);
        for (Employees emp : employeesList) {
            emp.setEmployeeId(emp.getEmployeeId() + i);
            emp.setFirstName(emp.getFirstName() + i);
            //hashCode
            System.out.println(emp.hashCode());
        }
       employeesList.forEach(emp-> System.out.println(emp));
       employeesMapper.mergeInto(employeesList);
    }

    /**
     * 获取 List Employees (记录数为 size * 2^i ; i = 10, size = 100 为 10W 级别)
     *  @param employees 查询条件
     * @return List<Employees>
     */
    public List<Employees> getEmployees(Employees employees) {
        List<Employees> employeesList = employeesMapper.selectByCondition(employees);
        System.out.println(employeesList.size());
        //记录数为 size * 2^i ; i = 10, size = 100 为 10W 级别
        for (int i = 0; i < 10; i++) {
            //此处 addAll 只是把 employeesList 的 数量 增加了,   Employees对象的数量 没有增加, 相同的 对象 指向同一个地址
            employeesList.addAll(employeesList);
        }
        System.out.println(employeesList.size());
        return employeesList;
    }

    /**
     * 获取 默认路径(桌面路径), 并判断 该文件是否存在, 直到生成 不存在的文件名 为止
     *
     * @param fileName 文件名
     * @param type     类型(0 : 导出, 1 : 导入)
     * @return 包含文件名 的 路径
     */
    public String getDefaultPath(String fileName, int type) {
        //获取 本地 桌面路径
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File home = fsv.getHomeDirectory();
        String deskTopPath = home.getPath();
        //后缀名
        String suffix = ".xlsx";
        String pathName = deskTopPath + "/" + fileName + suffix;
        if (type == 0) {
            for (int i = 1; ; i++) {
                if (FileOperateUtils.isExist(pathName)) {
                    pathName = deskTopPath + "/" + fileName + i + suffix;
                } else {
                    break;
                }
            }
        }
        return pathName;
    }
}
