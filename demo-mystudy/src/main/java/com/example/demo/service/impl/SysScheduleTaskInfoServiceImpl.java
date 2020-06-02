package com.example.demo.service.impl;

import com.example.demo.dao.SysScheduleTaskInfoMapper;
import com.example.demo.model.SysScheduleTaskInfo;
import com.example.demo.scheduletask.ScheduleTaskUtils;
import com.example.demo.service.SysScheduleTaskInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: SysScheduleTaskInfoServiceImpl
 * @Description: SysScheduleTaskInfo Service 层接口  实现
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/11/19 03:23
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
@Service
public class SysScheduleTaskInfoServiceImpl implements SysScheduleTaskInfoService {
    @Autowired
    private SysScheduleTaskInfoMapper scheduleTaskInfoMapper;

    /**
     * 开启所有 定时器(初始化时候使用)
     * @return 开启是定时器数量
     */
    @Override
    public void oprenAllScheduleTask() {
        List<SysScheduleTaskInfo> scheduleTaskInfos = scheduleTaskInfoMapper.listAllScheduleTaskInfo(1);
        ScheduleTaskUtils.init(scheduleTaskInfos);
    }

    /**
     * 根据id 开启定时器
     * @param id SysScheduleTaskInfo的 主键 id
     * @param parameterMap 参数map
     * @return 是否开启成功
     */
    @Override
    public Boolean openScheduleTaskById(Integer id, HashMap<String, Object> parameterMap) {
        SysScheduleTaskInfo scheduleTaskInfo = scheduleTaskInfoMapper.selectByPrimaryKey(id);
        ScheduleTaskUtils.start(scheduleTaskInfo, parameterMap);
        return true;
    }

    /**
     * 根据id 重置并开启定时器
     * @param id SysScheduleTaskInfo的 主键 id
     * @param parameterMap 参数map
     * @return 是否重置成功
     */
    @Override
    public Boolean resetScheduleTaskById(Integer id, HashMap<String, Object> parameterMap) {
        SysScheduleTaskInfo scheduleTaskInfo = scheduleTaskInfoMapper.selectByPrimaryKey(id);
        ScheduleTaskUtils.reset(scheduleTaskInfo, parameterMap);
        return true;
    }

    /**
     * 关闭所有 定时器
     * @return 关闭是定时器数量
     */
    @Override
    public Integer closeAllScheduleTask() {
        List<SysScheduleTaskInfo> scheduleTaskInfos = scheduleTaskInfoMapper.listAllScheduleTaskInfo(1);
        int count = 0;
        for (SysScheduleTaskInfo scheduleTaskInfo : scheduleTaskInfos) {
            ScheduleTaskUtils.stop(scheduleTaskInfo.getId());
            count ++;
        }

        return count;
    }

    /**
     * 根据id 关闭定时器
     * @param id SysScheduleTaskInfo的 主键 id
     * @return 是否关闭成功
     */
    @Override
    public Boolean closeScheduleTaskById(Integer id) {
        ScheduleTaskUtils.stop(id);
        return true;
    }

    /**
     * 根据 id 调用执行 该 定时器 方法 一次(只执行一次)
     * @param id SysScheduleTaskInfo的 主键 id
     * @return 是否 运行成功
     */
    @Override
    public void runScheduleTaskOnce(Integer id) {

    }
}
