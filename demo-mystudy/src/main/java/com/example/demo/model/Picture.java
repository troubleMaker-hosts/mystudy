package com.example.demo.model;

import lombok.Data;

import java.util.Date;

@Data
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
}