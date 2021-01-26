package com.example.demo.config;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.demo.dao.BaseMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: EasyExcelListener
 * @Description: easyExcel读取 excel 监听器 (防止 oom)
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/06/21 03:31
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
public class EasyExcelListener<T> extends AnalysisEventListener<T> {
    private static final Logger LOGGER = LogManager.getLogger(EasyExcelListener.class);

    /**
     * Mapper 基类, 此处 用来 批量出入 数据
     */
    private BaseMapper baseMapper;

    /**
     * 每次处理 T 的 记录数 (防止 oom), 方便内存回收
     */
    private static final int BATCH_COUNT = 5;

    /**
     * 保存 T 的 数据
     */
    private List<T> dataList = new ArrayList<>();

    public EasyExcelListener(BaseMapper dataDao) {
        this.baseMapper = dataDao;
    }

    /**
     *  每一条数据解析都会来调用 此 方法
     *  此 方法 根据 需求修改
     * @param data  one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context   A context is the main anchorage point of a excel reader.{@link AnalysisContext}
     */
    @Override
    public void invoke(T data, AnalysisContext context) {
        dataList.add(data);
        // 达到BATCH_COUNT，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (dataList.size() >= BATCH_COUNT) {
            batchInsertData();
            // 存储完成清理 list
            dataList.clear();
        }
    }

    /**
     * 全部解析完 后执行
     * @param context    A context is the main anchorage point of a excel reader.{@link AnalysisContext}
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        batchInsertData();
        LOGGER.info("数据解析完成!");
    }

    /**
     *  批量插入数据
     * @return  插入数据记录 数(BATCH_COUNT)
     */
    private int batchInsertData() {
        int i = 0;
        if (!dataList.isEmpty()) {
            i = baseMapper.batchInsert(dataList);
            LOGGER.info("批量插入 {}, 数量 : {}", dataList.get(0).getClass(), i);
        }
        return i;
    }
}
