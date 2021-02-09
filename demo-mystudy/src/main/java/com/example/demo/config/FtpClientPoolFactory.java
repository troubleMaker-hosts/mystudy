package com.example.demo.config;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: FtpClientPoolFactory
 * @Description:
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/09/02 02:48
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
@Configuration
public class FtpClientPoolFactory extends BasePooledObjectFactory<FtpUtil> {
    /**
     * 日志(log4j)
     */
    private static final Logger LOGGER = LogManager.getLogger(FtpClientPoolFactory.class);

    /**
     * 默认 配置 ftp 字符集
     */
    @Value("${ftp.default-charset}")
    private String defaultCharset;

    /**
     * 默认 配置 ftp 超时时间
     */
    @Value("${ftp.default-timeout}")
    private int defaultTimeout;

    /**
     * 默认 配置 ftp 初始化时ftp服务器路径(根目录)
     */
    @Value("${ftp.ftpBasePath}")
    private String ftpBasePath;

    /**
     * 默认 配置 ftp 主机名(ip)
     */
    @Value("${ftp.hostname}")
    private String hostname;

    /**
     * 默认 配置 ftp 端口号
     */
    @Value("${ftp.port}")
    private Integer port;

    /**
     * 默认 配置 ftp 用户名
     */
    @Value("${ftp.username}")
    private String username;

    /**
     * 默认 配置 ftp 密码
     */
    @Value("${ftp.password}")
    private String password;

    /**
     * 使用单例 创建 ftpClientPoolConfig
     */
    private volatile static FtpClientPoolConfig ftpClientPoolConfig;

    @Autowired
    private FtpClientPoolFactory ftpClientPoolFactory;


    /**
     * 创建 FtpUtil (不推荐使用)
     * @return  FtpUtil
     */
    @Deprecated
    @Override
    public FtpUtil create() {
        return FtpUtil.createFtpClient(hostname, port, username, password, ftpBasePath, defaultCharset);
    }

    /**
     * 通过 FtpClientPoolConfig(系统默认 的 ftp 线程池) 创建 FtpUtil (受 FtpClientPoolConfig 管理)
     * @return  FtpUtil
     * @throws Exception    GenericObjectPool.borrowObject()方法的异常
     */
    public FtpUtil createByGenericObjectPool() throws Exception {
        return createByGenericObjectPool(getFtpClientPoolConfig(ftpClientPoolFactory));
    }

    @Override
    public PooledObject<FtpUtil> wrap(FtpUtil ftpUtil) {
        return new DefaultPooledObject<>(ftpUtil);
    }

    /**
     * 通过 FtpClientPoolConfig(形参(可用户自定义) 的 ftp 线程池) 创建 FtpUtil (受 FtpClientPoolConfig 管理)
     * @param genericObjectPool GenericObjectPool<FtpUtil>
     * @return  FtpUtil
     * @throws Exception    GenericObjectPool.borrowObject()方法的异常
     */
    public FtpUtil createByGenericObjectPool(GenericObjectPool<FtpUtil> genericObjectPool) throws Exception {
        if (genericObjectPool.getNumActive() >= genericObjectPool.getMaxTotal()) {
            LOGGER.error("此FtpPool 没有空闲 ftpClient, 不能再创建FtpClient. NumActive : " + genericObjectPool.getNumActive());
            throw new RuntimeException("此FtpPool 没有空闲 ftpClient, 不能再创建FtpClient. NumActive : " + genericObjectPool.getNumActive());
        }
        return genericObjectPool.borrowObject();
    }

    /**
     * 通过 双检锁 创建 FtpClientPoolConfig 单例
     * @param factory   FtpClientPoolFactory
     * @return  FtpClientPoolConfig 单例
     */
    private FtpClientPoolConfig getFtpClientPoolConfig(BasePooledObjectFactory factory) {
        if (ftpClientPoolConfig == null) {
            synchronized (FtpClientPoolFactory.class) {
                if (ftpClientPoolConfig == null) {
                    GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
                    //获取连接超时时间
                    genericObjectPoolConfig.setMaxWaitMillis(defaultTimeout);
                    ftpClientPoolConfig = new FtpClientPoolConfig(factory, genericObjectPoolConfig);
                }
            }
        }
        return ftpClientPoolConfig;
    }
}
