package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.*;

/**
 * @Description: 线程池 工具类 (test_demo)
 * @ClassName: ThreadPoolUtils
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/09/18 01:49
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
@Configuration
public class ThreadPoolConfig {
    /**
     * corePoolSize:核心线程数(可根据cpu核心数设置)
     */
    private static final int CORE_POOL_SIZE = 3;

    /**
     * maximumPoolSize：线程池所容纳最大线程数(workQueue队列满了之后才开启)
     */
    private static final int MAXIMUM_POOL_SIZE = 6;

    /**
     * keepAliveTime：非核心线程闲置时间超时时长
     */
    private static final int KEEP_ALIVE_TIME = 60;

    /**
     * timeUnit：keepAliveTime的单位
     */
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;

    /**
     * 等待队列，存储还未执行的任务
     * 阻塞队列，超过corePoolSize部分的请求放入这个阻塞队列中等待执行。
     * 阻塞队列分为有界阻塞队列和无界阻塞队列。
     * 在创建阻塞队列时如果我们指定了这个队列的“capacity”则这个队列就是有界的，否则是无界的。
     * 这里有一点需要注意：使用线程池之前请明确是否真的需要无界阻塞队列，
     * 如果阻塞队列是无界的，会导致大量的请求堆积，进而造成内存溢出系统崩溃。
     */
    private static final ArrayBlockingQueue<Runnable> ARRAY_BLOCKING_QUEUE = new ArrayBlockingQueue<>(20);

    /**
     * 线程创建的工厂
     */
    private static final ThreadFactory THREAD_FACTORY = Executors.defaultThreadFactory();

    /**
     * 异常处理机制
     * ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
     * ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
     * ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
     * ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务(请求脱离线程池运行（调用者caller线程来运行这个任务）)
     */
    private static final RejectedExecutionHandler REJECTED_EXECUTION_HANDLER = new ThreadPoolExecutor.AbortPolicy();

    /**
     * 普通(线程数固定) 线程池
     */
    private static ThreadPoolExecutor threadPool;

    /**
     * 定时任务 线程池
     */
    private static ThreadPoolTaskScheduler threadPoolTaskScheduler;

    /**
     * 获取 普通线(线程数固定)程池
     * @return ThreadPoolExecutor 普通綫程池
     */
    @Bean(name = "threadPool")
    public static ThreadPoolExecutor getThreadPoolExecutor() {
        if (threadPool != null) {
            return threadPool;
        } else {
            synchronized (ThreadPoolConfig.class) {
                if (threadPool == null) {
                    threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME,
                            TIME_UNIT, ARRAY_BLOCKING_QUEUE, THREAD_FACTORY, REJECTED_EXECUTION_HANDLER);
                }
                return threadPool;
            }
        }
    }

    /**
     *  获取 定时器 线程池
     * @return threadPoolTaskScheduler 定时器 线程池
     */
    @Bean(name = "taskThreadPool")
    public static ThreadPoolTaskScheduler getThreadPoolTaskScheduler() {
        if (threadPoolTaskScheduler != null) {
            return threadPoolTaskScheduler;
        } else {
            synchronized (ThreadPoolConfig.class) {
                if (threadPoolTaskScheduler == null) {
                    threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
                    threadPoolTaskScheduler.setPoolSize(20);
                    threadPoolTaskScheduler.setThreadNamePrefix("taskSchedule-");
                    //用来设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
                    threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);
                    //该方法用来设置线程池中任务的等待时间，
                    // 如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住。
                    threadPoolTaskScheduler.setAwaitTerminationSeconds(300);
                    //线程池对拒绝任务的处理策略：这里采用了CallerRunsPolicy策略，
                    // 当线程池没有处理能力的时候，该策略会直接在 execute 方法的调用线程中运行被拒绝的任务；
                    // 如果执行程序已关闭，则会丢弃该任务
                    threadPoolTaskScheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
                }
                return threadPoolTaskScheduler;
            }
        }
    }


    /**
     * 开启一个无返回结果的线程
     *
     * @param runnable Runnable 类型的 线程
     */
    public static void execute(Runnable runnable) {
        getThreadPoolExecutor().execute(runnable);
    }

    /**
     * 开启一个有返回结果的线程
     *
     * @param callable Callable 类型的 线程
     */
    public static <T> Future<T> submit(Callable<T> callable) {
        return getThreadPoolExecutor().submit(callable);
    }

    /**
     * 把任务移除等待队列
     * @param r 移除 Runnable 类型的 线程
     */
    public static void cancel(Runnable r) {
        if (r != null) {
            getThreadPoolExecutor().getQueue().remove(r);
        }
    }

}
