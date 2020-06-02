package com.example.demo.study.testdemo.javabasic;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * @ClassName: ranDomNumberTest
 * @Description:    随机数生成测试
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/12/25 01:13
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class RanDomNumberTest {
    public static void main(String[] args) {
        /**
         * Math.random（）一随机数 (随机数，采用的是类似于统计学的随机数生成规则，其输出结果很容易预测，
         *                          因此可能导致被攻击者击中。
         *                          而真随机数，采用的是类似于密码学的随机数生成规则，
         *                          其输出结果较难预测，若想要预防被攻击者攻击，
         *                          最好做到使攻击者根本无法，或不可能鉴别生成的随机值和真正的随机值。)
         * java.util.Random 伪随机数（线性同余法生成）
         * java.security.SecureRandom   真随机数
         */
        RanDomNumberTest test = new RanDomNumberTest();
        int times = 10;
        int seed = 10;
        test.mathRandomTest(times);
        test.utilRandom(times, seed);
        test.secureRandomTest(times, "SHA1PRNG", null);
        test.secureRandomTest(times, "SHA1PRNG", "SUN");
        //test.secureRandomTest(times, "DES", null);
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed("qwert".getBytes());
        //12968616
        //153317398
        //1644191916
        System.out.println(secureRandom.nextInt());
        System.out.println(secureRandom.nextInt());
        System.out.println(secureRandom.nextInt());
        System.out.println("------secureRandom1---------");
        SecureRandom secureRandom1 = new SecureRandom();
        secureRandom1.setSeed("qwert".getBytes());
        //12968616
        //153317398
        //1644191916
        System.out.println(secureRandom1.nextInt());
        System.out.println(secureRandom1.nextInt());
        System.out.println(secureRandom1.nextInt());

    }


    /**
     * Math.random() 生成随机数测试
     * @param times 生成次数
     */
    private void mathRandomTest(int times) {
        for (int i = 0; i < times; i++) {
            System.out.println("Math.random() : 第 " + i + " 次 : " + Math.random());
        }
    }

    /**
     *  Random java.util 生成随机数测试
     *      测试结构 : 当 seed(种子) 一定时, 每次 生成的 随机数的值和顺序一定的.
     * @param times 生成的次数
     * @param seed  Random的种子
     */
    private void utilRandom(int times, int seed) {
        Random random = new Random(seed);
        for (int i = 0; i < times; i++) {
            System.out.println("random.nextDouble : 第 " + i + " 次 : 种子 : " + seed + " : " + random.nextDouble());
        }
    }

    /**
     * SecureRandom 生成随机数测试
     * @param times 次数
     * @param algorithmicName   创建SecureRandom的 算法名称
     * @param provider  创建SecureRandom的 提供者
     */
    private void secureRandomTest(int times, String algorithmicName, String provider) {
        SecureRandom random = null;
        try {
            if (StringUtils.isNotBlank(provider)) {
                random = SecureRandom.getInstance(algorithmicName, provider);
            } else {
                random = SecureRandom.getInstance(algorithmicName);
            }
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            e.printStackTrace();
            System.out.println("获取SecureRandom实例失败, 请检查参数是否合格 : " + ExceptionUtils.getMessage(e));
        }
        if (ObjectUtils.isEmpty(random)) {
            random = new SecureRandom();
        }
        random.setSeed("10".getBytes());
        for (int i = 0; i < times; i++) {
                //random.nextDouble() : 生成10位数内的随机数
                //random.nextDouble(100) : 生成0~99的随机数
                System.out.println("random.nextInt : 第 " + i + " 次 : " + random.nextInt());
            }
    }


}
