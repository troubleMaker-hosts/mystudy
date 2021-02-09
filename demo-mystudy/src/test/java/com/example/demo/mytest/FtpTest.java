package com.example.demo.mytest;

import com.baidu.fsg.uid.UidGenerator;
import com.example.demo.DemoMystudyApplication;
import com.example.demo.annotation.ResourceAnnotation;
import com.example.demo.config.FtpClientPoolFactory;
import com.example.demo.config.FtpUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.Objects;

/**
 * @ClassName: FtpTest
 * @Description:    FTP 测试
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/09/07 03:55
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
@ResourceAnnotation("resourceAnnotationType")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {DemoMystudyApplication.class})
public class FtpTest {

    @Autowired
    private FtpClientPoolFactory ftpClientPoolFactory;
    @Autowired
    private UidGenerator uidGenerator;

    @Test
    public void test() throws Exception {
        System.out.println("FTP 测试");
        FtpUtil ftpUtil1 = ftpClientPoolFactory.create();
        FtpUtil ftpUtil2 = ftpClientPoolFactory.create();
        //FtpUtil ftpUtil3 = ftpClientPoolFactory.createByGenericObjectPool();
        //FtpUtil ftpUtil4 = ftpClientPoolFactory.createByGenericObjectPool();
        //FtpUtil ftpUtil5 = ftpClientPoolFactory.createByGenericObjectPool();
        //FtpUtil ftpUtil6 = ftpClientPoolFactory.createByGenericObjectPool();
        //FtpUtil ftpUtil7 = ftpClientPoolFactory.createByGenericObjectPool();
        FtpUtil ftpUtil8 = ftpClientPoolFactory.createByGenericObjectPool();
        //FtpUtil ftpUtil9 = ftpClientPoolFactory.createByGenericObjectPool();
        //FtpUtil ftpUtil10 = ftpClientPoolFactory.createByGenericObjectPool();
        //FtpUtil ftpUtil11 = ftpClientPoolFactory.createByGenericObjectPool();
        //FtpUtil ftpUtil12 = ftpClientPoolFactory.createByGenericObjectPool();
        System.out.println("ftpUtil1 : " + ftpUtil1);
        System.out.println("ftpUtil2 : " + ftpUtil2);
        //System.out.println("ftpUtil3 : " + ftpUtil3);
        //System.out.println("ftpUtil4 : " + ftpUtil4);
        //System.out.println("ftpUtil5 : " + ftpUtil5);
        //System.out.println("ftpUtil6 : " + ftpUtil6);
        //System.out.println("ftpUtil7 : " + ftpUtil7);
        System.out.println("ftpUtil8 : " + ftpUtil8);
        //System.out.println("ftpUtil9 : " + ftpUtil9);
        //System.out.println("ftpUtil10 : " + ftpUtil10);
        //System.out.println("ftpUtil11 : " + ftpUtil11);
        //System.out.println("ftpUtil12 : " + ftpUtil12);
        ftpUtil8.connect();
        ftpUtil8.makeDirs("picture/java");
        //ftpUtil8.changeWorkingDirectory("picture/java");
        //输出的 printWorkingDirectory() 是以 虚拟用户的home目录为根目录的
        System.out.println(ftpUtil8.printWorkingDirectory());
        System.out.println(ftpUtil8.changeWorkingDirectory("/home/vsftp/devftp"));
        System.out.println(ftpUtil8.printWorkingDirectory());
        ftpUtil8.upload(uidGenerator.getUID() + ".txt", new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("static/testFile.txt")).getPath()));
        ftpUtil8.disconnect();
    }

    @Test
    public void ftpConnectTest() throws Exception {
        //创建一个FtpClient对象
        FTPClient ftpClient = new  FTPClient();
        //创建ftp连接
        ftpClient.connect("192.168.1.107",21);
        System.out.println("ftpClient : " + ftpClient.isConnected());
        ftpClient.disconnect();

        FtpUtil ftpUtil = FtpUtil.createFtpClient("192.168.1.107", 21, "kaifatest", "777777", "/");
        ftpUtil.connect();
        ftpUtil.upload("testFileUpload.txt", new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("static/testFile.txt")).getPath()));
        System.out.println("ftpUtil : " + ftpUtil.isConnected());
        ftpUtil.disconnect();
    }
}
