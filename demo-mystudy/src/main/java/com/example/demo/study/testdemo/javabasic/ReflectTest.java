package com.example.demo.study.testdemo.javabasic;

import com.example.demo.model.StudyUser;
import org.apache.commons.lang3.ObjectUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @ClassName: ReflectTest
 * @Description: 反射 测试类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/10/25 02:51
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class ReflectTest {
    /**
     * 主函数 测试
     * @param args args
     */
    public static void main(String[] args) {
        ReflectTest test = new ReflectTest();
        String classUrl = "com.example.demo.model.StudyUser";
        //反射 创建 对象的 三种 方式:
        //创建Class对象的方式一: (对象.getClass())，获取类中的字节码文件
        //创建Class对象的方式二: (类.class:需要输入一个明确的类，任意一个类型都有一个静态的class属性)
        //创建Class对象的方式三: (forName():传入时只需要以字符串的方式传入即可)
        //通过Class类的一个forName（String className)静态方法返回一个Class对象，className必须是全路径名称；
        //Class.forName()有异常: ClassNotFoundException 需要 处理
        Constructor<?>[] constructors = test.getAllConstructorByClassUrl(classUrl);
        Constructor<?> defaultConstructor = test.getDefaultConstructorByClassUrl(classUrl, (Class<?>) null);
        Constructor<?> publicConstructor = test.getDefaultConstructorByClassUrl(classUrl, Integer.class, String.class, String.class, String.class);
        Constructor<?> privateConstructor = test.getDefaultConstructorByClassUrl(classUrl, String.class, String.class);
        try {
            //使用默认构造器
            StudyUser studyUser = (StudyUser) defaultConstructor.newInstance();
            studyUser.setUserName("reflect");
            System.out.println("默认构造器：" + studyUser.toString());

            //使用公共构造器
            studyUser = (StudyUser) publicConstructor.newInstance(11122, "公共构造器name", "公共构造器password" , "公共构造器phone");
            System.out.println("公共构造器：" + studyUser.toString());

            //使用私有构造器
            System.out.println(privateConstructor);
            //设置 可以访问私有构造器
            privateConstructor.setAccessible(true);
            studyUser = (StudyUser) privateConstructor.newInstance("getDeclaredConstructor : 私有构造器name", "getDeclaredConstructor : 私有构造器password");
            System.out.println("getDeclaredConstructor : 私有构造器：" + studyUser.toString());

            //遍历 所有构造器
            for (Constructor<?> constructor : constructors) {
                System.out.println(constructor);
            }
            //使用私有构造器
            StudyUser studyUserPrivate = (StudyUser) test.reflectPrivateConstructor(classUrl)
                    .newInstance("privateName", "privatePwd");
            System.out.println("私有构造器：" + studyUserPrivate.toString());

            //反射私有 属性
            ArrayList<Field> list = test.reflectPrivateField(classUrl, "serialVersionUID", "userId");
            for (Field field : list) {
                //拿到属性对应值
                System.out.println("拿到属性对应值 : " + field.getName() + " : " + field.get(studyUser));
                if ("userId".equals(field.getName())) {
                    field.set(studyUser, 888);
                    System.out.println("拿到属性(userId) set 之后 的 对应值 : " + field.getName() + " : " + field.get(studyUser));
                }
            }
            list.get(list.size() - 1).set(studyUser, 2);
            System.out.println("userId: " + list.get(list.size() - 1).get(studyUser));
            //反射私有方法 private
            Method privateMethod = test.reflectPrivateMethod(classUrl, "privateMethodTest", String.class);
            //调用 私有方法
            privateMethod.invoke(studyUser, "myReflectTset");
            //反射 共有方法 public  (getUserId)
            Method publicMethod = test.reflectPrivateMethod(classUrl, "getUserId");
            //调用 公共方法 getUserId
            System.out.println("调用 公共方法 getUserId : " + publicMethod.invoke(studyUser));

            //反射 共有方法 public  (publicMethodTest)
            publicMethod = test.reflectPrivateMethod(classUrl, "publicMethodTest", String.class);
            //调用 公共方法 publicMethodTest
            publicMethod.invoke(studyUser, "myReflectTset--public");

            //反射 共有方法 public  (publicMethodTest) ---- 重载的 无参数 方法
            publicMethod = test.reflectPrivateMethod(classUrl, "publicMethodTest");
            //调用 公共方法 publicMethodTest ---- 重载的 无参数 方法
            publicMethod.invoke(studyUser);

            System.out.println("reflectTest : 测试结束---------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 构造方法的作用
     * 为了初始化成员属性，而不是初始化对象，初始化对象是通过new关键字实现的
     * 通过new调用构造方法初始化对象，编译时根据参数签名来检查构造函数，称为静态联编和编译多态（参数签名：参数的类型，参数个数和参数顺序）
     * 创建子类对象会调用父类构造方法但不会创建父类对象，只是调用父类构造方法初始化父类成员属性
     *
     * 根据 类路径 得到 类的构造器，默认构造器
     * @param classUrl 类路径
     * @return 所有构造器
     */
    public Constructor<?> getDefaultConstructorByClassUrl(String classUrl, Class<?>... classes) {
        Class<?> clz;
        try {
            System.out.println(classes[0]);
            //获取类的 Class 对象实例
            clz = Class.forName(classUrl);
            //根据 Class 对象实例获取 Constructor 对象
            if (classes.length == 1 && ObjectUtils.isEmpty(classes[0])) {
                return clz.getDeclaredConstructor();
            } else {
                return clz.getDeclaredConstructor(classes);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("class路径错误:" + classUrl);
        }  catch (Exception e) {
            e.printStackTrace();
            System.out.println("根据 类路径 得到 类的默认构造器 失败" + classUrl);
        }
        return null;
    }

    /**
     * 构造方法的作用
     * 为了初始化成员属性，而不是初始化对象，初始化对象是通过new关键字实现的
     * 通过new调用构造方法初始化对象，编译时根据参数签名来检查构造函数，称为静态联编和编译多态（参数签名：参数的类型，参数个数和参数顺序）
     * 创建子类对象会调用父类构造方法但不会创建父类对象，只是调用父类构造方法初始化父类成员属性
     *
     * 根据 类路径 得到 类的构造器，所有构造器
     * @param classUrl 类路径
     * @return 所有构造器
     */
    public Constructor<?>[] getAllConstructorByClassUrl(String classUrl) {
        Class<?> clz;
        try {
            //获取类的 Class 对象实例
            clz = Class.forName(classUrl);
            //根据 Class 对象实例获取 Constructor 对象
            return clz.getDeclaredConstructors();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("class路径错误:" + classUrl);
        }  catch (Exception e) {
            e.printStackTrace();
            System.out.println("根据 类路径 得到 类的所有构造器 失败" + classUrl);
        }
        return null;
    }

    /**
     * 构造方法的作用
     * 为了初始化成员属性，而不是初始化对象，初始化对象是通过new关键字实现的
     * 通过new调用构造方法初始化对象，编译时根据参数签名来检查构造函数，称为静态联编和编译多态（参数签名：参数的类型，参数个数和参数顺序）
     * 创建子类对象会调用父类构造方法但不会创建父类对象，只是调用父类构造方法初始化父类成员属性
     *
     * 反射私有的构造方法
     * @param classUrl 累路径
     * @return 私有构造器
     */
    public Constructor<?> reflectPrivateConstructor(String classUrl) {
        try {
            Class<?> clz = Class.forName(classUrl);
            Constructor<?> declaredConstructor = clz.getDeclaredConstructor(String.class, String.class);
            // 设置 私有 构造器 可访问
            declaredConstructor.setAccessible(true);
            return declaredConstructor;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 反射私有属性
     * @param classUrl 类路径
     * @param strProvateAttribute  需要反射的 类的 属性名
     */
    public ArrayList<Field> reflectPrivateField(String classUrl, String... strProvateAttribute) {
        ArrayList<Field> list = new ArrayList<>();
        try {
            Class<?> clz = Class.forName(classUrl);
            for (String s : strProvateAttribute) {
                Field field = clz.getDeclaredField(s);
                field.setAccessible(true);
                list.add(field);
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 反射私有方法
     * @param classUrl 类路径
     * @param methodName 方方法名
     * @param parameterClass 参数类型
     * @return Method对象
     */
    public Method reflectPrivateMethod(String classUrl, String methodName,  Class<?>... parameterClass) {
        try {
            Class<?> classBook = Class.forName(classUrl);
            Method method = classBook.getDeclaredMethod(methodName, parameterClass);
            //强制 拿到 所有访问权限
            method.setAccessible(true);
            return method;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }



}
