package com.example.demo.mytest;

import com.example.demo.DemoMystudyApplication;
import com.example.demo.annotation.ResourceAnnotation;
import com.example.demo.annotation.RuntimeAnnotation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @ClassName: AnnotationTest
 * @Description:    自定义注解 测试
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/06/26 04:30
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
@ResourceAnnotation("resourceAnnotationType")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {DemoMystudyApplication.class})
public class AnnotationTest {
     /**
     * 日志(log4j)
     */
    private static final Logger logger = LogManager.getLogger(AnnotationTest.class);

    @ResourceAnnotation("resourceAnnotationField")
    @RuntimeAnnotation(name = "annotationTest", number = 12, strings = {"stringTest1", "stringTest2"})
    private String fieldTest;

    @Test
    public void annotationTest() throws Exception {
        Class annotationTest = Class.forName("com.example.demo.mytest.AnnotationTest");

        //拿到 Field 上的 注解
        logger.info("拿到 Field 上的 注解 : ");
        Field field = annotationTest.getDeclaredField("fieldTest");
        // 设置 私有 属性 可访问
        field.setAccessible(true);
        System.out.println(field);
        System.out.println(field.getName());
        System.out.println(field.get(annotationTest.getDeclaredConstructor().newInstance()));
        // 获取该元素上指定类型的注解
        //只有当注解的保持力处于运行阶段，即使用@Retention(RetentionPolicy.RUNTIME)修饰注解时，才能在JVM运行时，检测到注解，并进行一系列特殊操作。
        RuntimeAnnotation runtimeAnnotation = field.getAnnotation(RuntimeAnnotation.class);
        System.out.println(runtimeAnnotation.toString());
        System.out.println("name : " + runtimeAnnotation.name() + " ; number : " + runtimeAnnotation.number() + " ; strings : " + Arrays.toString(runtimeAnnotation.strings()));
        ResourceAnnotation resourceAnnotation = field.getAnnotation(ResourceAnnotation.class);
        System.out.println(resourceAnnotation);

        //使用 getAnnotations() 方法 只能拿到 使用 @Retention(RetentionPolicy.RUNTIME) 修饰的注解
        Annotation[] annotations = field.getAnnotations();
        for (Annotation annotation : annotations) {
            //打印 annotation 和 annotation.toString() 是一样的
            System.out.println(annotation);
            System.out.println(annotation.toString());
        }

        //拿到 Type 上的 注解
        logger.info("拿到 Type 上的 注解 : ");
        //只能拿到 使用 @Retention(RetentionPolicy.RUNTIME) 修饰的注解
        Annotation[] typeAnnotations = annotationTest.getAnnotations();
        for (Annotation typeAnnotation : typeAnnotations) {
            System.out.println(typeAnnotation);
            System.out.println(typeAnnotation.toString());
        }
    }

}
