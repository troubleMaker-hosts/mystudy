package com.example.demo.model;

import java.util.Date;

/**
 * @ClassName: SysScheduleTaskInfo
 * @Description: SysScheduleTaskInfo 系统schedule定时任务信息 实体类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/11/18 04:06
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class SysScheduleTaskInfo {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 任务名
     */
    private String scheduleTaskName;

    /**
     * 任务描述
     */
    private String scheduleTaskDescription;

    /**
     * 任务所在类名
     */
    private String scheduleTaskClassname;

    /**
     * 任务所在包名
     */
    private String scheduleTaskPackageName;

    /**
     * 任务 方法名
     */
    private String scheduleTaskMethodName;

    /**
     * 执行频率(cron表达式)
     */
    private String scheduleTaskCron;

    /**
     * 任务开关
     */
    private Integer scheduleTaskSwitch;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 修改时间
     */
    private Date updateDate;

    /**
     * 是否有效
     */
    private Integer validflag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScheduleTaskName() {
        return scheduleTaskName;
    }

    public void setScheduleTaskName(String scheduleTaskName) {
        this.scheduleTaskName = scheduleTaskName == null ? null : scheduleTaskName.trim();
    }

    public String getScheduleTaskDescription() {
        return scheduleTaskDescription;
    }

    public void setScheduleTaskDescription(String scheduleTaskDescription) {
        this.scheduleTaskDescription = scheduleTaskDescription == null ? null : scheduleTaskDescription.trim();
    }

    public String getScheduleTaskClassname() {
        return scheduleTaskClassname;
    }

    public void setScheduleTaskClassname(String scheduleTaskClassname) {
        this.scheduleTaskClassname = scheduleTaskClassname == null ? null : scheduleTaskClassname.trim();
    }

    public String getScheduleTaskPackageName() {
        return scheduleTaskPackageName;
    }

    public void setScheduleTaskPackageName(String scheduleTaskPackageName) {
        this.scheduleTaskPackageName = scheduleTaskPackageName == null ? null : scheduleTaskPackageName.trim();
    }

    public String getScheduleTaskMethodName() {
        return scheduleTaskMethodName;
    }

    public void setScheduleTaskMethodName(String scheduleTaskMethodName) {
        this.scheduleTaskMethodName = scheduleTaskMethodName == null ? null : scheduleTaskMethodName.trim();
    }

    public String getScheduleTaskCron() {
        return scheduleTaskCron;
    }

    public void setScheduleTaskCron(String scheduleTaskCron) {
        this.scheduleTaskCron = scheduleTaskCron == null ? null : scheduleTaskCron.trim();
    }

    public Integer getScheduleTaskSwitch() {
        return scheduleTaskSwitch;
    }

    public void setScheduleTaskSwitch(Integer scheduleTaskSwitch) {
        this.scheduleTaskSwitch = scheduleTaskSwitch;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getValidflag() {
        return validflag;
    }

    public void setValidflag(Integer validflag) {
        this.validflag = validflag;
    }

    @Override
    public String toString() {
        return "SysScheduleTaskInfo{" +
                "id='" + id + '\'' +
                ", scheduleTaskName='" + scheduleTaskName + '\'' +
                ", scheduleTaskDescription='" + scheduleTaskDescription + '\'' +
                ", scheduleTaskClassname='" + scheduleTaskClassname + '\'' +
                ", scheduleTaskPackageName='" + scheduleTaskPackageName + '\'' +
                ", scheduleTaskMethodName='" + scheduleTaskMethodName + '\'' +
                ", scheduleTaskCron='" + scheduleTaskCron + '\'' +
                ", scheduleTaskSwitch=" + scheduleTaskSwitch +
                ", remarks='" + remarks + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createDate=" + createDate +
                ", updateUser='" + updateUser + '\'' +
                ", updateDate=" + updateDate +
                ", validflag=" + validflag +
                '}';
    }
}