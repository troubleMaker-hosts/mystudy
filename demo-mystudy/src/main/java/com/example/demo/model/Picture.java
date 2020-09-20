package com.example.demo.model;

import java.util.Date;

public class Picture {
    private Integer id;

    private String pictureName;

    private String pictureType;

    private Integer storageType;

    private String tftpUrl;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private Integer isValid;

    private String remarks;

    private byte[] pictureContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName == null ? null : pictureName.trim();
    }

    public String getPictureType() {
        return pictureType;
    }

    public void setPictureType(String pictureType) {
        this.pictureType = pictureType == null ? null : pictureType.trim();
    }

    public Integer getStorageType() {
        return storageType;
    }

    public void setStorageType(Integer storageType) {
        this.storageType = storageType;
    }

    public String getTftpUrl() {
        return tftpUrl;
    }

    public void setTftpUrl(String tftpUrl) {
        this.tftpUrl = tftpUrl == null ? null : tftpUrl.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public byte[] getPictureContent() {
        return pictureContent;
    }

    public void setPictureContent(byte[] pictureContent) {
        this.pictureContent = pictureContent;
    }
}