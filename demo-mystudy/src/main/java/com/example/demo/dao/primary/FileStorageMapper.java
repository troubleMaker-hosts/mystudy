package com.example.demo.dao.primary;

import com.example.demo.dao.BaseMapper;
import com.example.demo.model.FileStorage;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName: FileStorageMapper
 * @Description:    FileStorage dao 层
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/11/18 04:06
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
@Mapper
public interface FileStorageMapper extends BaseMapper<FileStorage> {
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
    int insertSelective(FileStorage record);

    /**
     * 根据主键查询 记录
     * @param id 主键id
     * @return  实体对象
     */
    FileStorage selectByPrimaryKey(Integer id);

    /**
     * 根据主键 修改 记录
     * @param record    实体类
     * @return  更新是否成功
     */
    int updateByPrimaryKeySelective(FileStorage record);


    /**
     * 根据主键 修改 记录(有BLOB)
     * @param record    实体类
     * @return  更新是否成功
     */
    int updateByPrimaryKeyWithBLOBs(FileStorage record);
}