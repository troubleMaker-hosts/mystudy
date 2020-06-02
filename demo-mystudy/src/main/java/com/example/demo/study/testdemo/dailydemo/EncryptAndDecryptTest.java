package com.example.demo.study.testdemo.dailydemo;

import com.example.demo.utils.EncryptUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @ClassName: encodeAndDecodeTest
 * @Description:    加解密test
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/12/24 02:24
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class EncryptAndDecryptTest {
    public static void main(String[] args) {
        EncryptAndDecryptTest test = new EncryptAndDecryptTest();
        String content = "base64加密test";
        //base64加密(编码)
        String base64En = test.base64EncryptTest(content);
        System.out.println("base64加密 : " + base64En);
        //base64解密(解码)
        System.out.println("base64解密 : " + test.base64DecryptTest(base64En));

        String desKey = "2343";
        String desEncryt = EncryptUtils.encryptDES(content, desKey);
        System.out.println("DES加密 : " + desEncryt);
        System.out.println("DES解密 : " + EncryptUtils.decryptDES(desEncryt, desKey));

        String des3Encryt = EncryptUtils.encryptDESede(content, desKey);
        System.out.println("3DES加密 : " + des3Encryt);
        System.out.println("3DES解密 : " + EncryptUtils.decryptDESede(des3Encryt, desKey));

        String aesEncryt = EncryptUtils.encryptAES(content);
        System.out.println("AES加密 : " + aesEncryt);
        System.out.println("AES解密 : " + EncryptUtils.decryptAES(aesEncryt));

        //获取 RSA 密钥
        //Map<String, String> keyMap = EncryptUtils.getAsymmetricKey("RSA");
        //for (Map.Entry<String, String> stringEntry : keyMap.entrySet()) {
        //    System.out.println("key : " + stringEntry.getKey() + " ; value : " + stringEntry.getValue());
        //    System.out.println(stringEntry.getValue().length());
        //}
        //String pubRsaCentent = EncryptUtils.encryptRSAByPubKey(content, keyMap.get(EncryptUtils.PUBLIC_KEY));
        //System.out.println("RSA 公钥 加密 : " + pubRsaCentent);
        //System.out.println("RSA 私钥 解密 : " + EncryptUtils.decryptRSAByPriKey(pubRsaCentent, keyMap.get(EncryptUtils.PRIVATE_KEY)));
        //String priRsaCentent = EncryptUtils.encryptRSAByPriKey(content, keyMap.get(EncryptUtils.PRIVATE_KEY));
        //System.out.println("RSA 私钥 加密 : " + priRsaCentent);
        //System.out.println("RSA 公钥 解密 : " + EncryptUtils.decryptRSAByPubKey(priRsaCentent, keyMap.get(EncryptUtils.PUBLIC_KEY)));


        //获取 ECC 密钥
        //Map<String, String> keyMap = EncryptUtils.getAsymmetricKey("EC");
        //for (Map.Entry<String, String> stringEntry : keyMap.entrySet()) {
        //    System.out.println("key : " + stringEntry.getKey() + " ; value : " + stringEntry.getValue());
        //    System.out.println(stringEntry.getValue().length());
        //}

        //DSA 数字签名
        Map<String, String> keyMap = EncryptUtils.getAsymmetricKey("DSA");
        for (Map.Entry<String, String> stringEntry : keyMap.entrySet()) {
            System.out.println("key : " + stringEntry.getKey() + " ; value : " + stringEntry.getValue());
            System.out.println(stringEntry.getValue().length());
        }
        String sign = EncryptUtils.encryptDSA(content, keyMap.get(EncryptUtils.PRIVATE_KEY));
        System.out.println("DSA 私钥 加密(签名) : " + sign);
        System.out.println("DSA 公钥 校验 : " + EncryptUtils.checkoutDSA(content, keyMap.get(EncryptUtils.PUBLIC_KEY), sign));
    }

    /**
     * base64编码(加密)
     * @param content   被编码的字符串
     * @return 加密之后的字符串
     */
    public String base64EncryptTest(String content) {
        content = EncryptUtils.encryptBase64(content.getBytes(StandardCharsets.UTF_8));
        return content;
    }

    /**
     * base64解码(解密)
     * @param content   被编码的字符串
     * @return 解密之后的字符串
     */
    public String base64DecryptTest(String content) {
        content = EncryptUtils.decryptBase64(content);
        return content;
    }

}
