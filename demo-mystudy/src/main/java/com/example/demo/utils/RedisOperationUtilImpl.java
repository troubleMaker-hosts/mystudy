package com.example.demo.utils;

import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: RedisOperationUtilImpl
 * @Description: RedisOperationUtilImpl
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/11/21 03:26
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
@Configuration
public class RedisOperationUtilImpl extends RedisOperationUtil {
    /**
     * 默认的 redisKey
     */
    private String redisKey = "redisTestKey";

    /**
     * 返回 默认 redis 的 key
     * @return 默认 redis 的 key
     */
    @Override
    protected String getRedisKey() {
        return this.redisKey;
    }

    public void setRedisKey(String redisKey) {
        this.redisKey = redisKey;
    }
}
