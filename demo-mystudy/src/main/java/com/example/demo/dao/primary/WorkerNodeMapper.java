package com.example.demo.dao.primary;

import com.baidu.fsg.uid.worker.entity.WorkerNode;

public interface WorkerNodeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkerNode record);

    int insertSelective(WorkerNode record);

    WorkerNode selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkerNode record);

    int updateByPrimaryKey(WorkerNode record);
}