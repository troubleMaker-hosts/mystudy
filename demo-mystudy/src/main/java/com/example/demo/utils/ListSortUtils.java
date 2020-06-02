package com.example.demo.utils;

import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

/**
 * 功能说明
 *
 * 在数据库中查出来的列表中，往往需要对不同的字段重新排序。 一般的做法都是使用排序的字段，重新到数据库中查询。
 * 如果不到数据库查询，直接在第一次查出来的list中排序，无疑会提高系统的性能。 下面就写一个通用的方法，对list排序，
 *
 * 至少需要满足以下5点：
 *
 * ①.list元素对象类型任意
 * 		---->使用泛型解决
 *
 * ②.可以按照list元素对象的任意多个属性进行排序,即可以同时指定多个属性进行排序
 * 		--->使用java的可变参数解决
 *
 * ③.list元素对象属性的类型可以是数字(byte、short、int、long、float、double等，包括正数、负数、0)、字符串(char、String)、日期(java.util.Date)
 * 		--->对于数字：统一转换为固定长度的字符串解决,比如数字3和123，转换为"003"和"123" ;再比如"-15"和"7"转换为"-015"和"007"
 * 		--->对于日期：可以先把日期转化为long类型的数字，数字的解决方法如上
 *
 * ④.list元素对象的属性可以没有相应的getter和setter方法
 * 		--->可以使用java反射进行获取private和protected修饰的属性值
 *
 * ⑤.list元素对象的对象中每个属性都可以指定是升序还是降序
 * 	  	-->使用2个重写的方法(一个方法满足所有属性都按照升序(降序)，另外一个方法满足每个属性都能指定是升序(降序))
 *
 *
 * @ClassName: ListSortUtils
 * @Description: list 排序 工具类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/10/15 01:41
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 *
 */
public class ListSortUtils {
    /**
     * 对list集合中的元素按照多个属性名称排序,多个属性的排序方式采用统一方式处理。
     * list元素的属性可以是数字（byte、short、int、long、float、double等，支持正数、负数、0）、
     * char、String、java.util.Date
     *
     * @param list  需要排序的集合
     * @param sortName list集合中需要排序的元素属性名称，动态数组
     * @param isAsc true升序，false降序
     */
    public static <T> void sort(List<T> list, final boolean isAsc, final String... sortName) {
        list.sort((a, b) -> {
            int ret = 0;
            try {
                for (String s : sortName) {
                    ret = ListSortUtils.compareObject(s, isAsc, a, b);
                    if (0 != ret) {
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ret;
        });
    }

    /**
     * 给list的每个属性都指定是升序还是降序
     *
     * @param list  需要排序的list
     * @param sortNameArray  参数数组
     * @param sortDirectionArray      每个属性对应的升降序数组， true升序，false降序
     */
    public static <T> void sort(List<T> list, final String[] sortNameArray, final boolean[] sortDirectionArray) {
        if (sortNameArray.length != sortDirectionArray.length) {
            throw new RuntimeException("属性数组元素个数和升降序数组元素个数不相等");
        }
        list.sort((t1, t2) -> {
            int ret = 0;
            try {
                //循环比较对象
                for (int i = 0; i < sortNameArray.length; i++) {
                    ret = compareObject(sortNameArray[i], sortDirectionArray[i], t1, t2);
                    if (0 != ret) {
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ret;
        });
    }
    /**
     * 对2个对象按照指定属性名称进行排序
     *
     * @param sortName 排序的属性名称
     * @param isAsc true升序，false降序
     * @param t1    对象1
     * @param t2    对象2
     * @return  比较结果(compare 的 结果)
     * @throws Exception 异常信息
     */
    private static <T> int compareObject(final String sortName, final boolean isAsc, T t1, T t2) throws Exception {
        int ret;
        Object value1 = forceGetFieldValue(t1, sortName);
        Object value2 = forceGetFieldValue(t2, sortName);
        String str1 = value1.toString();
        String str2 = value2.toString();
        //为数字类型时转换成指定长度的字符串
        if (value1 instanceof Number && value2 instanceof Number) {
            //获取需要比较的属性值最大长度，保持数据精度
            int maxlen = Math.max(str1.length(), str2.length());
            str1 = addZero2Str((Number) value1, maxlen);
            str2 = addZero2Str((Number) value2, maxlen);
        } else if (value1 instanceof Date && value2 instanceof Date) {
            //把时间转换成数字
            long time1 = ((Date) value1).getTime();
            long time2 = ((Date) value2).getTime();
            //获取需要比较的属性值最大长度，保持数据精度 把时间转换成数字再转换成字符串
            int maxlen = Long.toString(Math.max(time1, time2)).length();
            str1 = addZero2Str(time1, maxlen);
            str2 = addZero2Str(time2, maxlen);
        }
        //排序方式
        if (isAsc) {
            ret = str1.compareTo(str2);
        } else {
            ret = str2.compareTo(str1);
        }
        return ret;
    }

    /**
     * 给数字对象按照指定长度在左侧补0，并转换成字符串。
     *
     * 使用案例: addZero2Str(11,4) 返回 "0011", addZero2Str(-18,6)返回 "-000018"
     *
     * @param numObj 数字对象
     * @param length 指定的长度
     * @return  给数字对象按照指定长度在左侧补0，并转换成字符串
     */
    public static String addZero2Str(Number numObj, int length) {
        NumberFormat nf = NumberFormat.getInstance();
        // 设置是否使用分组
        nf.setGroupingUsed(false);
        // 设置最大整数位数
        nf.setMaximumIntegerDigits(length);
        // 设置最小整数位数
        nf.setMinimumIntegerDigits(length);
        return nf.format(numObj);
    }

    /**
     * 通过反射方式获取指定对象的指定属性值（去除private,protected的限制）
     *
     * @param obj 属性名称所属的对象
     * @param fieldName 属性名称
     * @return  对象 指定的属性值
     * @throws Exception 抛出异常
     */
    public static Object forceGetFieldValue(Object obj, String fieldName) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        Object object;
        boolean accessible = field.isAccessible();
        if (!accessible) {
            // 如果是private,protected修饰的属性，需要修改为可以访问的
            field.setAccessible(true);
            object = field.get(obj);
            // 还原private,protected属性的访问性质
            field.setAccessible(accessible);
            return object;
        }
        object = field.get(obj);
        return object;
    }
}
