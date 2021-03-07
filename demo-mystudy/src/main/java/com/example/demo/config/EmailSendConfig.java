package com.example.demo.config;

import com.example.demo.vo.MailInfoVo;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.Objects;

/**
 * @ClassName: Email
 * @Description:
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2021/03/05 00:22
 * @Copyright: Copyright(c)2021 kk All Rights Reserved
 */
@Log4j2
@Configuration
public class EmailSendConfig {
    @Autowired
    private JavaMailSenderImpl mailSender;

    /**
     * 发送邮件
     * @param mailInfoVo    邮件信息
     * @return  邮件信息(增加发送状态和msg)
     */
    public MailInfoVo sendMail(MailInfoVo mailInfoVo) {
        try {
            //检查 发送邮件的 必要的一些信息
            checkMailInfo(mailInfoVo);
            //发送邮件
            mailSender.send(getMimeMessageHelper(mailInfoVo).getMimeMessage());

            //设置 mailInfoVo status
            mailInfoVo.setStatus(0);
            mailInfoVo.setMsg("OK");
        } catch (Exception e) {
            mailInfoVo.setStatus(0);
            mailInfoVo.setMsg(e.getMessage());
            log.error("邮件发送失败 error : [{}]", e.getMessage());
            e.printStackTrace();
        }
        return mailInfoVo;
    }

    /**
     * 构建 复杂邮件信息类(MimeMessageHelper)
     * @param mailInfoVo    邮件信息
     */
    private MimeMessageHelper getMimeMessageHelper(MailInfoVo mailInfoVo) throws MessagingException {

        mailInfoVo.setSentDate(new Date());

        //创建 可以有附件的 MimeMessageHelper
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true);
        messageHelper.setFrom(mailInfoVo.getFrom());
        messageHelper.setTo(mailInfoVo.getTo());
        messageHelper.setSubject(mailInfoVo.getSubject());
        messageHelper.setText(mailInfoVo.getText());
        //cc
        if (StringUtils.isNotEmpty(mailInfoVo.getCarbonCopy())) {
            messageHelper.setCc(mailInfoVo.getCarbonCopy().split(","));
        }
        //bcc
        if (StringUtils.isNotEmpty(mailInfoVo.getBlindCarbonCopy())) {
            messageHelper.setBcc(mailInfoVo.getBlindCarbonCopy().split(","));
        }

        //附件
        if (CollectionUtils.isNotEmpty(mailInfoVo.getMultipartFiles())) {
            mailInfoVo.getMultipartFiles().forEach(multipartFile -> {
                try {
                    messageHelper.addAttachment(Objects.requireNonNull(multipartFile.getOriginalFilename()), multipartFile);
                } catch (MessagingException e) {
                    log.error("发送邮件 添加 附件错误 : [{}]", e.getStackTrace());
                    e.printStackTrace();
                }
            });
        }
        return messageHelper;
    }

    /**
     * 检查 发送邮件的 必要的一些信息
     * @param mailInfoVo    邮件信息
     */
    private void checkMailInfo(MailInfoVo mailInfoVo) {
        if (Objects.isNull(mailInfoVo)) {
            throw new RuntimeException("邮件信息(MailInfoVo) 为 null");
        }
        if (StringUtils.isEmpty(mailInfoVo.getFrom())) {
            throw new RuntimeException("邮件发件人 不能为空");
        }
        if (StringUtils.isEmpty(mailInfoVo.getTo())) {
            throw new RuntimeException("邮件收件人 不能为空");
        }
        if (StringUtils.isEmpty(mailInfoVo.getSubject())) {
            throw new RuntimeException("邮件标题(主题) 不能为空");
        }
        if (StringUtils.isEmpty(mailInfoVo.getText())) {
            throw new RuntimeException("邮件内容(正文) 不能为空");
        }

    }
}
