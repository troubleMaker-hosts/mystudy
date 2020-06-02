package com.example.demo.study.testdemo.mumtopic;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

/**
 * @ClassName: 字符串最后一个单词的长度
 * @Description:    计算字符串最后一个单词的长度，单词以空格隔开。
 *                  输入描述:
 *                      一行字符串，非空，长度小于5000。
 *
 *                  输出描述:
 *                      整数N，最后一个单词的长度。
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/04/18 03:54
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
public class 字符串最后一个单词的长度 {
    public static void main(String[] args) {
        //System.out.println("Enter a String : ");
        //从控制台 输入字符串
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int length = getLastStringLength(str);
        System.out.println(length);
    }

    /**
     * 获取字符串最后一个单词的长度
     * @param str   原始字符串
     * @return  字符串最后一个单词的长度
     */
    private static int getLastStringLength(String str) {
        String[] strs = str.split(" ");
        for (int i = strs.length - 1; i >= 0; i --) {
            String s = strs[i];
            Pattern pattern = compile("^[A-Za-z]+");
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                //System.out.println(matcher.group(0));
                return matcher.group(0).length();
            }
        }
        return 0;
    }
}
