package com.example.demo.study.testdemo.leetcodetopic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @ClassName: WordCompress 单词的压缩编码 (leetcode820)
 * @Description: 给定一个单词列表，我们将这个列表编码成一个索引字符串S与一个索引列表 A。
 *
 *              例如，如果这个列表是 ["time", "me", "bell"]，我们就可以将其表示为 S = "time#bell#" 和 indexes = [0, 2, 5]。
 *
 *              对于每一个索引，我们可以通过从字符串 S中索引的位置开始读取字符串，直到 "#" 结束，来恢复我们之前的单词列表。
 *
 *              那么成功对给定单词列表进行编码的最小字符串长度是多少呢？
 *
 *              示例：
 *
 *              输入: words = ["time", "me", "bell"]
 *              输出: 10
 *              说明: S = "time#bell#" ， indexes = [0, 2, 5] 。
 *
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/04/21 03:41
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
public class WordCompress {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] words = str.split(",");
        int len = wordCompressCode(words);
        System.out.println(len);
    }

    /**
     * 单词的压缩编码
     * @param words 单词数组
     * @return  压缩编码之后的长度
     */
    private static int wordCompressCode(String[] words) {
        Set<String> hashset = new HashSet<>(Arrays.asList(words));
        for (String word : words) {
            for (int i = 1; i < word.length(); i ++) {
                hashset.remove(word.substring(i));
            }
        }
        int len = 0;
        for (String s : hashset) {
            System.out.println(s);
            len += s.length() + 1;
        }
        return len;
    }
}
