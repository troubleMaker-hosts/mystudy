package com.example.demo.study.testdemo.javabasic;

/**
 * @ClassName: StringApiTest
 * @Description: 用于测试 string 的 api 或 其他的 一些 对 String 类型的数据 的 操作
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/10/30 03:48
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class StringApiTest {
    public static void main(String[] args) {
        StringApiTest test = new StringApiTest();
        String str = "qwertyuiopqwerqwer";
        System.out.println(str.contains("uio"));
        System.out.println("uio".contains(str));
        //substring 测试
        System.out.println(str + " : test.subStringTest(str, 0, 2) :　" + test.subStringTest(str, 0, 2));
        System.out.println(str + " : test.subStringTest(str, 2, null) :　" + test.subStringTest(str, 2, null));
        //compareToTest  测试
        System.out.println(test.compareToTest("23:34", "12:08"));
        System.out.println(test.compareToTest("23:34", "23:34"));
        System.out.println(test.compareToTest("23:34", "23:50"));
        int i = test.indexOfTest(str, "q");
        System.out.println(str + ".indexOf(q) : " + i);
        i = test.lastIndexOfTest(str, "q");
        System.out.println("subStringTest : " + test.subStringTest(str, i, null));
        System.out.println("subStringTest : " + test.subStringTest("CRZ/CI010", "CRZ/CI010".indexOf("/"), null));
        System.out.println(str + ".lastIndexOfTest(q) : " + i);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("qwer");
        stringBuilder.append("asdf");
        System.out.println(stringBuilder.toString());

        //测试 replace api
        String path = "econ/201905/28/B6845/00001e7e64195841921ac7dadc5e63dc";
        System.out.println("replaceAll 之前 : " + path);
        System.out.println("/");
        System.out.println("\\");
        System.out.println("\\\\");
        /**
         * \\会转义成反斜杠，反斜杠本身就是转义符，
         *  比如 : "\\|" : \\会转义成反斜杠，反斜杠本身就是转义符，所有就成了“\|”，在进行转义就是|，所以\\|实际上是“|”。
         *
         *  下面测试 个人理解:
         *  \\\\ : \\\\ 看成是两组 \\ , 每组 转义成 \ , 变成 \\, 再转义 成 \
         */
        System.out.println("replaceAll 之后 : " + test.replaceTest(path, "/", "\\\\"));

        System.out.println(test.startsWithTest(".qwer", "."));

        System.out.println("sdfh shdkf ".trim() + "结束");
        System.out.println("\f(FPL-MKR670-IS");
        System.out.println("\f(FPL-MKR670-IS".indexOf("\f"));
        System.out.println(test.indexOfTest("--|--", "|"));
        System.out.println(test.indexOfTest("xxx.sdfds", "."));

        /**
         * replace 和 replaceAll 的区别 : replace是 字符串 匹配, replace是 正则表达式 匹配
         * split 是 正则表达式 匹配
         */
        String strTest = "00.2.5/Z";
        System.out.println("substring test : " + strTest.substring(0, 0));
        System.out.println("substring test : " + strTest.substring(0, strTest.indexOf("Z")).replace(".", ""));
        System.out.println("substring test : " + strTest.substring(0, strTest.indexOf("Z")).replaceAll(".", ""));

        System.out.println(strTest.split("\\.")[0]);
        System.out.println("/");
        System.out.println(strTest.split("/")[0]);

        //repeat api 测试 : (当 count = 0 时, 返回 空字符串(""))
        int count = 0;
        System.out.println("repeat api 测试 : str + " + strTest +
                " , cpunt : " + count + " : " + test.repeatTest(strTest, count));

    }

    /**
     * repeat api 测试
     *      当 count = 0 时, 返回 空字符串("")
     * @param str   原字符串
     * @param count 复制的次数
     * @return  复制之后的字符串
     */
    public String repeatTest(String str, Integer count) {
        return str.repeat(count);
    }

    /**
     * startsWith api 测试
     * @param str   原字符串
     * @param regularStr    需要找到的 字符串
     * @return  str 是否 是以 regularStr 开头
     */
    public String startsWithTest(String str, String regularStr) {
        if (str.startsWith(regularStr)) {
            return str + " 是以 " + regularStr + "开头的----true";
        } else {
            return str + " 不是以 " + regularStr + "开头的---false";
        }
    }

    /**
     * replaceAll api 测试
     * @param str   原字符串
     * @param target    被替换的字符
     * @param replacement   替换的字符
     * @return  替换后的结果字符
     */
    public String replaceTest(String str, String target, String replacement) {
        return str.replaceAll(target, replacement);
    }

    /**
     * lastIndexOf api 测试
     * @param str   原字符串(目标字符串)
     * @param regularStr    需要找到的 字符串
     * @return  regularStr 在 str 中的最后一个
     */
    public int lastIndexOfTest(String str, String regularStr) {
        return str.lastIndexOf(regularStr);
    }

    /**
     * indexOf  方法测试
     * @param str 目标字符串
     * @param regularStr 需要查找的 str
     * @return 第一个下标
     */
    public int indexOfTest(String str, String regularStr) {
        return str.indexOf(regularStr);
    }

    /**
     * subString 方法 測試 ： 有 beginIndex 和 endIndex 和 只有 beginIndex 都可 測試
     * @param str 目标字符串
     * @param beginIndex 开始下标
     * @param endIndex 结束下标
     * @return 截取的 字符串
     */
    public String subStringTest(String str, Integer beginIndex, Integer endIndex) {
        if (endIndex == null) {
            return str.substring(beginIndex);
        }
        return str.substring(beginIndex, endIndex);
    }


    /**
     * compareToTest 兩個字符串 比較 測試
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 比较结果
     */
    public String compareToTest (String str1, String str2) {
        if (str1.compareTo(str2) > 0) {
            return str1 + " > "  + str2;
        }
        if (str1.compareTo(str2) < 0) {
            return str1 + " < "  + str2;
        }
        return str1 + " = "  + str2;
    }
}
