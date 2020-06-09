package com.example.demo.controller;

import com.example.demo.dao.primary.StudentMapper;
import com.example.demo.dao.primary.StudyUserMapper;
import com.example.demo.model.RespEntity;
import com.example.demo.model.StudyUser;
import com.example.demo.model.SysScheduleTaskInfo;
import com.example.demo.service.SysScheduleTaskInfoService;
import com.example.demo.utils.RespEntityUtils;
import com.example.demo.utils.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;

/**
 * @ClassName: SysScheduleTaskInfoController
 * @Description:
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/11/18 04:06
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
@ApiIgnore
@Controller
@RequestMapping("shceduleTask")
public class SysScheduleTaskInfoController {
    /**
     * 注入 applicationContext
     *
     * Java变量的初始化顺序为：静态变量或静态代码块–>实例变量或初始化代码块–>构造方法 - >@Autowired
     */
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SysScheduleTaskInfoService scheduleTaskInfoService;

    /**
     * 测试:
     * static 下 @Autowired 不能注入
     */
    @Autowired
    private static StudentMapper STUDENT_MAPPER;

    /**
     * 打开所有的 schedule 定时器
     * @return  开启定时器信息
     */
    @ResponseBody
    @PostMapping("openAllSchedule")
    public RespEntity openAllScheduleTask() {
        try {
            scheduleTaskInfoService.oprenAllScheduleTask();
        } catch (Exception e) {
            return RespEntityUtils.buildFailResp("开启所有定时任务成功");
        }
        return RespEntityUtils.buildSuccResp("开启所有定时任务失败");
    }

    /**
     * 打开一个定时器
     * @param scheduleTaskInfo  定时器信息
     * @return  RespEntity
     */
    @ResponseBody
    @PostMapping("openSchedule")
    public RespEntity openScheduleTask(@RequestBody SysScheduleTaskInfo scheduleTaskInfo) {
        //测试
        System.out.println("openSchedule ----------- 进来了");
        try {
            scheduleTaskInfoService.openScheduleTaskById(scheduleTaskInfo.getId(), initParameterMap(50000, "scheduleTask1"));
        } catch (Exception e) {
            return RespEntityUtils.buildFailResp("开启定时任务失败");
        }
        return RespEntityUtils.buildFailResp("开启定时任务成功");
    }


    /**
     * 关闭一个定时器
     * @param scheduleTaskInfo  定时器信息
     * @return  RespEntity
     */
    @ResponseBody
    @PostMapping("closeScheduleTask")
    public RespEntity closeScheduleTask(@RequestBody SysScheduleTaskInfo scheduleTaskInfo) {
        //测试
        System.out.println("closeScheduleTask ----------- 进来了");
        try {
            scheduleTaskInfoService.closeScheduleTaskById(scheduleTaskInfo.getId());
        } catch (Exception e) {
            return RespEntityUtils.buildFailResp("关闭定时任务失败");
        }
        return RespEntityUtils.buildFailResp("关闭定时任务成功");
    }

    /**
     * 关闭所有定时器
     * @return  RespEntity
     */
    @ResponseBody
    @PostMapping("closeAllScheduleTask")
    public RespEntity closeAllScheduleTask() {
        //测试
        System.out.println("closeScheduleTask ----------- 进来了");
        try {
            scheduleTaskInfoService.closeAllScheduleTask();
        } catch (Exception e) {
            return RespEntityUtils.buildFailResp("关闭所有定时任务失败");
        }
        return RespEntityUtils.buildFailResp("关闭所有定时任务成功");
    }

    /**
     * 注解测试:    @RequestBody注解测试
     * @param studyUser 传入参数封装成一个实体类
     * @return  返回标准结果
     */
    @ResponseBody
    @PostMapping("interfaceTest")
    public RespEntity interfaceTest(@RequestBody StudyUser studyUser) {
        System.out.println(STUDENT_MAPPER);
        StudyUserMapper userMapper = applicationContext.getBean(StudyUserMapper.class);
        System.out.println(applicationContext.getBean(StudyUserMapper.class));
        System.out.println(studyUser.getUserId());
        //System.out.println 会调用 String.valueOf(x);  String.valueOf 调用 toString 方法
        System.out.println(userMapper.selectByPrimaryKey(studyUser.getUserId()));
        System.out.println(SpringUtils.getBean(StudyUserMapper.class));
        return RespEntityUtils.buildSuccResp(studyUser.getUserId());
    }

    /**
     *  初始化 测试 定时器的 参数
     * @param count 耗时操作的 次数
     * @param threadName    线程名
     * @return  测试 定时器的 参数
     */
    private HashMap<String, Object> initParameterMap(Integer count, String threadName) {
        HashMap<String, Object> paramMap =  new HashMap<>(16);
        paramMap.put("count", count);
        paramMap.put("threadName", threadName);
        return paramMap;
    }
}
