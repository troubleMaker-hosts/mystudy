package com.example.demo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: Student
 * @Description: Student 学生 实体类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/11/18 04:06
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class Student extends RequestEntity implements Cloneable {

    /**
     * clone(深拷贝 和 浅拷贝) Test
     */
    private StudyUser studyUser;

    /**
     * mapstruct 泛型 转换 测试
     */
    private List<String> testGen;

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private String sex;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 状态(Y: 有效, N: 无效)
     */
    private String status;

    /**
     * 深拷贝 和 浅拷贝
     * @return  拷贝后的值
     * @throws CloneNotSupportedException   异常
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        Student student = (Student) super.clone();
        student.studyUser = (StudyUser) studyUser.clone();

        //深拷贝
        return student;

        //浅拷贝
        //return super.clone();
    }
}