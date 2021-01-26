package com.example.demo.config;

import com.baidu.fsg.uid.impl.DefaultUidGenerator;
import com.baidu.fsg.uid.worker.WorkerIdAssigner;
import com.baidu.fsg.uid.worker.entity.WorkerNode;
import com.example.demo.dao.primary.WorkerNodeMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @ClassName: UidGeneratorConfig
 * @Description:    UidGenerator 配置类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2021/01/23 02:47
 * @Copyright: Copyright(c)2021 kk All Rights Reserved
 */
@Log4j2
@Configuration
@ConfigurationProperties(prefix = "uid.generator")
public class UidGeneratorConfig implements WorkerIdAssigner {
    /**
     * 当前时间，相对于时间基点 epochStr 的增量值
     */
    private int timeBits;

    /**
     * 机器id。内置实现为在启动时由数据库分配，默认分配策略为用后即弃，后续可提供复用策略。
     */
    private int workerBits;

    /**
     * 每秒下的并发序列
     */
    private int seqBits;

    /**
     * 时间基点
     */
    private String epochStr;

    @Autowired
    private WorkerNodeMapper workerNodeMapper;

    @Bean
    public DefaultUidGenerator defaultUidGenerator() {
        DefaultUidGenerator uidGenerator = new DefaultUidGenerator();
        uidGenerator.setWorkerIdAssigner(this);
        uidGenerator.setTimeBits(timeBits);
        uidGenerator.setWorkerBits(workerBits);
        uidGenerator.setSeqBits(seqBits);
        uidGenerator.setEpochStr(epochStr);
        return uidGenerator;
    }

    @Override
    @Transactional
    public long assignWorkerId() {
        // build worker node entity
        WorkerNode workerNode = buildWorkerNode();

        workerNode.setCreated(new Date());
        workerNode.setModified(new Date());
        //add worker node for new (ignore the same IP + PORT)
        workerNodeMapper.insertSelective(workerNode);
        log.info("Add worker node: " + workerNode.toString());
        return workerNode.getId();
    }
}
