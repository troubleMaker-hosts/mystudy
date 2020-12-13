package com.example.demo.model;

/**
 * @ClassName: BaseRequestEntity
 * @Description:    请求基类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/11/14 02:58
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
public class RequestEntity<T> {
    /**
     * 国际化语言
     */
    private String language;

    private T data;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseRequestEntity{" +
                "language='" + language + '\'' +
                ", reqData=" + data +
                '}';
    }
}
