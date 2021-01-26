package com.example.demo.model;

import lombok.Data;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

@Data
public class PersonalInfo {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 中文姓名
     */
    private String cName;

    /**
     * 英文姓名
     */
    private String eName;

    /**
     * 性别(1:男; 2:女)
     */
    private Integer sex;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 证件类型(1:身份证; 2:护照; 3: 驾驶证; 0:其他)
     */
    private Integer certificateType;

    /**
     * 证件号
     */
    private String idNumber;

    /**
     * 电话(手机)号码
     */
    private String phoneNumber;

    /**
     * 民族
     */
    private String nation;

    /**
     * 学历(1:高中; 2:大专; 3:本科; 4:硕士; 5:博士; 6:博士后; 0:其他)
     */
    private Integer education;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 住址
     */
    private String address;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 是否有效(1:有效; 0:无效)
     */
    private Integer isValid;

    /**
     * 备注
     */
    private String remarks;

    @Override
    public String toString() {
        return "PersonalInfo{" +
                "id=" + id +
                ", cName='" + cName + '\'' +
                ", eName='" + eName + '\'' +
                ", sex=" + sex +
                ", birthday=" + DateFormatUtils.format(birthday, "yyyy-MM-dd") +
                ", certificateType=" + certificateType +
                ", idNumber='" + idNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", nation='" + nation + '\'' +
                ", education=" + education +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createTime=" + createTime +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime=" + updateTime +
                ", isValid=" + isValid +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}