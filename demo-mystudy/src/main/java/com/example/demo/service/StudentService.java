package com.example.demo.service;

import com.example.demo.model.Student;

import java.util.List;

/**
 * @ClassName: StudentService
 * @Description: StudentService 层接口约束
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/11/19 01:14
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public interface StudentService {
    /**
     *  插入数据
     * @param student    记录的数据
     * @return  插入数据结果
     */
    Integer insert(Student student);

    /**
     * 根据条件(name, age, sex) 模糊 查询 student
     * @param name  姓名
     * @param age   年龄
     * @param sex   性别
     * @return  sutdents
     */
    List<Student> findStudentByCondition(String name, Integer age, String sex);

    /**
     *  根据主键删除
     * @param id 主键id
     * @return  删除是否成功(1: 成功, 0 失败)
     */
    int delete(Integer id);
}
