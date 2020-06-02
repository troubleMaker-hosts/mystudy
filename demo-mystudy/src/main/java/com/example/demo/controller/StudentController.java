package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
@Controller
@RequestMapping("student")
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
    public Integer insert(@RequestBody Student student) {
        return studentService.insert(student);
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
    public int delete(@RequestBody Map<String, Integer> idMap) {
        return studentService.delete(idMap.get("id"));
    }
}
