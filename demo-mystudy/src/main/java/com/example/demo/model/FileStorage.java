package com.example.demo.model;

import java.util.Arrays;
import java.util.Date;

/**
 * @ClassName: FileStorage
 * @Description: FileStorage  实体类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/11/21 03:14
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class FileStorage {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件后缀名
     */
    private String fileSuffixName;

    /**
     * 问价路径
     */
    private String filePath;

    /**
     * 网络地址
     */
    private String networkUrl;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 是否有效 (1: 有效, 0 无效)
     */
    private Integer isValid;

    /**
     * 文件内容
     */
    private byte[] fileContext;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    public String getFileSuffixName() {
        return fileSuffixName;
    }

    public void setFileSuffixName(String fileSuffixName) {
        this.fileSuffixName = fileSuffixName == null ? null : fileSuffixName.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getNetworkUrl() {
        return networkUrl;
    }

    public void setNetworkUrl(String networkUrl) {
        this.networkUrl = networkUrl == null ? null : networkUrl.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public byte[] getFileContext() {
        return fileContext;
    }

    public void setFileContext(byte[] fileContext) {
        this.fileContext = fileContext;
    }

    @Override
    public String toString() {
        return "FileStorage{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileSuffixName='" + fileSuffixName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", networkUrl='" + networkUrl + '\'' +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                ", updateTime=" + updateTime +
                ", updateUser='" + updateUser + '\'' +
                ", isValid=" + isValid +
                ", fileContext=" + Arrays.toString(fileContext) +
                '}';
    }
}