package com.example.demo.mytest;

import com.baidu.fsg.uid.impl.DefaultUidGenerator;
import com.baidu.fsg.uid.utils.NetUtils;
import com.baidu.fsg.uid.worker.WorkerNodeType;
import com.baidu.fsg.uid.worker.entity.WorkerNode;
import com.example.demo.DemoMystudyApplication;
import com.example.demo.dao.primary.WorkerNodeMapper;
import com.example.demo.dao.seg.OrderFormMapper;
import com.example.demo.model.OrderForm;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: ShardingTest
 * @Description:    分表 测试
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2021/01/17 03:51
 * @Copyright: Copyright(c)2021 kk All Rights Reservedd
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {DemoMystudyApplication.class})
public class ShardingTest {
    @Autowired
    private OrderFormMapper orderFormMapper;

    @Autowired
    private DefaultUidGenerator uidGenerator;

    @Autowired
    private WorkerNodeMapper workerNodeMapper;


    /**
     * 插入测试
     */
    @Test
    public void insertTest() {
        List<OrderForm> orders = getOrderList(100);
        //orders.forEach(order -> System.out.println(order.toString()));
        System.out.println(orderFormMapper.batchInsert(orders));
    }

    /**
     * 插入 WorkerNode, 并 返回 WorkerNode 的主键(主键是 mysql 自增主键)
     *
     * insert操作时，需要获取主键ID。在mybatis执行完insert操作后，
     * 会传入到insert()里面的那个entity对象，就有ID值了， mybatis自动给赋值上了
     */
    @Test
    public void insertWorkerNode() {
        WorkerNode workerNode = getWorkerNode();
        int i = workerNodeMapper.insertSelective(workerNode);
        System.out.println(" WorkerNode 表 修改记录数 : " + i);
        System.out.println(" WorkerNode 的主键(主键是 mysql 自增主键) id : " + workerNode.getId());
    }

    /**
     * 获取 WorkerNode
     * @return  WorkerNode
     */
    private WorkerNode getWorkerNode() {
        WorkerNode workerNode = new WorkerNode();
        workerNode.setType(WorkerNodeType.ACTUAL.value());
        workerNode.setHostName(NetUtils.getLocalAddress());
        workerNode.setPort(System.currentTimeMillis() + "-" + RandomUtils.nextInt(0, 100000));
        workerNode.setCreated(new Date());
        workerNode.setModified(new Date());
        return workerNode;
    }

    /**
     * 获取 List<OrderForm>
     * @param num List<OrderForm>.size
     * @return  List<OrderForm>
     */
    private List<OrderForm> getOrderList(int num) {
        List<OrderForm> orders = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            OrderForm order = new OrderForm();
            order.setId(uidGenerator.getUID());
            order.setUserId((int)(Math.random() * 10) + 1);
            order.setTotal(new BigDecimal(12.37 + i));
            order.setCurrency((short)1);
            order.setPayType("0101");
            order.setPayStatus((short)1);
            order.setCreateTime(new Date());
            order.setCreateUser("gerry ");
            order.setStatus((short)1);
            order.setUpdateTime(new Date());
            order.setUpdateUser("gerry");
            orders.add(order);
        }
        return orders;
    }
}
