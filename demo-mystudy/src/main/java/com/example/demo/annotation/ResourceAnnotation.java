package com.example.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: ResourceAnnotation
 * @Description:    注解保持力 : Java源文件阶段
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/06/25 03:55
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface ResourceAnnotation {
    String value();
}
