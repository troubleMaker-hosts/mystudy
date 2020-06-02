package com.example.demo.dao;

import com.example.demo.model.AccountNumber;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: AccountNumberMapper
 * @Description:    AccountNumber dao 层
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/11/18 04:06
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
@Mapper
public interface AccountNumberMapper {
    /**
     *  根据主键删除 记录
     * @param id 主键id
     * @return  删除是否成功(1: 成功, 0 失败)
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入记录
     * @param record 实体类
     * @return  插入是否成功
     */
    int insertSelective(AccountNumber record);

    /**
     * 查找所有 AccountNumber
     * @return  所有 AccountNumber
     */
    List<AccountNumber> findAll();

    /**
     * 根据主键查询 记录
     * @param id 主键id
     * @return  实体对象
     */
    AccountNumber selectByPrimaryKey(Integer id);

    /**
     * 通过 systematicName 模糊查询
     * @param systematicName    系统名
     * @return  所有 系统名 包含 systematicName 的 AccountNumber
     */
    List<AccountNumber> findBySystematicName(@Param("systematicName") String systematicName);

    /**
     * 根据主键 修改 记录
     * @param record    实体类
     * @return  更新是否成功
     */
    int updateByPrimaryKeySelective(AccountNumber record);
}