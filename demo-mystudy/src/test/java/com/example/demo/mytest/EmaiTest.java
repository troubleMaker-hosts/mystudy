package com.example.demo.mytest;

import com.example.demo.DemoMystudyApplication;
import com.example.demo.config.EmailSendConfig;
import com.example.demo.vo.MailInfoVo;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName: EmaiTest
 * @Description:    邮件测试
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2021/03/05 11:06
 * @Copyright: Copyright(c)2021 kk All Rights Reserved
 */
@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {DemoMystudyApplication.class})
public class EmaiTest {
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private EmailSendConfig emailSendConfig;

    /**
     * 邮件发送test
     */
    @Test
    public void mailSendTest() {
        MailInfoVo mailInfoVo = emailSendConfig.sendMail(getMailInfoVo());
        System.out.println(mailInfoVo.toString());
    }

    /**
     * 获取 MailInfoVo
     * @return  MailInfoVo
     */
    private MailInfoVo getMailInfoVo() {
        File textFile = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("static/testFile.txt")).getPath());
        File imgFile = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("static/temp/background.jpg")).getPath());
        List<MultipartFile> multipartFileList = new ArrayList<>();
        try {
            FileInputStream input = new FileInputStream(textFile);
            MultipartFile textMultipartFile = new MockMultipartFile("textFile", textFile.getName(), null, IOUtils.toByteArray(input));
            FileInputStream imageInputStream = new FileInputStream(imgFile);
            MultipartFile imageMultipartFile = new MockMultipartFile("imgFile", imgFile.getName(), null, IOUtils.toByteArray(imageInputStream));
            multipartFileList.add(textMultipartFile);
            multipartFileList.add(imageMultipartFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        MailInfoVo mailInfoVo = MailInfoVo.builder()
                .from(from)
                .to("15116251140@163.com")
                .subject("邮件主题(标题)")
                .text("邮件内容(正文)")
                //.carbonCopy("1321171422@qq.com")
                .blindCarbonCopy("1321171422@qq.com")
                .multipartFiles(multipartFileList)
                .build();
        return mailInfoVo;
    }
}
