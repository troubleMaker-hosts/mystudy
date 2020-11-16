package com.example.demo.utils;

import com.example.demo.config.RespCodeEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * @ClassName: I18nMessageUtil
 * @Description: 国际化 message(系统层面) util
 *              注意: 此工具使用
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/11/04 02:32
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
public class I18nMessageUtils {
    /**
     * 日志(log4j)
     */
    private static final Logger LOGGER = LogManager.getLogger(I18nMessageUtils.class);

    private static MessageSourceAccessor accessor;

    /**
     * 配置文件路径
     */
    private static final String PATH_PARENT = "classpath:i18n/";

    /**
     * 文件q前缀
     */
    private static final String PREFIX = "message_";

    /**
     * 文件后缀名
     */
    private static final String SUFFIX = ".properties";

    private static ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    /**
     * 初始化资源文件的存储器
     * 加载指定语言配置文件
     *
     * @param language 语言类型(文件名即为语言类型,eg: en_us 表明使用 美式英文 语言配置)
     */
    private static void initMessageSourceAccessor(String language) {
        /**
         * 获取配置文件名
         */
        String relativePath = PATH_PARENT + PREFIX + language + SUFFIX;
        Resource resource = resourcePatternResolver.getResource(relativePath);
        String fileName = "";
        try {
            fileName = resource.getURL().toString();
        } catch (IOException e) {
            LOGGER.error("文件路径有误 : relativePath : " + relativePath);
            e.printStackTrace();
        }
        int lastIndex = fileName.lastIndexOf(".");
        String baseName = fileName.substring(0, lastIndex);
        //读取配置文件
        ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
        reloadableResourceBundleMessageSource.setBasename(baseName);
        reloadableResourceBundleMessageSource.setCacheSeconds(5);
        reloadableResourceBundleMessageSource.setDefaultEncoding("UTF-8");
        accessor = new MessageSourceAccessor(reloadableResourceBundleMessageSource);
    }

    /**
     * 获取一条语言配置信息
     *
     * @param language       语言类型,zh_cn: 简体中文, en_us: 英文
     * @param code        配置信息属性名,eg: api.response.code.user.signUp
     * @param defaultMessage 默认信息,当无法从配置文件中读取到对应的配置信息时返回该信息
     * @return  i18n 的 message
     */
    public static String getMessage(String language, String code, String defaultMessage) {
        initMessageSourceAccessor(language);
        return accessor.getMessage(code, defaultMessage);
    }

    /**
     * 获取一条语言配置信息
     *
     * @param language       语言类型,zh_cn: 简体中文, en_us: 英文
     * @param code        配置信息属性名,eg: api.response.code.user.signUp
     * @return  i18n 的 message
     */
    public static String getMessage(String language, String code) {
        initMessageSourceAccessor(language);
        String message = null;
        try {
            message = accessor.getMessage(code);
        } catch (NoSuchMessageException e) {
            LOGGER.error("配置信息属性名有误 : code : " + code);
            e.printStackTrace();
        }
        return message;
    }

}
