package com.example.demo.dao.secondary;

import com.example.demo.dao.BaseMapper;
import com.example.demo.model.Employees;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName: AccountNumberMapper
 * @Description:    AccountNumber dao 层
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/06/07 20:14
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public interface EmployeesMapper extends BaseMapper<Employees> {
    /**
     *  根据主键删除 记录
     * @param employeeId 主键id
     * @return  删除是否成功(1: 成功, 0 失败)
     */
    int deleteByPrimaryKey(Integer employeeId);

    /**
     * 插入记录
     * @param record 实体类
     * @return  插入是否成功
     */
    int insert(Employees record);

    /**
     * 根据主键查询 记录
     * @param employeeId 员工id(主键id)
     * @return  实体对象
     */
    Employees selectByPrimaryKey(Integer employeeId);

    /**
     * 根据 条件查询
     * @param employees 条件
     * @return  符合条件的Employees
     */
    List<Employees> selectByCondition(Employees employees);

    /**
     * 根据主键 修改 记录
     * @param record    实体类
     * @return  更新是否成功
     */
    int updateByPrimaryKeySelective(Employees record);

    /**
     * merge into Employees (有就更新, 无则添加)
     * @param employeesList List<Employees>
     * @return 变更记录数
     */
    int mergeInto(List<Employees> employeesList);
}