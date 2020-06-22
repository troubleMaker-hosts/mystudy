package com.example.demo.dao.primary;

import com.example.demo.dao.BaseMapper;
import com.example.demo.model.ShiroUser;

public interface ShiroUserMapper extends BaseMapper<ShiroUser> {
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
    int insert(ShiroUser record);

    /**
     * 根据主键查询 记录
     * @param id 主键id
     * @return  实体对象
     */
    ShiroUser selectByPrimaryKey(Integer id);

    /**
     * 根据主键 修改 记录
     * @param record    实体类
     * @return  更新是否成功
     */
    int updateByPrimaryKey(ShiroUser record);
}