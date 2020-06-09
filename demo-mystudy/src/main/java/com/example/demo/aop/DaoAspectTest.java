package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @ClassName: InsertAopTest
 * @Description:    切面类 : 以所有 dao 层 的 insert开头的 方法 为 切入点
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/04/03 04:26
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
@Aspect
@Component
public class DaoAspectTest {
    /**
     * 定义切入点，切入点为com.example.demo.aop.AopController中的所有函数
     * 通过@Pointcut注解声明频繁使用的切点表达式 :
     *
     * execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern)throws-pattern?)
     *
     * 括号中各个pattern分别表示：
     *
     * 修饰符匹配（modifier-pattern?）
     * 返回值匹配（ret-type-pattern）可以为*表示任何返回值,全路径的类名等
     * 类路径匹配（declaring-type-pattern?）
     * 方法名匹配（name-pattern）可以指定方法名 或者 *代表所有, set* 代表以set开头的所有方法
     * 参数匹配（(param-pattern)）可以指定具体的参数类型，多个参数间用“,”隔开，各个参数也可以用“*”来表示匹配任意类型的参数，如(String)表示匹配一个String参数的方法；(*,String) 表示匹配有两个参数的方法，
     *      第一个参数可以是任意类型，而第二个参数是String类型；可以用(..)表示零个或多个任意参数
     * 异常类型匹配（throws-pattern?）
     * 其中后面跟着“?”的是可选项
     *
     *
     * 接口类 默认修饰符 为 public
     * @param object 参数
     */
    @Pointcut("execution(public * com.example.demo.dao.*.insert*(*)) && args(object)")
    public void InsertDataAspect(Object object){
    }

    /**
     * 切入点 执行之前 执行
     * @param object 参数
     */
    //@Before(value = "InsertDataAspect(object)")
    public void doBeforeInsert(Object object){
        System.out.println(object);

        System.out.println("doBeforeInser-------");
    }

    /**
     * 切入点 执行之后 执行
     * @param object 参数
     */
    //@After("InsertDataAspect(object)")
    public void doAfterInsert(Object object){
        System.out.println("doAfterInsert-------");
    }

    /**
     * 切入点 执行之后 执行(返回结果之后执行)
     * @param object 参数
     * @param result 返回结果
     */
    //@AfterReturning(value = "InsertDataAspect(object)", returning = "result")
    public void doAfterReturningInsert(Object object, Integer result){
        System.out.println("doAfterReturningInsert-------");
        System.out.println("result : " + result);
    }

    /**
     * 切入点 执行之后 抛出异常执行(抛出异常之后执行)
     * @param object 参数
     */
    //@AfterThrowing(value = "InsertDataAspect(object)")
    public void doAfterThrowingInsert(Object object){
        System.out.println("doAfterThrowingInsert-------");
    }

    /**
     *  环绕执行(通知)
     * @param pjp proceedingJoinPoint
     * @param object 参数
     *
     * 注意 : 1. 环绕通知接受ProceedingJoinPoint作为参数，它来调用被通知的方法。
     *               通知方法中可以做任何的事情，当要将控制权交给被通知的方法时，
     *               需要调用ProceedingJoinPoint的proceed()方法。当你不调用proceed()方法时，将会阻塞被通知方法的访问。
     *               (proceed() 方法 抛出 Throwable)
     *        2. 加上Around注解之后, AfterReturning 中的  returning 将 变得 不可用, 会返回 null
     */
    //@Around(value = "InsertDataAspect(object)")
    public void doAroundGameData(ProceedingJoinPoint pjp, Object object) {
        //拿到所有 参数
        Object[] objects = pjp.getArgs();
        //处理 object 对象(切入点前 执行 操作)
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if ("id".equalsIgnoreCase(field.getName())) {
                try {
                    //修改 属性值
                    field.set(object, 12);
                    //查看 修改之后 的属性值
                    System.out.println("查看 修改之后 的属性值 : " + field.get(object));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        //执行 切入点方法
        try {
            Integer i = (Integer) pjp.proceed();
            System.out.println("doAroundGameData 返回值 : " + i);
        } catch (Throwable throwable) {
            System.out.println("执行切入点方法 遇到异常");
            throwable.printStackTrace();
        }
        //切入点后 执行 操作
        System.out.println(object);
        System.out.println("doAroundGameData--------");
    }


}
