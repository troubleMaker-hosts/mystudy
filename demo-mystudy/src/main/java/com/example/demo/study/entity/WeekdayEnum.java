package com.example.demo.study.entity;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: WeekdayEnum
 * @Description: 星期 的 枚举类(第几天, 星期几)
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/10/15 01:41
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public enum WeekdayEnum {
    //自定义变量与默认的ordinal属性并不冲突，ordinal还是按照它的规则给每个枚举变量按顺序赋值。
    //一定要把枚举变量的定义放在第一行，并且以分号结尾。
    MON(1,"mon"),
    TUS(2,"tus"),
    WED(3,"wed"),
    THU(4,"thu"),
    FRI(5,"fri"),
    SAT(6,"sat"),
    SUN(0,"sun");

    private int key;

    private String label;

    WeekdayEnum(Integer key, String label) {
        this.key = key;
        this.label = label;
    }

    public Integer getKey() {
        return key;
    }

    public String getLabel() {
        return label;
    }

    /**
     * 设置 键 的 值
     * @param key 键
     */
    public void setKey(Integer key) {
        //TODO 此处使用了 set 方法  实际应用中 应该 谨慎 使用（最好不要使用set方法）
        this.key = key;
    }

    /**
     * 设置 Label 的 值
     * @param label 值
     */
    public void setLabel(String label) {
        //TODO 此处使用了 set 方法  实际应用中 应该 谨慎 使用（最好不要使用set方法）
        this.label = label;
    }

    @Override
    public String toString() {
        return key + "";
    }

    /**
     * 通过 key 获取 label
     * @param num 键
     * @return 该key(键)对应的值(label)
     */
    public static String getLabelByKey(Integer num) {
        for (WeekdayEnum weekdayEnum : WeekdayEnum.values()) {
            if (num.equals(weekdayEnum.getKey())) {
                return weekdayEnum.getLabel();
            }
        }
        return null;
    }

    /**
     * 通过 label 获取 key
     * @param str 值
     * @return 该label对应的key
     */
    public static Integer getKeyByLabel(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        for (WeekdayEnum weekdayEnum : WeekdayEnum.values()) {
            if (str.equalsIgnoreCase(weekdayEnum.getLabel())) {
                return weekdayEnum.getKey();
            }
        }
        return null;
    }
}
