package com.shuai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

@RestController
public class DemoController {
   /* @RequestMapping("info")
    public String aa(){
        return "55";
    }*/
   @Autowired
   private JavaMailSender javaMailSender;

    //发送普通文本邮件
    @RequestMapping(value = "sendMail")
    public String sendMail(Model model) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("2593174524@qq.com"); //发送者
        message.setTo("zhangshuai9@asiainfo.com");  //接受者
       // message.setCc("654321***@163.com"); //抄送，填发送者的邮箱即可
        message.setSubject("55");	//主题
        message.setText("你好你好你好！");	//内容

        try {
            javaMailSender.send(message);
            System.out.println("简单邮件已经发送");
        } catch (Exception e) {
            System.out.println("发送简单邮件时发生异常！"+e.toString());
            e.printStackTrace();
        }
        model.addAttribute("msg", "");
        return "login";
    }

    //也可以html邮件
    @RequestMapping("sendHtmlMail")
    public void sendHtmlMail() throws UnsupportedEncodingException {
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>您的入职学历有误，请您仔细核对或者叫你左手边的人\n" +
                "<img src=\"cid:aaa\"/>"+
                "</body>\n" +
                "</html>";
        FileSystemResource iimg = new FileSystemResource(new File("src/main/imp/55.jpg"));



        MimeMessage message = javaMailSender.createMimeMessage();

        String from="2593174524@qq.com";
        InternetAddress fromAddress = new InternetAddress(from,"北京教育质量监测中心");//设置发送人别称
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(fromAddress);
            messageHelper.setTo("zhangshuai@maitewang.com");
            messageHelper.setSubject("入职学历审查");
            messageHelper.setText(content, true);
            messageHelper.addInline("aaa",iimg);

            MimeBodyPart img = new MimeBodyPart();
            /*
             * JavaMail API不限制信息只为文本,任何形式的信息都可能作茧自缚MimeMessage的一部分.
             * 除了文本信息,作为文件附件包含在电子邮件信息的一部分是很普遍的. JavaMail
             * API通过使用DataHandler对象,提供一个允许我们包含非文本BodyPart对象的简便方法.
             */
            DataHandler dh = new DataHandler(new FileDataSource("src//main//imp//消息.png"));//图片路径
            img.setDataHandler(dh);
            // 创建图片的一个表示用于显示在邮件中显示
            img.setContentID("a");

            MimeBodyPart img2 = new MimeBodyPart();
            DataHandler dh2 = new DataHandler(new FileDataSource("src//main//imp//消息.png"));//第二张图片路径
            img2.setDataHandler(dh2);
            img2.setContentID("b");
            // 创建附件
            // MimeBodyPart attch = new MimeBodyPart();
            // DataHandler dh1 = new DataHandler(new FileDataSource("src//b.jpg"));
            // attch.setDataHandler(dh1);
            // String filename1 = dh1.getName();
            // MimeUtility 是一个工具类，encodeText（）用于处理附件字，防止中文乱码问题
            // attch.setFileName(MimeUtility.encodeText(filename1));
            // 关系 正文和图片的
            MimeMultipart mm = new MimeMultipart();
            MimeBodyPart text = new MimeBodyPart();
            // setContent(“邮件的正文内容”,”设置邮件内容的编码方式”)
            text.setContent(content,
                    "text/html;charset=gb2312");

            mm.addBodyPart(text);
            mm.addBodyPart(img);
            mm.setSubType("related");// 设置正文与图片之间的关系
            // 图班与正文的 body
            MimeBodyPart all = new MimeBodyPart();
            all.setContent(mm);
            // 附件与正文（text 和 img）的关系
            MimeMultipart mm2 = new MimeMultipart();
            mm2.addBodyPart(all);
            mm2.addBodyPart(img2);
            mm2.setSubType("mixed");//
            message.setContent(mm2);
            message.saveChanges(); // 保存修改

            javaMailSender.send(message);
            System.out.println("邮件成功发送");
        } catch (MessagingException e) {
            System.out.println("发送邮件时发生异常！"+e.toString());
        }
    }

}
