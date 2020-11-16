package com.example.demo.config;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: I18nLanguageEnum
 * @Description:    国际化  语言 枚举类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/10/27 03:18
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
public enum I18nLanguageEnum {
    /**
     * 美式英文
     */
    LANGUAGE_EN_US("en_us"),
    /**
     * 简体中文
     */
    LANGUAGE_ZH_CN("zh_cn");

    /**
     * 语言
     */
    private String language;

    I18nLanguageEnum(String language){
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    /**
     * 获取指定语言类型(如果没有对应的语言类型,则返回中文)
     *
     * @param language 语言类型
     * @return  语言类型
     */
    public static String getLanguageType(String language){
        if (StringUtils.isEmpty(language)) {
            return LANGUAGE_ZH_CN.getLanguage();
        }
        for (I18nLanguageEnum languageEnum : I18nLanguageEnum.values()) {
            if (languageEnum.getLanguage().equalsIgnoreCase(language)) {
                return languageEnum.getLanguage();
            }
        }
        return LANGUAGE_ZH_CN.getLanguage();
    }
}
