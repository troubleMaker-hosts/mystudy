package com.example.demo.study.testdemo.javabasic;

import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: MapTest
 * @Description: 对不同 map 的 测试类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/10/31 02:52
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class MapTest {
    public static void main(String[] args) {
        MapTest test = new MapTest();
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();

        //treeMap : 自然排序(升序)
        TreeMap<String, String> treeMap = new TreeMap<>();
        //hashMap : 无序
        //对象 在 new 之后 就不为 null 了, 此时判断 是否为 empty 需要 用相应的 方法
        HashMap<String, String> hashMap = new HashMap<>(16);
        System.out.println("hashMap = new HashMap<>(16) : hashMap == null : " + (hashMap == null));
        System.out.println("hashMap = new HashMap<>(16) : CollectionUtils.isEmpty(hashMap) : " + CollectionUtils.isEmpty(hashMap));
        test.initMap(treeMap, 10);
        test.initMap(hashMap, 10);

        treeMap.clear();
        System.out.println("treeMap.clear() 测试 : treeMap.size(): " + treeMap.size());

        //treeMap : 自然排序(升序)
        treeMap.put("22", "mapAdd_22");
        treeMap.put("11", "mapAdd_11");
        treeMap.put("55", "mapAdd_55");
        treeMap.put("33", "mapAdd_33");

        //hashMap : 无序
        hashMap.put("22", "mapAdd_22");
        hashMap.put("11", "mapAdd_11");
        hashMap.put("55", "mapAdd_55");
        hashMap.put("33", "mapAdd_33");
        for (Map.Entry<String, String> stringEntry : treeMap.entrySet()) {
            System.out.println("treeMap(自然排序,升序):  key : " + stringEntry.getKey() + "  value : " + stringEntry.getValue());
        }

        //java 8 之前 使用 hashMap.entrySet() 遍历
        //for (Map.Entry<String, String> stringEntry : hashMap.entrySet()) {
        //    System.out.println("hashMap:  key : " + stringEntry.getKey() + "  value : " + stringEntry.getValue());
        //}
        //java 8 使用 forEach 进行遍历
        //hashMap.forEach((String key, String value) -> System.out.println("hashMap:  key : " + key + "  value : " + value));
        hashMap.forEach((key, value) -> System.out.println("hashMap:  key : " + key + "  value : " + value));
        //hashMap get 不存在的 key
        System.out.println("hashMap get 不存在的 key : " + hashMap.get("inexistence"));

    }



    /**
     * 初始化 map
     * @param map 目标map
     * @param num 添加元素的个数
     */
    public void initMap(Map<String, String> map, Integer num) {
        for (int i = num; i >= 0; i--) {
            String str = "mapAdd_" + i;
            map.put(i + "", str);
        }
    }

}
