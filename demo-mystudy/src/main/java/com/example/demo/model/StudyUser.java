package com.example.demo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: StudyUser
 * @Description: StudyUser 实体类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/11/18 04:06
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class StudyUser<T> extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 测试属性 (有默认值)
     */
    private int testAttribute = 999;

    /**
     * 测试共有属性
     */
    public int testPubAttribute;

    /**
     * 泛型测试
     */
    private List<T> testGen;

    /**
     * 主键id
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 默认构造器
     */
    public StudyUser(){
        System.out.println("这是 StudyUser 的 默认构造器------------");
    }

    /**
     * 公共有参 构造器
     * @param userId    主键id
     * @param userName  用户名
     * @param password  密码
     * @param phone 手机号码
     */
    public StudyUser(Integer userId, String userName, String password, String phone) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        System.out.println("这是 StudyUser 的 构造器 : (Integer userId, String userName, String password, String phone) ------------");
    }

    /**
     * 私有构造器
     * @param userName 用户名
     * @param password  密码
     */
    private StudyUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
        System.out.println("这是 StudyUser 的 构造器 : (String userName, String password) ------------");
    }

    /**
     * 反射 的 私有方法 测试
     * @param parameter 参数(测试)
     */
    private void privateMethodTest(String parameter) {
        System.out.println("反射 的 私有方法 测试 + parameter : " + parameter);
    }

    /**
     * 反射 的 共有方法 测试（public）
     * @param parameter 参数(测试)
     */
    public void publicMethodTest(String parameter) {
        System.out.println("反射 的 共有方法 测试 + parameter : " + parameter + (this.testAttribute));
    }

    /**
     * 反射 的 共有方法 测试（public）
     */
    public void publicMethodTest() {
        System.out.println("反射 的 共有方法 测试  重载的 无参数 公共方法 ------ 重载 无参数" + this.testAttribute);
    }
}