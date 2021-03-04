package com.example.demo.utils;

import com.example.demo.config.ThreadPoolConfig;
import com.example.demo.model.SysScheduleTaskInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * @ClassName: ScheduleTaskManager
 * @Description: 定时器 管理类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/10/25 02:51
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
public class ScheduleTaskUtils {
    private static Logger logger = LogManager.getLogger(ScheduleTaskUtils.class);

    /**
     * 定时任务 线程池
     */
    private static ThreadPoolTaskScheduler threadPoolTaskScheduler = ThreadPoolConfig.getThreadPoolTaskScheduler();

    /**
     * 存放 所有 定时器 任务
     */
    private static final ConcurrentHashMap<String, ScheduledFuture> TASK_MAP = new ConcurrentHashMap<>(8);

    /**
     * 开始执行 任务
     * @param taskInfo 任务配置详情(实体类)
     */
    public static void start(SysScheduleTaskInfo taskInfo, HashMap<String, Object> paramMap) {
        if (TASK_MAP.containsKey(taskInfo.getId())) {
            logger.debug("taskSchedule : " + taskInfo.getId() + " (" + taskInfo.getScheduleTaskName() + ")" + " :  is running");
        }
        executeJob(taskInfo, paramMap);
    }

    /**
     * 根据 任务 id 停止
     * @param taskId 任务配置详情id
     */
    public static void stop(Integer taskId) {
        ScheduledFuture scheduledFuture = TASK_MAP.get(taskId);
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            logger.info("taskSchedule cancel : " + taskId);
        } else {
            logger.info("taskSchedule : " + taskId + " : is not running ");
        }
    }

    /**
     * 修改corn配置，先取消再重新启动
     * @param taskInfo 任务配置详情(实体类)
     */
    public static void reset(SysScheduleTaskInfo taskInfo, HashMap<String, Object> paramMap) {
        ScheduledFuture scheduledFuture = TASK_MAP.get(taskInfo.getId());
        if (Objects.nonNull(scheduledFuture)) {
            //取消任务
            scheduledFuture.cancel(true);
            logger.info("taskSchedule : " +  taskInfo.getId() + " (" + taskInfo.getScheduleTaskName() + ")" + " : cancel success");
        } else {
            logger.info("taskSchedule : " +  taskInfo.getId() + " (" + taskInfo.getScheduleTaskName() + ")" + " : 未运行, 将直接启动该任务");
        }
        executeJob(taskInfo, paramMap);
        logger.info("taskSchedule : " + taskInfo.getId() + " (" + taskInfo.getScheduleTaskName() + ") :" + " start success" );
    }

    /**
     * 启动初始化启动所有 task
     * @param taskInfos 任务配置详情(实体类)集合
     */
    public static void init(List<SysScheduleTaskInfo> taskInfos) {
        if (!CollectionUtils.isEmpty(taskInfos)) {
            taskInfos.forEach(taskInfo -> executeJob(taskInfo, new HashMap<String, Object>(taskInfos.size())));
            logger.info("taskSchedule : 初始化完成.");
        }
    }

    /**
     * 执行 任务
     * @param taskInfo 任务配置详情(实体类)
     * @param paramMap 执行 参数
     */
    private static void executeJob(SysScheduleTaskInfo taskInfo, HashMap<String, Object> paramMap) {
        if (Objects.nonNull(taskInfo)) {
            Runnable runnable;
            try {
                //反射
                Class<?> targetClass = Class.forName(taskInfo.getScheduleTaskPackageName() + "." + taskInfo.getScheduleTaskClassname());
                Method targetMethod = targetClass.getMethod(taskInfo.getScheduleTaskMethodName(), HashMap.class);
                targetMethod.setAccessible(true);
                runnable = () -> {
                    try {
                        //此处 bean 从 springBean容器中拿
                        targetMethod.invoke(SpringUtils.getBean(targetClass), paramMap);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                        logger.error("taskSchedule : " + taskInfo.getId() + " (" + taskInfo.getScheduleTaskName() + ") :"
                                + "定时任务方法调用失败 : " + e.getMessage());
                    }
                };
                //添加 到 定时器 map  中
                ScheduledFuture<?> schedule = threadPoolTaskScheduler.schedule(runnable, new CronTrigger(taskInfo.getScheduleTaskCron()));
                if (Objects.nonNull(schedule)) {
                    TASK_MAP.put(taskInfo.getId().toString(), schedule);
                    logger.info("taskSchedule : " + taskInfo.getId() + " (" + taskInfo.getScheduleTaskName() + ") :" + " is running...");
                } else {
                    logger.error("taskSchedule : " + taskInfo.getId() + " (" + taskInfo.getScheduleTaskName() + ") :" +
                            " 定时任务创建失败, 建议检查 cron : " + taskInfo.getScheduleTaskCron());
                }
            } catch (Exception e) {
                logger.error("taskSchedule : " + taskInfo.getId() + " (" + taskInfo.getScheduleTaskName() + ") : 任务调度失败 : " + e.getMessage());
            }
        } else {
            logger.error("taskSchedule : 执行 taskSchedule 失败 : SysScheduleTaskInfo 为 null.");
        }

    }

}
