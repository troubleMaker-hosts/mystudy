package com.example.demo.controller;

import com.example.demo.model.RequestEntity;
import com.example.demo.model.RespEntity;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import com.example.demo.utils.RespEntityUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: StudentController
 * @Description: StudentController 层
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/04/06 06:01
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
@ApiIgnore
@Controller
@RequestMapping("student")
@Log4j2
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     *  插入数据
     * @param student    记录的数据
     * @return  插入数据结果
     */
    @ResponseBody
    @PostMapping("insert")
    public RespEntity insert(@RequestBody RequestEntity<Student> student) {
        System.out.println(student.getData().getName().length());
        log.info("注解 -- 日志 --- test : student-----insert : {}", student.toString());
        return RespEntityUtils.buildSuccResp(studentService.insert(student.getData()));
    }

    /**
     *  通过 name 修改 student
     * @param student    记录的数据
     * @return  修改数据结果
     */
    @ResponseBody
    @PostMapping("updateByName")
    public RespEntity updateByName(@RequestBody RequestEntity<Student> student) {
        System.out.println(student.getData().getName().length());
        log.info("注解 -- 日志 --- test : student-----update : [{}]", student.toString());
        return RespEntityUtils.buildSuccResp(studentService.updateByName(student.getData()));
    }

    /**
     * 根据条件(name, age, sex) 模糊 查询 student
     * @param name  姓名
     * @param age   年龄
     * @param sex   性别
     * @return  sutdents
     */
    @ResponseBody
    @PostMapping("findStudent")
    public List<Student> findStudent(@RequestParam("name") String name,
                                     @RequestParam("age") Integer age,
                                     @RequestParam("sex") String sex) {
        return studentService.findStudentByCondition(name, age, sex);
    }

     /**
     *  根据主键删除
     * @param idMap 主键id
     * @return  删除是否成功(1: 成功, 0 失败)
     */
     @ResponseBody
    @PostMapping("delete")
    public RespEntity delete(@RequestBody Map<String, Integer> idMap) {
        return RespEntityUtils.buildSuccResp(studentService.delete(idMap.get("id")));
    }
}
