package com.example.demo.controller;

import com.example.demo.config.ThreadPoolConfig;
import com.example.demo.dao.primary.StudentMapper;
import com.example.demo.dao.primary.StudyUserMapper;
import com.example.demo.dao.secondary.EmployeesMapper;
import com.example.demo.model.RespEntity;
import com.example.demo.model.Student;
import com.example.demo.model.StudyUser;
import com.example.demo.study.entity.ThreadCallableTest;
import com.example.demo.utils.RedisOperationUtilImpl;
import com.example.demo.utils.RespEntityUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.*;

/**
 * @ClassName: TestController
 * @Description: controller测试类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2018/11/18 04:06
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
@ApiIgnore
@Controller
public class TestController {
    private static final Logger logger = LogManager.getLogger(TestController.class);

    /**
     * 使用 @Autowired 会有警告, 此处暂时 使用 @Resource
     */
    @Resource
    private RedisOperationUtilImpl redisOperationUtil;

    @Resource
    private StudyUserMapper studyUserMapper;

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private EmployeesMapper employeesMapper;

    /**
     * 添加用户 测试接口
     *
     * @return reids 中所有的 user信息
     */
    @ResponseBody
    @GetMapping("addusertest")
    public Object addUser() {
        logger.error("error日志测试");
        logger.info("info日志测试");

        System.out.println("jinlail");
        StudyUser user = studyUserMapper.selectByPrimaryKey(1);
        System.out.println(user.toString());
        //redis 操作 会产生 很多 debug 日志
        //过期时间(单位:秒),传入 -1 时表示不设置过期时间
        redisOperationUtil.put("user01", user, -1);
        redisOperationUtil.put("user02", user, -1);
        System.out.println("所有的key：" + redisOperationUtil.getKeys());
        System.out.println("查询key为  user01：" + redisOperationUtil.get("user01"));
        System.out.println("查询key=user是否存在：" + redisOperationUtil.isKeyExists("user"));
        System.out.println("查询key=user01是否存在：" + redisOperationUtil.isKeyExists("user01"));
        System.out.println("查询缓存数量：" + redisOperationUtil.count());
        System.out.println(redisOperationUtil.getAll());
        redisOperationUtil.setRedisKey("addRedisKeyTest");
        redisOperationUtil.put("user01_addRedisKeyTest", user, -1);
        System.out.println("查询key为  user01：" + redisOperationUtil.get("user01"));
        System.out.println("查询key为  user01_addRedisKeyTest：" + redisOperationUtil.get("user01_addRedisKeyTest"));
        System.out.println(redisOperationUtil.getAll());
        return redisOperationUtil.getAll();
    }

    /**
     * 删除redis缓存 测试接口
     *
     * @return reids 中所有的 user信息
     */
    @ResponseBody
    @GetMapping("deleteredisdata")
    public Object deleteRedisData() {
        System.out.println("所有的key：" + redisOperationUtil.getKeys());
        System.out.println("查询key=user是否存在：" + redisOperationUtil.isKeyExists("user"));
        System.out.println("查询key=user01是否存在：" + redisOperationUtil.isKeyExists("user01"));
        redisOperationUtil.remove("user");
        redisOperationUtil.remove("user01");
        System.out.println("查询key=user是否存在：" + redisOperationUtil.isKeyExists("user"));
        System.out.println("查询key=user01是否存在：" + redisOperationUtil.isKeyExists("user01"));
        System.out.println("查询缓存数量：" + redisOperationUtil.count());
        System.out.println(redisOperationUtil.getAll());
        return redisOperationUtil.getAll();
    }


    /**
     * 看 前端 传回来的 entity 是否会 带有 默认值
     *
     * 测试结果 :  testAttribute = 999, 默认值 返回给了前端
     *
     * @param studyUser 前端传过来的 studyUser 参数
     * @return  studyUser
     */
    @ResponseBody
    @PostMapping("defaultvaluetest")
    public RespEntity defaultValueTest(@RequestBody  StudyUser studyUser) {
        System.out.println(studyUser.toString());
        return RespEntityUtils.buildSuccResp(studyUser);
    }

    /**
     * 数据库 日志测试 接口
     *
     * @return user信息
     */
    @ResponseBody
    @GetMapping("dblogtest")
    public RespEntity dbLogTest() {
        logger.error("error日志测试");
        logger.info("info日志测试");
        System.out.println("jinlail");
        StudyUser user = studyUserMapper.selectByPrimaryKey(1);
        System.out.println(user.toString());
        return RespEntityUtils.buildSuccResp(user);
    }

    /**
     * post 多数据源测试
     *
     * @param studentId  学生id(mysql 中 student 表)
     * @param employeeId 员工id(orcal中 employees 表)
     * @return 传入参数字符串
     */
    @ResponseBody
    @PostMapping("multiDataSource")
    public RespEntity multiDataSource(@RequestParam("studentId") Integer studentId,
                                      @RequestParam("employeeId") Integer employeeId, @RequestParam("language") String language) {
        System.out.println("studentId : " + studentId + "  ; employeeId : " + employeeId);
        Map<String, String> resultMap = new HashMap<>(16);
        resultMap.put("student", studentMapper.selectByPrimaryKey(studentId).toString());
        resultMap.put("employee", employeesMapper.selectByPrimaryKey(employeeId).toString());
        resultMap.put("language", language);
        return RespEntityUtils.buildSuccResp(resultMap);
    }

    /**
     * 多线程测试
     *
     * @return 测试标志
     */
    @ResponseBody
    @GetMapping("threadTest")
    public String threadTest() {
        logger.error("error日志测试");
        logger.info("info日志测试");

        //多线程 测试
        //无 返回结果线程
        ThreadPoolExecutor executor = ThreadPoolConfig.getThreadPoolExecutor();
        //等待队列
        System.out.println("executor.getQueue().size() : " + executor.getQueue().size());
        //注意 线程数量 以及 线程池处理 异常的 规则(自己设置的)
        for (int i = 0; i < executor.getMaximumPoolSize(); i++) {
            System.out.println("executor.getActiveCount() : " + executor.getActiveCount() + " executor.getTaskCount() : " + executor.getTaskCount());
            ThreadPoolConfig.execute(new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " : executor.getTaskCount() : " + executor.getTaskCount());
                    System.out.println(Thread.currentThread().getName() + " : executor.getActiveCount() : " + executor.getActiveCount());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("无 返回结果线程 ------  进入 : " + Thread.currentThread().getName());
                }
            }));
        }
        Future<String> callableThreadTest = ThreadPoolConfig.submit(new ThreadCallableTest());
        String reslut = null;
        try {
            System.out.println(Thread.currentThread().getName());
            System.out.println("------");
            reslut = callableThreadTest.get(7, TimeUnit.SECONDS);
            System.out.println(reslut);
            System.out.println("+++++++");
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            e.printStackTrace();
        }
        if (reslut != null) {
            return reslut;
        }
        return "test--h5";
    }

    /**
     * post 传入参数测试
     *
     * @param parmerterOne 参数一
     * @param parmerterTwo 参数二
     * @return 传入参数字符串
     * <p>
     * 注意 : 此种 方式 实际上 接收的 多参数 是 get (不能就收 post 方法请求的参数) 方法的 多参数,
     * 如果前端 只能 使用 post 方法请求, 可以用 map 或实体类接收
     */
    @ResponseBody
    @PostMapping("testpost")
    public String testPost(@RequestParam("parameterOne") String parmerterOne,
                           @RequestParam("parameterTwo") Integer parmerterTwo) {
        System.out.println(parmerterTwo.getClass().getName());
        String integerClass = "java.lang.Integer";
        if (integerClass.equals(parmerterTwo.getClass().getName())) {
            System.out.println(parmerterTwo + " : 的类型是 : " + parmerterTwo.getClass().getName());
        }
        return "test--post----parameterTrue  parameterOne : " + parmerterOne + " ; parameterTwo : " + parmerterTwo;
    }

    /**
     * 传入多个参数时 用 map 接收
     *
     * @param paramMap map类型的参数
     * @return 多参数 map 接收 测试结果
     * <p>
     * 注意 : 此种 方式 实际上 接收的 多参数 是 get (不能就收 post 方法请求的参数) 方法的 多参数,
     * 如果前端 只能 使用 post 方法请求, 可以用 map 或实体类接收
     */
    @ResponseBody
    @PostMapping("mapPost")
    public RespEntity mapPost(@RequestBody Map<String, List<String>> paramMap) {
        if (!Objects.isNull(paramMap) && !paramMap.isEmpty()) {
            for (Map.Entry<String, List<String>> stringListEntry : paramMap.entrySet()) {
                System.out.println("key : ");
                System.out.println(stringListEntry.getKey());
                System.out.println("value : ");
                stringListEntry.getValue().forEach(System.out::println);
            }
            return RespEntityUtils.buildSuccResp("成功");
        }
        return RespEntityUtils.buildFailResp("失敗");
    }

    /**
     * 多线程测试
     *
     * @return 该线程执行时间 信息
     */
    @ResponseBody
    @GetMapping("multithreadingTest")
    public String multithreadingTest() {
        String beginDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        System.out.println("beginDate : " + beginDate);
        Thread timeConsumingThread = new Thread(new TimeConsumingThread());
        //1。start():
        //Java API中对于该方法的介绍：
        //使该线程开始执行；Java 虚拟机调用该线程的 run 方法。
        //结果是两个线程并发地运行；当前线程（从调用返回给 start 方法）和另一个线程（执行其 run 方法）。
        //多次启动一个线程是非法的。特别是当线程已经结束执行后，不能再重新启动。
        //用start方法来启动线程，真正实现了多线程运行，这时无需等待run方法体中的代码执行完毕而直接继续执行后续的代码。
        // 通过调用Thread类的 start()方法来启动一个线程，
        // 这时此线程处于就绪（可运行）状态，并没有运行，
        // 一旦得到cpu时间片，就开始执行run()方法，这里的run()方法 称为线程体，
        // 它包含了要执行的这个线程的内容，Run方法运行结束，此线程随即终止。
        //
        //2。run():
        //Java API中对该方法的介绍：
        //如果该线程是使用独立的 Runnable 运行对象构造的，则调用该 Runnable 对象的 run 方法；否则，该方法不执行任何操作并返回。
        //Thread 的子类应该重写该方法。
        //run()方法只是类的一个普通方法而已，如果直接调用Run方法，程序中依然只有主线程这一个线程，
        // 其程序执行路径还是只有一条，还是要顺序执行，还是要等待run方法体执行完毕后才可继续执行下面的代码
        timeConsumingThread.start();
        //.run 会直接调用 run 方法
        //timeConsumingThread.run();
        String endDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        System.out.println("endDate : " + endDate);
        return "执行时间 : " + beginDate + "---------" + endDate;
    }

    /**
     * 耗时的 线程
     */
    class TimeConsumingThread implements Runnable {
        @Override
        public void run() {
            System.out.println("耗时的 线程 执行开始 : " + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
            //消耗时间 大于 3 s , 大概 10s
            for (int i = 0; i < 100000; i++) {
                Student student = studentMapper.selectByPrimaryKey(1);
            }
            System.out.println("耗时的 线程 执行结束 : " + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        }
    }


}
