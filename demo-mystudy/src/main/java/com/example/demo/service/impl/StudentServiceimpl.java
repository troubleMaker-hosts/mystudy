package com.example.demo.service.impl;

import com.example.demo.dao.primary.StudentMapper;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @ClassName: StudentServiceimpl service层实现
 * @Description: Student  service层实现
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/04/06 05:52
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
@Service
public class StudentServiceimpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 局部变量 测试
     */
    private List<Integer> integers;

    /**
     *  插入数据
     * @param student    记录的数据
     * @return  插入数据结果
     */
    @Override
    public Integer insert(Student student) {
        Integer i = studentMapper.insertSelective(student);
        System.out.println(i + " : ------------ service 查看 返回值");
        return i;
    }

    /**
     * 根据条件(name, age, sex) 模糊 查询 student
     * @param name  姓名
     * @param age   年龄
     * @param sex   性别
     * @return  sutdents
     */
    @Override
    public List<Student> findStudentByCondition(String name, Integer age, String sex) {
        //初始化 放在 方法里, 否则 并发情况下 integers 会有 多个请求的值
        integers = new ArrayList<>();
        try {
            integers.add(age);
            System.out.println("integers 局部变量测试 : " + integers);
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        integers.clear();
        return studentMapper.findStudentByCondition(name, age, sex);
    }

    /**
     *  根据主键删除
     * @param id 主键id
     * @return  删除是否成功(1: 成功, 0 失败)
     */
    @Override
    public int delete(Integer id) {
        return studentMapper.deleteByPrimaryKey(id);
    }

    /**
     *  根据name 字段 修改 student
     * @param student 记录的数据
     * @return  修改是否成功(1: 成功, 0 失败)
     */
    @Override
    public Integer updateByName(Student student) {
        return studentMapper.updateByName(student);
    }
}
