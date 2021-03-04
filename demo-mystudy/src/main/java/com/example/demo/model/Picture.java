package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", pictureName='" + pictureName + '\'' +
                ", pictureType='" + pictureType + '\'' +
                ", storageType=" + storageType +
                ", tftpUrl='" + tftpUrl + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createTime=" + createTime +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime=" + updateTime +
                ", isValid=" + isValid +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}