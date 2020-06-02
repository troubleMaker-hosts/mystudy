package com.example.demo.dao;

import com.example.demo.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: StudentMapper
 * @Description:    Student dao 层
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2018/11/18 04:06
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
@Mapper
public interface StudentMapper {
    /**
     *  根据主键删除
     * @param id 主键id
     * @return  删除是否成功(1: 成功, 0 失败)
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入记录 Student
     * @param record Student实体类
     * @return  插入是否成功
     */
    Integer insertSelective(Student record);

    /**
     * 根据主键查询 Student
     * @param id 主键id
     * @return  Student
     */
    Student selectByPrimaryKey(Integer id);

    /**
     * 根据主键 修改 student
     * @param record    Student
     * @return  是否修改成功
     */
    int updateByPrimaryKeySelective(Student record);

    /**
     * 根据条件(name, age, sex) 模糊 查询 student
     * @param name  姓名
     * @param age   年龄
     * @param sex   性别
     * @return  sutdents
     */
    List<Student> findStudentByCondition(@Param("name") String name, @Param("age") Integer age, @Param("sex") String sex);

    /**
     * 根据name 模糊 查询 tableName
     * 此处测试 tableName 为 student
     * @param tableName  表名
     * @param name  姓名
     * @return  sutdents
     */
    List<Student> selectByNameTest(@Param("tableName") String tableName, @Param("name") String name);
}