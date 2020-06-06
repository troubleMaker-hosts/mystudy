package com.example.demo.controller;

import com.example.demo.model.Student;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: SwaggerTestController
 * @Description: SwaggerTestController 测试swagger
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/06/05 02:05
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 *
 *
 * swagger2 注解整体说明 :
 *
 *  -@Api：用在请求的类上，表示对类的说明
 *     tags="说明该类的作用，可以在UI界面上看到的注解"
 *     value="该参数没什么意义，在UI界面上也看到，所以不需要配置"
 *
 * -@ApiOperation：用在请求的方法上，说明方法的用途、作用
 *     value="说明方法的用途、作用"
 *     notes="方法的备注说明"
 *
 * - @ApiParam()用于方法，参数，字段说明；
 * 表示对参数的添加元数据（说明或是否必填等）
 *
 * -@ApiImplicitParams：用在请求的方法上，表示一组参数说明
 *     -@ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面
 *         name：参数名
 *         value：参数的汉字说明、解释
 *         required：参数是否必须传
 *         paramType：参数放在哪个地方
 *             · header --> 请求参数的获取：@RequestHeader
 *             · query --> 请求参数的获取：@RequestParam
 *             · path（用于restful接口）--> 请求参数的获取：@PathVariable
 *             · body（不常用）
 *             · form（不常用）
 *         dataType：参数类型，默认String，其它值dataType="Integer"
 *         defaultValue：参数的默认值
 *
 * -@ApiResponses：用在请求的方法上，表示一组响应
 *     -@ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
 *         code：数字，例如400
 *         message：信息，例如"请求参数没填好"
 *         response：抛出异常的类
 *
 * -@ApiModel：用于响应类上，表示一个返回响应数据的信息
 *             （这种一般用在post创建的时候，使用@RequestBody这样的场景，
 *             请求参数无法使用@ApiImplicitParam注解进行描述的时候）
 * -@ApiModelProperty：用在属性上，描述响应类的属性
 *
 * -@ApiIgnore()用于类，方法，方法参数
 * 表示这个方法或者类被忽略
 */
@RestController
@Api(tags = "swagger接口测试", value ="swagger接口类")
@RequestMapping("swagger")
public class SwaggerTestController {
    /**
     * swagger-Api中get方式请求test
     * @param name  姓名
     * @param age   年龄
     * @param sex   性别
     * @return 请求的信息
     */
    @GetMapping("getTest")
    @ApiOperation(value = "swagger-Api中get方式请求test", notes = "get测试方法备注信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名字", paramType = "query", dataType = "String", required = true),
            @ApiImplicitParam(name = "age", value = "年龄", paramType = "query", dataType = "int", required = true, example = "0"),
            @ApiImplicitParam(name = "sex", value = "性别", paramType = "query", dataType = "String", required = true)
    })
    public String getTest(@RequestParam("name") String name,
                          @RequestParam("age") Integer age,
                          @RequestParam("sex") String sex) {
        String s = "name : " + name + "; age : " + age + "; sex : " + sex;
        System.out.println(s);
        return s;
    }

    /**
     * swagger-Api中post方式请求test
     * @param name  姓名
     * @param age   年龄
     * @param sex   性别
     * @return 请求的信息
     */
    @PostMapping("postTest")
    @ApiOperation(value = "swagger-Api中post方式请求test", notes = "post测试方法备注信息")
    public String postTest(@ApiParam(name = "name", value = "名字", required = true) @RequestParam("name") String name,
                           @ApiParam(name = "age", value = "年龄", required = true, example = "0") @RequestParam("age") Integer age,
                           @ApiParam(name = "sex", value = "性别", required = true) @RequestParam("sex") String sex) {
        String s = "name : " + name + "; age : " + age + "; sex : " + sex;
        System.out.println(s);
        return s;
    }

    /**
     * swagger-Api中postObjectTest方式请求test
     * @param student   Student实体类
     * @return  请求的信息
     */
    @PostMapping("postObjectTest")
    @ApiOperation(value = "swagger-Api中postObjectTest方式请求test", notes = "postObjectTest测试方法备注信息")
    public String postObjectTest(@ApiParam(name = "student", value = "Student实体类", required = true) @RequestBody Student student) {
        System.out.println(student.toString());
        return student.toString();
    }

}
