package com.example.demo.utils;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.DigestUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName: EncryptUtils
 * @Description: 加密工具类 (对称加密DES, 3DES, AES，非对称加密: RSA, ECC(EC), 消息摘要: MD5, 数字签名: DSA), base64编码
 *               编码格式为:UTF-8
 *               注意 : 本 类 只 支持 EC 获取公钥和私钥, 暂不支持 ECC 加解密
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/12/24 01:33
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class EncryptUtils {
    /**
     * DES(加密算法名称)
     */
    public static final String DES = "DES";

    /**
     * DESede(3DES)
     */
    public static final String DESEDE = "DESede";

    /**
     * AES(对称)
     */
    public static final String AES = "AES";

    /**
     * RSA(非对称)
     */
    public static final String RSA = "RSA";

    /**
     * EC (非对称): java ECC算法 密钥的生成与解析
     * ECC算法在jdk1.5后加入支持，目前仅仅只能完成密钥的生成与解析
     * 本 类 只 支持 EC 获取公钥和私钥, 暂不支持 ECC 加解密
     */
    public static final String EC = "EC";

    /**
     * DSA(数字签名)
     */
    public static final String DSA = "DSA";

    /**
     * 公钥 key
     */
    public static final String PUBLIC_KEY = "PUBLIC_KEY";

    /**
     * 私钥 key
     */
    public static final String PRIVATE_KEY = "PRIVATE_KEY";

    /**
     * 默认编码
     */
    public static final Charset UTF_8 = StandardCharsets.UTF_8;

    /**
     * 32 位(256 bit) 密钥长度(默认 : AES)
     */
    private static final Integer KEY_SIZE_32 = 32;

    /**
     * 512 密钥长度(默认 : DES,3DES(DESede)(32 以上(16的倍数)), ECC)
     */
    private static final Integer KEY_SIZE_512 = 512;

    /**
     * 1024 密钥长度(默认 : RSA(非对称), DSA)
     */
    private static final Integer KEY_SIZE_1024 = 1024;

    /**
     * 默认 密钥(对称加密密钥, 非对称加密密钥种子)
     */
    private static final String DEFAULT_KEY = "20CFCD9050D052C2B008206D244BE2C5";

    /**
     * 默认 创建SecureRandom的 算法名称
     */
    private static final String SECURERANDOM_ALGORITHMIC = "SHA1PRNG";

    /**
     * 日志(log4j)
     */
    private static Logger logger = LogManager.getLogger(EncryptUtils.class);


    /**
     * DES加密
     *
     * @param content 明文
     * @return des加密之后的密文
     */
    public static String encryptDES(String content) {
        return encryptDES(content, null);
    }

    /**
     * DES加密
     *
     * @param content 明文
     * @param key     密钥
     * @return des加密之后的密文
     */
    public static String encryptDES(String content, String key) {
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom sr = SecureRandom.getInstance(SECURERANDOM_ALGORITHMIC);
            //生成密钥
            SecretKey secretKey = generateSecreKey(key, KEY_SIZE_512, DES, DESKeySpec.class);
            // 对称加密 : 执行 Cipher, 获取 密文
            return executeCipher(content, DES, Cipher.ENCRYPT_MODE, secretKey, sr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(DES + "加密失败 : " + content);
        }
        return null;
    }

    /**
     * DES解密
     *
     * @param content 密文
     * @return 解密后的明文
     */
    public static String decryptDES(String content) {
        return decryptDES(content, null);
    }

    /**
     * DES解密
     *
     * @param content 密文
     * @param key     密钥
     * @return 解密后的明文
     */
    public static String decryptDES(String content, String key) {
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom sr = SecureRandom.getInstance(SECURERANDOM_ALGORITHMIC);
            //生成密钥
            SecretKey secretKey = generateSecreKey(key, KEY_SIZE_512, DES, DESKeySpec.class);
            // 对称加密 : 执行 Cipher, 获取 明文
            return executeCipher(content, DES, Cipher.DECRYPT_MODE, secretKey, sr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(DES + "解密失败 : " + content);
        }
        return null;
    }

    /**
     * 3DES(DESede)加密
     *
     * @param content 明文
     * @return 3DES(DESede)加密之后的密文
     */
    public static String encryptDESede(String content) {
        return encryptDESede(content, null);
    }

    /**
     * 3DES(DESede)加密
     *
     * @param content 明文
     * @param key     密钥
     * @return 3DES(DESede)加密之后的密文
     */
    public static String encryptDESede(String content, String key) {
        try {
            //生成密钥
            SecretKey secretKey = generateSecreKey(key, KEY_SIZE_512, DESEDE, DESedeKeySpec.class);
            // 对称加密 : 执行 Cipher, 获取 密文
            return executeCipher(content, DESEDE, Cipher.ENCRYPT_MODE, secretKey);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(DESEDE + "加密失败 : " + content);
        }
        return null;
    }

    /**
     * 3DES(DESede)解密
     *
     * @param content 密文
     * @return 解密后的明文
     */
    public static String decryptDESede(String content) {
        return decryptDESede(content, null);
    }

    /**
     * 3DES(DESede)解密
     *
     * @param content 密文
     * @param key     密钥
     * @return 解密后的明文
     */
    public static String decryptDESede(String content, String key) {
        try {
            //生成密钥
            SecretKey secretKey = generateSecreKey(key, KEY_SIZE_512, DESEDE, DESedeKeySpec.class);
            // 对称加密 : 执行 Cipher, 获取 明文
            return executeCipher(content, DESEDE, Cipher.DECRYPT_MODE, secretKey);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(DESEDE + "解密失败 : " + content);
        }
        return null;
    }

    /**
     * AES 加密
     *
     * @param content 明文
     * @return AES加密之后的密文
     */
    public static String encryptAES(String content) {
        return encryptAES(content, null);
    }

    /**
     * AES 加密
     *
     * @param content 明文
     * @param key     密钥
     * @return AES 加密之后的密文
     */
    public static String encryptAES(String content, String key) {
        try {
            //判断用户是否提供了密钥
            if (StringUtils.isBlank(key)) {
                key = DEFAULT_KEY;
            }
            //生成有效长度的 key
            key = generateValidKey(key, KEY_SIZE_32);
            // 生成密钥
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(UTF_8), AES);
            // 对称加密 : 执行 Cipher, 获取 密文
            return executeCipher(content, AES, Cipher.ENCRYPT_MODE, secretKey);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(AES + "加密失败 : " + content);
        }
        return null;
    }

    /**
     * AES 解密
     *
     * @param content 密文
     * @return 解密后的明文
     */
    public static String decryptAES(String content) {
        return decryptAES(content, null);
    }

    /**
     * AES 解密
     *
     * @param content 密文
     * @param key     密钥
     * @return 解密后的明文
     */
    public static String decryptAES(String content, String key) {
        try {
            //判断是否提供了密钥
            if (StringUtils.isBlank(key)) {
                key = DEFAULT_KEY;
            }
            //生成有效长度的 key
            key = generateValidKey(key, KEY_SIZE_32);
            // 生成密钥
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(UTF_8), AES);
            // 对称加密 : 执行 Cipher, 获取 明文
            return executeCipher(content, AES, Cipher.DECRYPT_MODE, secretKey);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(AES + "解密失败 : " + content);
        }
        return null;
    }

    /**
     * RSA 公钥 加密
     * @param content   明文
     * @param publicKey 公钥
     * @return  密文
     */
    public static String encryptRSAByPubKey(String content, String publicKey) {
        try {
            //公钥
            PublicKey pubKey = generatePublicKey(publicKey, RSA);
            // RSA加密
            return executeCipher(content, RSA, Cipher.ENCRYPT_MODE, pubKey);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("RSA 公钥 加密 失败 : " + ExceptionUtils.getMessage(e));
        }
        return null;
    }

    /**
     * RSA 私钥 解密
     * @param content   密文
     * @param privateKey    私钥
     * @return  明文
     */
    public static String decryptRSAByPriKey(String content, String privateKey) {
        try {
            //私钥
            PrivateKey priKey = generatePrivateKey(privateKey, RSA);
            //RSA解密
            return executeCipher(content, RSA, Cipher.DECRYPT_MODE, priKey);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("RSA 私钥 解密 失败 : " + ExceptionUtils.getMessage(e));
        }
		return null;
    }

    /**
     * RSA 私钥 加密
     * @param content   明文
     * @param privateKey 私钥
     * @return  密文
     */
    public static String encryptRSAByPriKey(String content, String privateKey) {
        try {
            //私钥
            PrivateKey priKey = generatePrivateKey(privateKey, RSA);
            // RSA加密
            return executeCipher(content, RSA, Cipher.ENCRYPT_MODE, priKey);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("RSA 私钥 加密 失败 : " + ExceptionUtils.getMessage(e));
        }
        return null;
    }

    /**
     * RSA 公钥 解密
     * @param content   密文
     * @param publicKey    私钥
     * @return  明文
     */
    public static String decryptRSAByPubKey(String content, String publicKey) {
        try {
            //公钥
            PublicKey pubKey = generatePublicKey(publicKey, RSA);
            //RSA解密
            return executeCipher(content, RSA, Cipher.DECRYPT_MODE, pubKey);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("RSA 公钥 解密 失败 : " + ExceptionUtils.getMessage(e));
        }
		return null;
    }

    /**
     * DSA 私钥 加密 (签名)
     * @param content   明文
     * @param privateKey    私钥
     * @return  密文
     */
    public static String encryptDSA(String content, String privateKey) {
        try {
            Signature signature = Signature.getInstance(DSA);
            //私钥
            PrivateKey priKey = generatePrivateKey(privateKey, DSA);
            signature.initSign(priKey);
            signature.update(content.getBytes(UTF_8));
            return binaryToHex(signature.sign());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * DSA 公钥 校验 签名 是否 正确
     * @param content   明文
     * @param publicKey 公钥
     * @param sign  数字签名(密文)
     * @return  校验 结果
     */
    public static boolean checkoutDSA(String content, String publicKey, String sign) {
        try {
            Signature signature = Signature.getInstance(DSA);
            //私钥
            PublicKey pubKey = generatePublicKey(publicKey, DSA);
            signature.initVerify(pubKey);
            signature.update(content.getBytes(UTF_8));
            return signature.verify(hexToBinary(sign));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Base64编码(加密)
     *
     * @param content 明文(String类型)
     * @return Base64加密之后的密文
     */
    public static String encryptBase64(byte[] content) {
        if (ObjectUtils.isNotEmpty(content)) {
            byte[] encoeContent = Base64.getEncoder().encode(content);
            return new String(encoeContent, UTF_8);
        }
        return null;
    }

    /**
     * Base64编码(加密)
     * 注意 : Base64编码 的长度 不是 明文的 长度
     *
     * @param content 明文(String类型)
     * @return Base64加密之后的密文
     */
    public static String encryptBase64(String content) {
        if (StringUtils.isNotBlank(content)) {
            byte[] encoeContent = Base64.getEncoder().encode(content.getBytes(UTF_8));
            return new String(encoeContent, UTF_8);
        }
        return null;
    }

    /**
     * Base64解码(解密)
     *
     * @param content 密文
     * @return Base64解密之后的明文
     */
    public static String decryptBase64(String content) {
        if (StringUtils.isNotBlank(content)) {
            byte[] encoeContent = Base64.getDecoder().decode(content);
            return new String(encoeContent, UTF_8);
        }
        return null;
    }

    /**
     * MD5摘要
     *
     * @param content 明文
     * @return 密文(32位)
     */
    public static String md5Digest(String content) {
        //32 位 md5
        return DigestUtils.md5DigestAsHex(content.getBytes(UTF_8)).toUpperCase();
    }

    /**
     * 二进制数组转十六进制的字符串
     *
     * @param binBytes 二进制数据
     * @return 十六进制的字符串
     */
    public static String binaryToHex(byte[] binBytes) {
        StringBuilder hexStr = new StringBuilder();
        for (byte binByte : binBytes) {
            if ((binByte & 0xff) < 0x10) {
                hexStr.append("0");
            }
            hexStr.append(Integer.toString(binByte & 0xff, 16));
        }
        //返回全大写字符串
        return hexStr.toString().toUpperCase();
    }

    /**
     * 十六进制的字符串转二进制数组
     *
     * @param hexStr 十六进制字符串
     * @return 二进制byte数组
     */
    public static byte[] hexToBinary(String hexStr) {
        char[] hexChars = hexStr.toCharArray();
        byte[] binBytes = new byte[hexStr.length() / 2];
        for (int i = 0; i < binBytes.length; i++) {
            int index = i * 2;
            String hexChar = "0123456789ABCDEF";
            binBytes[i] = (byte) ((hexChar.indexOf(hexChars[index]) << 4) |
                    hexChar.indexOf(hexChars[index + 1]));
        }
        return binBytes;
    }

    /**
     * 根据 algorithm(非对称加密算法名) 获取 默认长度 的 公钥
     * @param algorithm 非对称加密算法名
     * @return  默认长度的 非对称加密的 公钥
     */
    public static String getPublicKey(String algorithm) {
        return getAsymmetricKey(algorithm).get(PUBLIC_KEY);
    }

     /**
     * 根据 algorithm(非对称加密算法名) 和 种子(seed) 获取 默认长度 的 公钥
     * @param algorithm 非对称加密算法名
     * @param seed SecureRandom 的 种子
     * @return  默认长度的 非对称加密的 公钥
     */
    public static String getPublicKey(String algorithm, String seed) {
        return getAsymmetricKey(algorithm, seed).get(PUBLIC_KEY);
    }

    /**
     * 根据 algorithm(非对称加密算法名) 获取 默认长度 的 公钥
     * @param algorithm 非对称加密算法名
     * @return  默认长度的 非对称加密的 公钥
     */
    public static String getPrivateKey(String algorithm) {
         return getAsymmetricKey(algorithm).get(PRIVATE_KEY);
    }

     /**
     * 根据 algorithm(非对称加密算法名) 和 种子(seed) 获取 默认长度 的 私钥
     * @param algorithm 非对称加密算法名
     * @param seed SecureRandom 的 种子
     * @return  默认长度的 非对称加密的 私钥
     */
    public static String getPrivateKey(String algorithm, String seed) {
        return getAsymmetricKey(algorithm, seed).get(PRIVATE_KEY);
    }

    /**
     * 根据 algorithm(非对称加密算法名) 获取 默认长度 的 公钥 和 私钥
     * @param algorithm 非对称加密算法名
     * @return  默认长度的 非对称加密的 公钥 和 私钥
     */
    public static Map<String, String> getAsymmetricKey(String algorithm) {
        return getAsymmetricKey(algorithm, null);
    }

    /**
     * 根据 algorithm(非对称加密算法名) 和 种子(seed) 获取 默认长度 的 公钥 和 私钥
     * @param algorithm 非对称加密算法名
     * @param seed SecureRandom 的 种子
     * @return  默认长度的 非对称加密的 公钥 和 私钥
     */
    public static Map<String, String> getAsymmetricKey(String algorithm, String seed) {
        if (StringUtils.isBlank(seed)) {
            seed = DEFAULT_KEY;
        }
        if (EC.equals(algorithm)) {
            //ECC 密钥长度 最多 571 bit
            return getAsymmetricKey(algorithm, KEY_SIZE_512, seed);
        }
        return getAsymmetricKey(algorithm, KEY_SIZE_1024, seed);
    }

    /**
     * 根据 algorithm(非对称加密算法名) 和 种子(seed) 获取 指定长度的 公钥 和 私钥
     * @param algorithm 非对称加密算法名
     * @param keySize   密钥长度
     * @param seed  SecureRandom 的 种子
     * @return  指定长度的 非对称加密的 公钥 和 私钥
     */
    private static Map<String, String> getAsymmetricKey(String algorithm, Integer keySize, String seed) {
        Map<String, String> keyMap = new HashMap<>();
        try {
            // KeyPairGenerator类用于生成公钥和私钥对
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(algorithm);
            // 初始化密钥对生成器
            SecureRandom secureRandom = SecureRandom.getInstance(SECURERANDOM_ALGORITHMIC);
            if (StringUtils.isNotBlank(seed)) {
                secureRandom.setSeed(seed.getBytes());
            }
            keyPairGen.initialize(keySize, secureRandom);
            // 生成一个密钥对，保存在keyPair中
            KeyPair keyPair = keyPairGen.generateKeyPair();
            // 得到私钥
            PrivateKey privateKey = keyPair.getPrivate();
            // 得到公钥
            PublicKey publicKey = keyPair.getPublic();
            // 公钥 和 私钥
            keyMap.put(PUBLIC_KEY, binaryToHex(publicKey.getEncoded()));
            keyMap.put(PRIVATE_KEY, binaryToHex(privateKey.getEncoded()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("非对称加密获取指定长度的公钥和私钥失败 : algorithm : " + algorithm + " : " + ExceptionUtils.getMessage(e));
        }
        return keyMap;
    }

    /**
     * 根据 非对称加密算法 获取 公钥
     * @param publicKey     公钥
     * @param algorithm     非对称加密算法名
     * @return  公钥
     * @throws Exception 抛出异常
     */
    private static PublicKey generatePublicKey(String publicKey,  String algorithm) throws Exception {
        //公钥
		byte[] decoded = Objects.requireNonNull(hexToBinary(publicKey));
        return KeyFactory.getInstance(algorithm).generatePublic(new X509EncodedKeySpec(decoded));

    }

    /**
     * 根据 非对称加密算法 获取 私钥
     * @param privateKey    私钥
     * @param algorithm     非对称加密算法名
     * @return  私钥
     * @throws Exception 抛出异常
     */
    private static PrivateKey generatePrivateKey(String privateKey,  String algorithm) throws Exception {
        // 私钥
		byte[] decoded = Objects.requireNonNull(hexToBinary(privateKey));
        return KeyFactory.getInstance(algorithm).generatePrivate(new PKCS8EncodedKeySpec(decoded));
    }

    /**
     * 生成 密钥(SecreKey) (对称加密)
     *
     * @param key       原始密钥
     * @param length    有效的密钥长度
     * @param algorithm 加密算法
     * @return 密钥(SecreKey)
     */
    private static SecretKey generateSecreKey(String key, Integer length, String algorithm, Class<?> clz) {
        try {
            //判断是否提供了密钥
            if (StringUtils.isBlank(key)) {
                key = DEFAULT_KEY;
            }
            //生成有效长度的 key
            key = generateValidKey(key, length);
            //key 转换
            KeySpec keySpec = (KeySpec) clz.getDeclaredConstructor(byte[].class).newInstance((Object) key.getBytes(UTF_8));
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);
            return keyFactory.generateSecret(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("密钥生成失败 : algorithm : " + algorithm + " , key : " + key);
        }
        return null;
    }

    /**
     * 对称加密 生成有效(length)的长度的 密钥
     * 如果 给定的 key 的长度 和 需要的 length 一致, 则直接返回 key; 当长度不一致时, 生成有效的长度的 密钥
     *
     * @param key    原始密钥
     * @param length 有效的密钥长度
     * @return 有效的长度的 密钥
     */
    private static String generateValidKey(String key, Integer length) {
        //如果 给定的 key 的长度 和 需要的 length 一致, 则直接返回 key
        if (length == key.length()) {
            return key;
        }
        //当长度不一致时, 生成有效的长度的 密钥
        String validKey = "";
        //此处 使用 32 位的 md5
        int md5Len = 32;
        if (length <= md5Len) {
            return md5Digest(key).substring(0, length);
        }
        for (int i = 0; i < length / md5Len; i++) {
            if (i == 0) {
                validKey = md5Digest(key);
            } else {
                validKey += md5Digest(validKey);
            }
        }
        validKey += md5Digest(validKey).substring(0, length % md5Len);
        return validKey;
    }

    /**
     * 对称加密 执行 Cipher, 获取 明文或密文
     *
     * @param content    明文或密文
     * @param algorithm  加密算法名
     * @param cipherMode Cipher init类型(加密还是解密) (1 : 加密, 2 : 解密)
     * @param secretKey  对称加密密钥
     * @param sr         SecureRandom随机函数
     * @return 明文或密文
     * @throws Exception 抛出异常
     */
    private static String executeCipher(String content, String algorithm, int cipherMode, SecretKey secretKey, SecureRandom sr) throws Exception {
        // 加密对象
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(cipherMode, secretKey, sr);
        return executeCipherByCipherMode(cipher, content, cipherMode);
    }

    /**
     * 对称加密 执行 Cipher, 获取 明文或密文
     *
     * @param content    明文或密文
     * @param algorithm  加密算法名
     * @param cipherMode Cipher init类型(加密还是解密) (1 : 加密, 2 : 解密)
     * @param key        密钥(私钥或公钥)
     * @return 明文或密文
     * @throws Exception 抛出异常
     */
    private static String executeCipher(String content, String algorithm, int cipherMode, Key key) throws Exception {
        // 加密对象
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(cipherMode, key);
        return executeCipherByCipherMode(cipher, content, cipherMode);
    }

    /**
     * 对称加密 根据 cipherMode(1 : 加密; 2 : 解密) 执行 Cipher, 获取 明文或密文
     *
     * @param cipher    加密器
     * @param content   明文或密文
     * @param cipherMode    Cipher init类型(加密还是解密) (1 : 加密, 2 : 解密)
     * @return  明文或密文
     * @throws Exception    抛出异常
     */
    private static String executeCipherByCipherMode(Cipher cipher, String content, int cipherMode) throws Exception {
        //把字符串解码为字节数组，并解密
        //注意: 加密后的byte数组是不能强制转换成字符串的,
        //     换言之: 字符串和byte数组在这种情况下不是互逆的.
        //     此处将二进制数据转换成十六进制表示
        if (cipherMode == Cipher.ENCRYPT_MODE) {
            //加密
            return binaryToHex(cipher.doFinal(content.getBytes(UTF_8)));
        } else if (cipherMode == Cipher.DECRYPT_MODE) {
            //解密
            try {
                return new String(cipher.doFinal(Objects.requireNonNull(hexToBinary(content))), UTF_8);
            } catch (BadPaddingException e) {
                e.printStackTrace();
                logger.error("加密或解密错误 : 请输入正确的信息 : content : " + content + " : " + ExceptionUtils.getMessage(e));
                throw new RuntimeException("加密或解密错误 : 请输入正确的信息 : content : " + content + " : " + ExceptionUtils.getMessage(e));
            }
        }
        throw new RuntimeException("cipherMode 类型错误,只支持加密与解密, cipherMode : " + cipherMode);
    }

}
