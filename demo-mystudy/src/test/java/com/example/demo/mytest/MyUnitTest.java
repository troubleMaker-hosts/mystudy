package com.example.demo.mytest;

import com.example.demo.DemoMystudyApplication;
import com.example.demo.dao.primary.AccountNumberMapper;
import com.example.demo.dao.primary.StudentMapper;
import com.example.demo.model.AccountNumber;
import com.example.demo.utils.EncryptUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName: MyUnitTest
 * @Description: 单元测试
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/09/17 01:31
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {DemoMystudyApplication.class})
public class MyUnitTest {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private AccountNumberMapper accountNumberMapper;


    @Test
    public void test() {
        System.out.println(studentMapper.selectByNameTest("student", "test12"));
    }

    @Test
    public void insertAccountNumber() {
        AccountNumber accountNumber = getAccopuntNumber();
        System.out.println(accountNumber.getUserPwd());
        System.out.println(accountNumber.getUserPwd().length());
        System.out.println(accountNumber);
        accountNumberMapper.insertSelective(accountNumber);
    }

    @Test
    public void findAccountNumber() {
        //List<AccountNumber> accountNumbers = accountNumberMapper.findBySystematicName("华");
        List<AccountNumber> accountNumbers = accountNumberMapper.findAll();
        for (AccountNumber accountNumber : accountNumbers) {
            System.out.println(accountNumber.toString());
            System.out.println("账号 : " + accountNumber.getAccountNumber()
                    + " ;用户名 : " + accountNumber.getUserName()
                    + " ;密码 : " + EncryptUtils.decryptRSAByPriKey(accountNumber.getUserPwd(), EncryptUtils.getPrivateKey(EncryptUtils.RSA)));
        }
    }

    @Test
    public void updateAccountNumberPwd() {
        Integer id = 0;
        AccountNumber accountNumber = accountNumberMapper.selectByPrimaryKey(id);
        accountNumber.setUserPwd(EncryptUtils.encryptRSAByPubKey("xxx", EncryptUtils.getPublicKey(EncryptUtils.RSA)));
        accountNumberMapper.updateByPrimaryKeySelective(accountNumber);
        accountNumber = accountNumberMapper.selectByPrimaryKey(id);
        System.out.println(accountNumber.toString());
        System.out.println("账号 : " + accountNumber.getAccountNumber()
                    + " ;用户名 : " + accountNumber.getUserName()
                    + " ;密码 : " + EncryptUtils.decryptRSAByPriKey(accountNumber.getUserPwd(), EncryptUtils.getPrivateKey(EncryptUtils.RSA)));

    }

    /**
     * 获取 AccopuntNumber
     * @return AccopuntNumber
     */
    private AccountNumber getAccopuntNumber() {
        AccountNumber accountNumber = new AccountNumber();
        accountNumber.setSystematicName("xxx");
        accountNumber.setAccountNumber("xxx");
        accountNumber.setUserName("xxx");
        accountNumber.setUserPwd(EncryptUtils.encryptRSAByPubKey("xxx", EncryptUtils.getPublicKey(EncryptUtils.RSA)));
        //accountNumber.setEmail("xxx");
        accountNumber.setRemarks("xxx");
        accountNumber.setCreateTime(LocalDateTime.now());
        accountNumber.setUpdateTime(LocalDateTime.now());
        accountNumber.setIsValid(1);
        return accountNumber;
    }
}
