package com.example.demo.study.testdemo.javabasic;

import com.example.demo.study.entity.WeekdayEnum;

/**
 * @ClassName: EnumWeekdayTest
 * @Description:
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/09/15 23:49
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class EnumWeekdayTest {
    public static void main(String[] args) {
        WeekdayEnum today = WeekdayEnum.FRI;
        //WeekdayEnum today = WeekdayEnum.valueOf("FRI");

        //返回此枚举常量的名称，与其枚举声明中声明的完全相同。
        // 大多数程序员应该使用toString()方法，
        // 因为toString方法可能会返回一个更加用户友好的名称。该方法主要用于专门的情况，
        // 其中正确性取决于获得确切的名称，这从发布到发布不会有所不同。
        System.out.println(today.name());
        //返回此枚举常数的序数（其枚举声明中的位置，其中初始常数的序数为零）。
        // 大多数程序员将不会使用这种方法。
        // 它被设计为使用复杂的基于枚举的数据结构，如EnumSet和EnumMap 。
        System.out.println(today.ordinal());
        //返回声明中包含的此枚举常量的名称。
        // 该方法可以被覆盖，尽管它通常不是必需或不可取的。
        // 当一个更“程序员友好”的字符串形式存在时，枚举类型应该覆盖此方法
        //EnumWeekdayTest 重写了 toString 方法 ， 此处输出的时 EnumWeekdayTest 的key 值的 String
        System.out.println(today.toString());

        EnumWeekdayTest test = new EnumWeekdayTest();
        test.getTodayIsWeekday(today);

        //试试 get和set方法
        System.out.println("set 之前 getKey：" + today.getKey());
        today.setKey(6);
        System.out.println("set 之后 getKey：" + today.getKey());
        System.out.println("set 之前 name：" + today.name());
        System.out.println("set 之后 toString：" + today.toString());

        //通过 key 获取 label
        System.out.println("通过 key 获取 label : " + WeekdayEnum.getLabelByKey(2));
    }

    /**
     * 根据 WeekdayEnum 枚举类的值 输出是 星期几
     * @param today 星期几
     */
    public void getTodayIsWeekday(WeekdayEnum today) {
        switch (today) {
            case MON:
                System.out.println("你输入的 是 星期一");
                break;
            case TUS:
                System.out.println("你输入的 是 星期二");
                break;
            case WED:
                System.out.println("你输入的 是 星期三");
                break;
            case THU:
                System.out.println("你输入的 是 星期四");
                break;
            case FRI:
                System.out.println("你输入的 是 星期五");
                break;
            case SAT:
                System.out.println("你输入的 是 星期六");
                break;
            case SUN:
                System.out.println("你输入的 是 星期天");
                break;
            default:
                System.out.println("输入错误");
                break;
        }
    }
}
