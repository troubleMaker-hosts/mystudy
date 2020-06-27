package com.example.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: TestAnnotation
 * @Description:    注解保持力 : 运行期阶段
 *                                  (只有当注解的保持力处于运行阶段，即使用@Retention(RetentionPolicy.RUNTIME)修饰注解时，
 *                                  才能在JVM运行时，检测到注解，并进行一系列特殊操作。)
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/06/25 03:49
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RuntimeAnnotation {
    String name();
    int number() default 10;
    String[] strings();
}
