package com.example.demo.dao.primary;

import com.example.demo.dao.BaseMapper;
import com.example.demo.model.SysScheduleTaskInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName: SysScheduleTaskInfoController
 * @Description: schedule 定时任务信息 dao 层
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/11/18 04:06
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
@Mapper
public interface SysScheduleTaskInfoMapper extends BaseMapper<SysScheduleTaskInfo> {
    /**
     * 跟据 主键 删除记录
     * @param id 主键id
     * @return 是否删除成功
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入记录
     * @param record 更新的 内容
     * @return 是否新增成功
     */
    int insertSelective(SysScheduleTaskInfo record);

    /**
     * 跟据 主键 查询记录
     * @param id 主键id
     * @return 记录实体类
     */
    SysScheduleTaskInfo selectByPrimaryKey(Integer id);

    /**
     * 查询所有记录(根据是否有效)
     * @param validflag 是否有效
     * @return 记录实体类 集合
     */
    List<SysScheduleTaskInfo> listAllScheduleTaskInfo(Integer validflag);

    /**
     * 跟据 主键 更新记录
     * @param record 主键id
     * @return 是否更新成功
     */
    int updateByPrimaryKeySelective(SysScheduleTaskInfo record);
}