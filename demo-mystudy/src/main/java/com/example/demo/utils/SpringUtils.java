package com.example.demo.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName: SpringUtils
 * @Description: spring bean 的 工具方法
 * 注意 : @Component注解是不可以去掉的，去掉后Spring就不会自动调用setApplicationContext方法来为我们设置上下文实例。
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/11/25 01:28
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
@Component
public class SpringUtils implements ApplicationContextAware {
    /**
     * springBean 容器
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtils.applicationContext = applicationContext;
    }

    /**
     * 根据bean名字和类名 获取 bean
     * @param beanName  bean名字
     * @param clazz bean类型
     * @param <T>   返回 bean类型
     * @return  返回 <T> 类型中 bean名字为 beanName 的 对象
     */
    public static <T> T getBean(String beanName, Class<T> clazz) {
        return applicationContext.getBean(beanName, clazz);
    }

    /**
     * 根据bean名字(默认名字为类名驼峰格式) 获取 bean
     * @param beanName  bean名字
     * @param <T>   返回 bean类型
     * @return  返回  bean名字为 beanName 的 对象
     */
    public static <T> T getBean(String beanName) {
        return (T) applicationContext.getBean(beanName);
    }

    /**
     *  根据 类型 获取 bean
     * @param clazz bean类型
     * @param <T>   返回 bean类型
     * @return  返回 一个 类型为 clazz 的 对象
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     *  获取容器中指定某类型、或实现某接口、或继承某父类所有的 Bean
     * @param clazz bean类型
     * @param <T>   返回 bean类型
     * @return  返回 某类型 所有的 bean(Map<beanName, bean>)
     */
    public static <T> Map<String, T> getBeansByType(Class<T> clazz){
        return applicationContext.getBeansOfType(clazz);
    }


}
