package com.example.demo.study.testdemo.javabasic;

import com.example.demo.model.StudyUser;
import org.apache.commons.io.FileUtils;

import java.io.*;
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

    public static void main(String[] args) throws Exception {
        SerializeTest test = new SerializeTest();
        StudyUser studyUser = test.createStudyUser();
        test.serializeObject(studyUser, FILE_NAME);
        System.out.println("文件长度: 1 : " + new File(FILE_NAME).length());
        test.serializeObject(studyUser, FILE_NAME);
        System.out.println("文件长度: 2 : " + new File(FILE_NAME).length());
        StudyUser studyUser1 = test.deserializationObject(StudyUser.class, FILE_NAME);
        StudyUser studyUser2 = test.deserializationObject(StudyUser.class, FILE_NAME);
        System.out.println("studyUser : " + studyUser.hashCode());
        System.out.println("studyUser : " + studyUser.getCreateTime());
        System.out.println("studyUser1 : " + studyUser1.hashCode());

        //createTime 用 transient 修饰, 不会被序列化和反序列化
        System.out.println("studyUser1 : " + studyUser1.getCreateTime());
        System.out.println("studyUser2 : " + studyUser2.hashCode());
        System.out.println("studyUser2 : " + studyUser2.getCreateTime());
        System.out.println("studyUser.equals(studyUser1) : " + studyUser.equals(studyUser1));
        System.out.println("studyUser1.equals(studyUser2) : " + studyUser1.equals(studyUser2));
        FileUtils.deleteQuietly(new File(FILE_NAME));
    }

    /**
     *  序列化实体对象
     * @param object    实体类
     * @param fileName  文件名
     */
    public void serializeObject(Object object, String fileName) throws IOException {
        //文件不存在 会自动创建
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        outputStream.writeObject(object);
        outputStream.flush();
        //io 流 关闭, 第二次序列化时 文件 会重新 覆盖
        outputStream.close();
    }

    /**
     * 反序列化
     * @param fileName  文件名
     */
    public <T> T deserializationObject(Class<T> clazz, String fileName) throws Exception {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));
        T object = (T) inputStream.readObject();
        inputStream.close();
        return object;
    }

    /**
     * 创建 StudyUser
     * @return  StudyUser
     */
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
