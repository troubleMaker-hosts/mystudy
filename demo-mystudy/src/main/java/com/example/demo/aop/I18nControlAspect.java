package com.example.demo.aop;

import com.example.demo.config.RespCodeEnum;
import com.example.demo.model.RespEntity;
import com.example.demo.utils.I18nMessageUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @ClassName: I18nControlAspect
 * @Description: 在control层 对 RespEntity 进行 国际化处理
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/11/06 02:50
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 *
 *
 * 系统层面
 * 系统层面需要做国际化处理的一般是Messages，也就是后端api返回的正常/异常信息。
 * 这里说一下我们的框架针对api的定义：前端调用api时会统一传一个lang的参数来代表当前语言，
 * 对于api调用正常的情况下我们一般返回的Message是ok或者success，具体要显示的内容交给前端去写，
 * 对于api调用异常时会返回异常信息Message。系统层面的国际化处理主要是对这些Messages进行国际化，
 * 国际化的方式大同小异，一般我们是使用两套或者多套properties配置文件将Message以键值对的形式配置在其中，
 * 对于不同的语言使用文件名区分，例如messages_zh_CN.properties代表中文。在系统启动时将这些配置文件以Map的形式加载到内存中方便调用。
 * Message该如何使用？最简单粗暴的方式是在代码中直接调用国际化方法传入properties中的键，返回对应的值。
 * 显然这种使用方式不够优雅，作者认为稍微优雅一些的方式是使用自定义异常+全局异常处理来使用，
 * 我们可以建一个自定义的本地化业务异常，例如LocaleBizServiceException，异常信息可以是上面所说的properties中的键，
 * 为了避免硬编码可以采用常量或者枚举来存键，配合上MVC框架的全局异常捕获，将捕获到的LocaleBizServiceException的异常信息进行国际化处理并返回给前端。
 * 以上系统层面国际化处理基本完成，这种处理方式可以写入框架中，使用者只需要按规定抛出指定异常和异常信息即可。
 *
 * 数据层面
 * 数据层面的国际化一般是为了处理一些系统的数据（不允许或者不建议用户新建的数据），例如系统的角色或者系统的商品、配置等，
 * 对于这些数据我们可能需要在数据层面为其做国际化处理。基本实现原理和上诉系统层面国际化相似。
 * 首先，我们可以在数据库中新建一张表i18n用于存储国际化内容的键值，该至少包含以下字段：键、值、语言，
 * 然后，在业务表中我们可以将需要国际化的字段设计为带i18n前缀，例如i18n_xxx，该字段里存的应该是i18n表中的键。
 * 最后，在系统启动时将这些以Map的形式加载到内存中，在dao层进行国际化处理。
 * 处理方式大致逻辑为：查询sql执行完毕后根据返回的数据集中是否带有i18n前缀的字段，
 * 如果有就从Map中获取这些字段的国际化值并填充进去。如果是框架有dao层代码生成器或者有抽象的dao层可以在框架中直接处理，使用者只需要按规定设计字段即可。
 */
@Aspect
@Component
public class I18nControlAspect {
    /**
     * 日志(log4j)
     */
    private static final Logger LOGGER = LogManager.getLogger(I18nControlAspect.class);

    /**
     * 默认语言
     */
    private static final String LANGUAGE_ZH_CN = "zh_cn";

    /**
     * 前端 返回 国际化语言 的 字段名
     */
    private static final String FIELD_NAME = "language";

    /**
     * 需要被过滤的 包名 (以此包名开头)
     */
    private static final String[] FILTER_PACKAGE_NAMES = {"java.lang"};

    /**
     * 定义国际化切入点 (只对 controller 层下 返回类型为 com.example.demo.model.RespEntity 的方法 进行aop)
     */
    @Pointcut("execution(public com.example.demo.model.RespEntity com.example.demo.controller..*.*(..))")
    public void I18nAspect() {
    }

    /**
     * 对 切入点(I18nAspect()) 执行后的结构 进行 i18n
     *
     * @param pjp 被切入点信息
     * @return 被国际化的 RespEntity
     */
    @Around(value = "I18nAspect()")
    public RespEntity doAroundGameData(ProceedingJoinPoint pjp) {
        Class<?> targetCls = pjp.getTarget().getClass();
        System.out.println(" class : " + targetCls.getName());
        MethodSignature ms = (MethodSignature) pjp.getSignature();
        //拿到 language
        String language = null;
        //先判断 实参(pjp.getArgs()) 中有没有 language
        String[] parameterNames = ms.getParameterNames();
        for (int i = 0; i < parameterNames.length; i++) {
            if (pjp.getArgs()[i] instanceof String && FIELD_NAME.equalsIgnoreCase(parameterNames[i])) {
                language = (String) pjp.getArgs()[i];
            }
        }
        //再 查找 实参 是否有 field 是 language
        if (StringUtils.isEmpty(language)) {
            language = getFieldByName(pjp.getArgs(), FIELD_NAME, String.class);
        }
        //执行 切入点方法
        RespEntity respEntity = null;
        try {
            respEntity = (RespEntity) pjp.proceed();
        } catch (Throwable throwable) {
            System.out.println("执行切入点方法 异常, method : " + targetCls.getName() + "." + ms.getName());
            throwable.printStackTrace();
        }
        //切入点后 执行 操作
        if (ObjectUtils.isNotEmpty(respEntity)) {
            //对 msg 进行 国际化
            if (StringUtils.isEmpty(respEntity.getMsg())) {
                respEntity.setMsg(I18nMessageUtils.getMessage(StringUtils.isEmpty(language) ? LANGUAGE_ZH_CN : language,
                        RespCodeEnum.getMsgByCode(respEntity.getCode())));
            }
            // TODO: 2020/11/11   对 data 进行 国际化
            if (ObjectUtils.isNotEmpty(respEntity.getDataInfo())) {
            }
        } else {
            LOGGER.error("message i18n 失败 : " + ms.getName());
        }
        return respEntity;
    }

    /**
     * 根据 fieldName 获取 field 值
     *
     * @param objects   需要查找的 object
     * @param fieldName field名
     * @param clazz     需要返回的class
     * @param <T>       需要返回的类型
     * @return field 值
     */
    private <T> T getFieldByName(Object[] objects, String fieldName, Class<T> clazz) {
        if (ObjectUtils.isEmpty(objects)) {
            return null;
        }
        for (Object object : objects) {
            if (ObjectUtils.isEmpty(object) || !filterByPackageName(object, FILTER_PACKAGE_NAMES)) {
                continue;
            }
            //class.getFields() 获取类的属性（public），包括父类；
            //class.getDeclaredFields()能获取所有属性（public、protected、default、private），但不包括父类属性
            //apache commons包下的FieldUtils.getAllFields()可以获取类和父类的所有(public、protected、default、private)属性
            Field[] fields = FieldUtils.getAllFields(object.getClass());
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    if (fieldName.equalsIgnoreCase(field.getName())) {
                        if (field.get(object).getClass().equals(clazz)) {
                            return (T) field.get(object);
                        }
                    } else {
                        if (ObjectUtils.isNotEmpty(field.get(object)) && filterByPackageName(field.get(object), FILTER_PACKAGE_NAMES)) {
                            getFieldByName(new Object[]{field.get(object)}, fieldName, clazz);
                        }
                    }
                } catch (IllegalAccessException e) {
                    LOGGER.error("获取属性值s失败 : class : " + object.getClass().getName() + "; field : " + field.getName());
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 过滤掉 不要遍历的 包(以此包名开头)
     *
     * @param object  被检查的object
     * @param filters 需要被过滤的 包名 (以此包名开头)
     * @return 是否需要过滤
     */
    private boolean filterByPackageName(Object object, String... filters) {
        for (String filter : filters) {
            if (object.getClass().getPackageName().startsWith(filter)) {
                return false;
            }
        }
        return true;
    }
}
