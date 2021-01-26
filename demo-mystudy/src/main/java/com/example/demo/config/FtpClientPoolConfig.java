package com.example.demo.config;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.AbandonedConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @ClassName: FtpClientPoolConfig
 * @Description:    ftp 连接池
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/08/27 02:40
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
public class FtpClientPoolConfig extends GenericObjectPool<FtpUtil> {
    private static final Logger LOGGER = LogManager.getLogger(FtpClientPoolConfig.class);
    public FtpClientPoolConfig(PooledObjectFactory<FtpUtil> factory) {
        super(factory);
    }

    public FtpClientPoolConfig(PooledObjectFactory<FtpUtil> factory, GenericObjectPoolConfig config) {
        super(factory, config);
    }

    public FtpClientPoolConfig(PooledObjectFactory<FtpUtil> factory, GenericObjectPoolConfig config, AbandonedConfig abandonedConfig) {
        super(factory, config, abandonedConfig);
    }
}
