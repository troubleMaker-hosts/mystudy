package com.example.demo.dao.primary;

import com.example.demo.dao.BaseMapper;
import com.example.demo.model.StudyUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName: StudyUserMapper
 * @Description:    StudyUser dao 层
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2018/11/18 04:06
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
@Mapper
public interface StudyUserMapper extends BaseMapper<StudyUser> {
    /**
     *  根据主键删除
     * @param userId 主键id
     * @return  删除是否成功(1: 成功, 0 失败)
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     * 插入记录 StudyUser
     * @param record StudyUser 实体类
     * @return  插入是否成功
     */
    int insertSelective(StudyUser record);

    /**
     * 根据主键查询 StudyUser
     * @param userId 主键id
     * @return  StudyUser
     */
    StudyUser selectByPrimaryKey(Integer userId);

    /**
     * 根据主键 修改 StudyUser
     * @param record    StudyUser
     * @return  是否修改成功
     */
    int updateByPrimaryKeySelective(StudyUser record);
}