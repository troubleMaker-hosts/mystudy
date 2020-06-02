package com.example.demo.model;

import java.io.Serializable;

/**
 * @ClassName: RespEntity
 * @Description: controller层 返回类型
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/11/21 03:14
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class RespEntity<T> implements Serializable {
    private static final long serialVersionUID = -5000452450309299229L;

    /**
     * 成功状态码
     */
    public static final int SUCCESS_CODE = 1;

    /**
     * 失败状态码
     */
    public static final int FAIL_CODE = 0;

    /**
     * 返回状态码(1:成功; 0:失败)
     */
    private Integer code;

    /**
     * 返回的消息
     */
    private String msg;

    /**
     * 返回的数据
     */
    private T dataInfo;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getDataInfo() {
        return dataInfo;
    }

    public void setDataInfo(T dataInfo) {
        this.dataInfo = dataInfo;
    }

    @Override
    public String toString() {
        return "RespEntity{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + dataInfo +
                '}';
    }
}
