package com.example.demo.service;

import java.util.HashMap;

/**
 * @ClassName: SysScheduleTaskInfoService
 * @Description: SysScheduleTaskInfoService 层接口约束
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/11/19 01:14
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public interface SysScheduleTaskInfoService {
    /**
     * 开启所有 定时器(初始化时候使用)
     * @return 开启是定时器数量
     */
    void oprenAllScheduleTask();

    /**
     * 根据id 开启定时器
     * @param id SysScheduleTaskInfo的 主键 id
     * @param parameterMap 参数map
     * @return 是否开启成功
     */
    Boolean openScheduleTaskById(Integer id, HashMap<String, Object> parameterMap);

    /**
     * 根据id 重置并开启定时器
     * @param id SysScheduleTaskInfo的 主键 id
     * @param parameterMap 参数map
     * @return 是否重置成功
     */
    Boolean resetScheduleTaskById(Integer id, HashMap<String, Object> parameterMap);

    /**
     * 关闭所有 定时器
     * @return 关闭是定时器数量
     */
    Integer closeAllScheduleTask();

    /**
     * 根据id 关闭定时器
     * @param id SysScheduleTaskInfo的 主键 id
     * @return 是否关闭成功
     */
    Boolean closeScheduleTaskById(Integer id);

    /**
     * 根据 id 调用执行 该 定时器 方法 一次(只执行一次)
     * @param id SysScheduleTaskInfo的 主键 id
     * @return 是否 运行成功
     */
    void runScheduleTaskOnce(Integer id);
}
