package com.example.demo.controller;

import com.example.demo.model.ShiroUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @ClassName: ShiroTestController
 * @Description: shiro 和 单点登陆 测试controller
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/05/19 02:25
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
@ApiIgnore
@Controller
public class ShiroTestController {
    private Logger logger = LogManager.getLogger();

    @GetMapping("login")
    public String login() {
        logger.info("静态界面测试------");
        return "/login/login";
    }

    @ResponseBody
    @PostMapping(value = "userlogin")
    public String userLogin(@RequestBody ShiroUser user) {
        System.out.println("访问了后端 /userlogin  请求");
        System.out.println(user.toString());
        //UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPwd(), false);
        //Subject currentUser = SecurityUtils.getSubject();
        //try {
        //    currentUser.login(token);
        //    //此步将 调用realm的认证方法
        //} catch (Exception e) {
        //    return "login";
        //}
        System.out.println("--------------menu");
        return "menu";
    }


    @GetMapping("menu")
    public String menu() {
        return "/menu";
    }

    @GetMapping("user/user_one")
    public String userOne() {
        return "/user/user_one";
    }

    @GetMapping("user/user_two")
    public String userTwo() {
        return "/user/user_two";
    }

    @GetMapping("admin/admin_one")
    public String adminOne() {
        return "/admin/admin_one";
    }

    @GetMapping("admin/admin_two")
    public String adminTwo() {
        return "/admin/admin_two";
    }
}
