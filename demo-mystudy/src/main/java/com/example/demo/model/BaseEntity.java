package com.example.demo.model;

import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName: BaseEntity
 * @Description:    基础Entity
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/09/23 03:21
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    @Transient
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否有效 (1: 有效, 0 : 无效)
     */
    private Integer isValid;

    /**
     * 备注
     */
    private String remarks;

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
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
        this.remarks = remarks;
    }
}
