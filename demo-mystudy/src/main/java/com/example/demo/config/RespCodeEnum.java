package com.example.demo.config;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: RespCodeEnum
 * @Description: API返回状态码枚举类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/11/03 02:15
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
public enum RespCodeEnum {
    //成功
    SUCCESS(1000, "api.response.code.success"),
    FAIL(2000, "api.response.code.fail"),

    // 公共参数
    //参数错误
    PARAM_ERROR(2001, "api.response.code.paramError"),
    //语言类型错误
    LANGUAGE_TYPE_ERROR(2002, "api.response.code.languageTypeError"),

    // 用户模块
    // 帐号
    ACCOUNT_NULL_ERROR(3001, "api.response.code.user.accountNullError"),
    ACCOUNT_FORMAT_ERROR(3002, "api.response.code.user.accountFormatError"),
    ACCOUNT_NOT_EXIST(3003, "api.response.code.user.accountNotExist"),
    ACCOUNT_EXIST(3004, "api.response.code.user.accountExist"),
    // 密码
    PASSWORD_NULL_ERROR(3101, "api.response.code.user.passwordNullError"),
    PASSWORD_FORMAT_ERROR(3102, "api.response.code.user.passwordFormatError"),
    PASSWORD_ERROR(3103, "api.response.code.user.passwordError"),

    //服务异常
    SERVER_ERROR(5000, "api.response.code.server.serverError"),

    //未知错误
    UNKNOWN_ERROR(9000, "api.response.code.unknownError");

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回信息
     */
    private String msg;

    RespCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 通过code获取msg
     * @param num   code
     * @return  msg
     */
    public static String getMsgByCode(Integer num) {
        for (RespCodeEnum respCodeEnum : RespCodeEnum.values()) {
            if (num.equals(respCodeEnum.getCode())) {
                return respCodeEnum.getMsg();
            }
        }
        return null;
    }

    /**
     * 通过code获取msg
     * @param str   msg
     * @return  code
     */
    public static Integer getCodeByMsg(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        for (RespCodeEnum respCodeEnum : RespCodeEnum.values()) {
            if (str.equalsIgnoreCase(respCodeEnum.getMsg())) {
                return respCodeEnum.getCode();
            }
        }
        return null;
    }



    @Override
    public String toString() {
        return "RespCodeEnum{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
