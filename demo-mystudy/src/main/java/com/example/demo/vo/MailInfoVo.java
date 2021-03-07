package com.example.demo.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: Test
 * @Description:
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2021/03/05 01:30
 * @Copyright: Copyright(c)2021 kk All Rights Reserved
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MailInfoVo {
    /**
     * 邮件发送人
     */
    private String from;

    /**
     * 邮件接收人
     */
    private String to;
    /**
     * 邮件标题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String text;

    /**
     * 发送时间
     */
    private Date sentDate;

    /**
     * 抄送
     * 多个 cc 用 英文逗号(,) 分割
     */
    private String carbonCopy;

    /**
     * 密送
     * 多个 bcc 用 英文逗号(,) 分割
     */
    private String blindCarbonCopy;

    /**
     * 状态(1 : 成功; 0 : 失败)
     */
    private int status;

    /**
     * 发送结果信息
     */
    private String msg;

    /**
     * 附件 集合
     * 注解@JsonIgnore : json序列化时将java bean中的一些属性忽略掉，序列化和反序列化都受影响
     */
    @JsonIgnore
    private List<MultipartFile> multipartFiles;

}
