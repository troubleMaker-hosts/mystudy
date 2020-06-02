package com.example.demo.study.testdemo.dailydemo;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @ClassName: FileOperationTest
 * @Description:    文件相关(io流) 测试
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/01/06 01:09
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
public class FileOperateTest {
    public static void main(String[] args) {
        String filePath = "F:\\fileTest\\file\\folder1\\folder2\\fileTset1.txt";
        String targetFilePath = "F:\\fileTest\\other\\fileTset1.txt";
        String folderPath = "F:\\fileTest\\file\\folder1";
        String targetFolderPath = "F:\\fileTest\\other";
        //创建 文件(包括文件夹)
        //FileOperateUtils.createFile(filePath);
        try {
            //递归删除目录
            //FileOperateUtils.deleteDirectory(new File(folderPaht));
            //复制 目录 (复制目标 文件夹 下的 文件和目录(不包括目标文件夹))
            //FileUtils.copyDirectory(new File(folderPath), new File(targetFolderPath), false);
            //复制文件到 文件夹 (会覆盖之前 的 文件)
            //FileUtils.copyFileToDirectory(new File(filePath), new File(targetFolderPath), false);
            //复制文件 到 文件 (会覆盖之前 的 文件)
            //FileUtils.copyFile(new File(filePath), new File(targetFilePath), false);

            //将文件的内容逐行读取到字符串列表。
            List<String> lineList = FileUtils.readLines(new File(filePath), StandardCharsets.UTF_8);
            //逐行toString()将集合中每个项的值写入指定File。可以选择追加。
            FileUtils.writeLines(new File(targetFilePath), "UTF-8", lineList,  true);
            //将 string 写入 file, 可以选择追加。
            FileUtils.writeStringToFile(new File(targetFilePath), lineList.get(1), StandardCharsets.UTF_8, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
