package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: ShiroUser
 * @Description: shiro 测试  user类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/05/14 23:09
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
public class ShiroUser implements Serializable {
    private static final long serialVersionUID = -6401178671289078055L;

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String pwd;

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
     * 状态 : 是否有效 (1: 有效, 0 : 无效)
     */
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    @Override
    public String toString() {
        return "ShiroUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                ", updateTime=" + updateTime +
                ", updateUser='" + updateUser + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}