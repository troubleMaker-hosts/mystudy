package com.example.demo.dao;

import java.util.List;

/**
 * @ClassName: BaseMapper
 * @Description:    Mapper 基类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/06/21 03:59
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
public interface BaseMapper<T> {
    /**
     * 批量插入
     * @param dataList  数据集
     * @return  成功数量 (dataList.size())
     */
    Integer batchInsert(List<T> dataList);
}
