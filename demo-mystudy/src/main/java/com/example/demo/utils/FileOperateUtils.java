package com.example.demo.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName: FileUtils
 * @Description:    文件 操作 工具类 (推荐直接使用 org.apache.commons.io.FileUtils)
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/01/02 01:25
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
@Deprecated
public class FileOperateUtils extends FileUtils {
    /**
     * 日志(log4j)
     */
    private static final Logger LOGGER = LogManager.getLogger(FileOperateUtils.class);

    /**
     * 判断 文件 或 文件夹 是否存在
     * @param path  路径
     * @return  是否存在
     */
    public static boolean isExist(String path) {
        File file = new File(path);
        return file.exists();
    }

    /**
     * 根据 路径创建 文件(包括文件夹)
     * @param path  路径
     * @return  是否创建成功
     */
    public static boolean createFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            LOGGER.info("文件已经存在 : " + path);
            return false;
        }
        try {
            //为给定的文件创建任何必要但不存在的父目录。
            FileUtils.forceMkdirParent(file);
            if (file.createNewFile()) {
                LOGGER.info("创建文件成功 : " + path);
                return true;
            } else {
                LOGGER.error("创建文件失败 : " + path);
            }
        } catch (IOException e) {
            LOGGER.error("创建文件失败 : " + ExceptionUtils.getMessage(e));
        }
        return false;
    }

    /**
     * 根据 相对路径 获取 绝对路径 (如果 文件 不存在 会报 空指针 的异常)
     * @param relativePath  相对路径
     * @return  绝对路径
     */
    public static String getAbsolutePath(String relativePath) {
        //如果 文件 不存在 会报 空指针 的异常
        try {
            return FileOperateUtils.class.getClassLoader().getResource(relativePath).getPath();
        } catch (Exception e) {
            LOGGER.error("获取绝对路径失败 : relativePath : " + relativePath + " : " + ExceptionUtils.getMessage(e));
        }
        return null;
    }


    public static void main(String[] args) {
        //绝对路径
        System.out.println("绝对路径 : " + FileOperateUtils.class.getResource(""));
        //当前的classpath的绝对URI路径
        System.out.println(FileOperateUtils.class.getResource("/"));
        //绝对路径 从 项目所在盘符开始(windows) (linux : /)
        System.out.println(isExist("/static/testFile.txt"));
        System.out.println(new File("/static/testFile.txt").getAbsoluteFile());
        //相对路径 从 项目 所在 文件夹 下面开始
        System.out.println(new File("static/testFile.txt").getAbsoluteFile());
        System.out.println(new File("").getAbsoluteFile());
        System.out.println(FileOperateUtils.class.getClassLoader().getResource("static").getPath());
        System.out.println(FileOperateUtils.class.getClassLoader().getResource("static/testFile.txt").getPath());
        System.out.println(FileOperateUtils.class.getClassLoader().getResource("static/testFile.txt").getPath());
        System.out.println(isExist(FileOperateUtils.class.getClassLoader().getResource("static/testFile.txt").getPath()));
        System.out.println(isExist("F:\\fileTest\\file"));
    }
}
