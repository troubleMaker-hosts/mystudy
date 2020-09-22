package com.example.demo.study.testdemo.javabasic;

import com.example.demo.model.StudyUser;

import java.time.LocalDateTime;

/**
 * @ClassName: SerializeTest
 * @Description:    序列化和反序列化 测试
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/09/23 03:27
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
public class SerializeTest {
    /**
     * 序列胡 输出的 文件名
     */
    private static final String FILE_NAME = "studyUser.out";

    public static void main(String[] args) {
        SerializeTest test = new SerializeTest();
        StudyUser studyUser = test.createStudyUser();
        test.serializeObject(studyUser, FILE_NAME);
        test.deserializationObject(FILE_NAME);
    }

    /**
     *  序列化实体对象
     * @param object    实体类
     * @param fileName  文件名
     */
    public void serializeObject(Object object, String fileName) {

    }

    /**
     * 反序列化
     * @param fileName  文件名
     */
    public void deserializationObject(String fileName) {

    }

    private StudyUser createStudyUser() {
        StudyUser studyUser = new StudyUser();
        studyUser.setUserId(4);
        studyUser.setUserName("serializeTest");
        studyUser.setPassword("pwd");
        studyUser.setPhone("phone");
        studyUser.setCreateUser("cu");
        studyUser.setCreateTime(LocalDateTime.now());
        studyUser.setUpdateUser("uu");
        studyUser.setUpdateTime(LocalDateTime.now());
        studyUser.setIsValid(1);
        studyUser.setRemarks("无");
        return studyUser;
    }
}
