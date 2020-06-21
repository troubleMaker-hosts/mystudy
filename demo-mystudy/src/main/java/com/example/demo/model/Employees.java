package com.example.demo.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: AccountNumber
 * @Description: AccountNumber  实体类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/06/07 20:14
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class Employees {
    /**
     * 员工id
     */
    @ExcelProperty(value = "员工id", index = 0)
    private Integer employeeId;

    /**
     * 名
     */
    @ExcelProperty(value = "名", index = 2)
    private String firstName;

    /**
     * 姓
     */
    @ExcelProperty(value = "姓", index = 3)
    private String lastName;

    /**
     * 电子邮件
     */
    @ExcelProperty(value = "电子邮件", index = 4)
    private String email;

    /**
     * 电话号码
     */
    @ExcelProperty(value = "电话号码", index = 5)
    private String phoneNumber;

    /**
     * 受雇日期
     */
    @DateTimeFormat("yyyy/MM/dd")
    @JsonFormat(pattern = "yyyy/MM/dd", locale = "zh", timezone = "GMT+8")
    @ExcelProperty(value = "受雇日期", index = 6)
    private Date hireDate;

    /**
     * 工号
     */
    @ExcelProperty(value = "工号", index = 7)
    private String jobId;

    /**
     * 薪水
     */
    @NumberFormat("####.##")
    @ExcelProperty(value = "薪水", index = 8)
    private BigDecimal salary;

    /**
     * 佣金
     * NumberFormat("#.##%") : 表示 用 百分比 表示
     * 0与#的区别 : 0为此位置不足用0补齐 #为此位置不足就空着
     */
    //@NumberFormat("#.##%")
    //@NumberFormat("#.##")
    @NumberFormat("0.00")
    @ExcelProperty(value = "佣金", index = 9)
    private BigDecimal commissionPct;

    /**
     * 经理id
     */
    @ExcelProperty(value = "经理id", index = 10)
    private Integer managerId;

    /**
     * 部门id
     */
    @ExcelProperty(value = "部门id", index = 11)
    private Short departmentId;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId == null ? null : jobId.trim();
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getCommissionPct() {
        return commissionPct;
    }

    public void setCommissionPct(BigDecimal commissionPct) {
        this.commissionPct = commissionPct;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Short getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Short departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", hireDate=" + hireDate +
                ", jobId='" + jobId + '\'' +
                ", salary=" + salary +
                ", commissionPct=" + commissionPct +
                ", managerId=" + managerId +
                ", departmentId=" + departmentId +
                '}';
    }
}