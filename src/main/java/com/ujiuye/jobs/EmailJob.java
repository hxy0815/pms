package com.ujiuye.jobs;

import com.ujiuye.info.bean.Email;
import org.quartz.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.File;

public class EmailJob implements Job {

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
        Email email = (Email)dataMap.get("email");
        JavaMailSenderImpl javaMailSender = (JavaMailSenderImpl)dataMap.get("JavaMailSenderImpl");
        Scheduler sched = (Scheduler)dataMap.get("scheduler");
        try{
            //邮件对象
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            helper.setFrom("13693965604@163.com");
            helper.setTo(email.getEname());
            helper.setSubject(email.getTitle());
            helper.setText(email.getContent(),true);

            //发送邮件
            javaMailSender.send(mimeMessage);
            System.out.println("EMAIL PASS");
            sched.shutdown();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }
}
