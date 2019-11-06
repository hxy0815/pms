package com.offcn;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.File;

public class EmailTest {

    @Test
    public  void test01() throws  Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-email.xml");
        JavaMailSenderImpl bean = context.getBean(JavaMailSenderImpl.class);
        //邮件对象
        MimeMessage mimeMessage = bean.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
        helper.setFrom("13693965604@163.com");
        helper.setTo("15238544606@163.com");
        helper.setSubject("这是0708班的邮件测试");
        helper.setText("<html><head></head><body>嘿嘿嘿 <img src=cid:identifie>  <span style='color:red'>嘿嘿</span>嘿嘿，阿哦哦<h1>阿哦</h1>好奥</body></html>",true);
        //图片作为内置资源
        FileSystemResource file1 = new FileSystemResource(new File("C:\\Users\\Administrator\\Desktop\\dj.jpg"));
        helper.addInline("identifie",file1);
        //作为附件下载
        FileSystemResource file = new FileSystemResource(new File("C:\\Users\\Administrator\\Desktop\\fengjing.jpg"));
        helper.addAttachment("CoolImage.jpg", file);


        //发送邮件
        bean.send(mimeMessage);
        System.out.println("EMAIL PASS");
    }
}
