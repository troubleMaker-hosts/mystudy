package com.example.demo.dao.primary;

import com.baidu.fsg.uid.worker.entity.WorkerNode;

public interface WorkerNodeMapper {
    int deleteByPrimaryKey(Long id);

    /**
     * 插入 WorkerNode, 返回 主键id
     * @param record    WorkerNode
     * @return  主键id
     */
    int insertSelective(WorkerNode record);

    WorkerNode selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkerNode record);
}