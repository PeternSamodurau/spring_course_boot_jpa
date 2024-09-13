package com.zaurtregulov.spring.boot.jpa.service;

import com.zaurtregulov.spring.boot.jpa.entity.Mail;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Objects;


@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    //@Autowired //аннотация @Autowired используется для автоматического создания бинов
    private JavaMailSender mailSender;
    private final TemplateEngine templateEn;


    public EmailServiceImpl(JavaMailSender mailSender, TemplateEngine templateEn) {
        this.mailSender = mailSender;
        this.templateEn = templateEn;
    }

    @Override
    @Async
    //аннотация, которая указывает, что метод будет выполняться в отдельном потоке, не блокируя главный поток веб-сервера
    public void sendSimpleMail(Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getTo());
        message.setSubject(mail.getSubject());
        message.setText(mail.getBody());

        mailSender.send(message);
    }

    @Override
    @Async
    public void sendHtmlMail(Mail mail) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            message.setFrom(new InternetAddress("samodurov1970@gmail.com"));
            for (String recipient : mail.getTo()) {
                message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(recipient));
            }

            message.setSubject(mail.getSubject());
            message.setContent(mail.getBody(), "text/html; charset=utf-8");

            mailSender.send(message);

            log.info("Sending html mail success...");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Async
    public void sendEmailWithThymeleaf(Mail mail) {
        for (String recipient : mail.getTo()) {
            Context context = new Context();
            context.setVariable("username", recipient);

            String process = templateEn.process("ThymeLeafMail", context);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            try {
                helper.setSubject(mail.getSubject());
                helper.setFrom("samodurov1970@gmail.com");
                helper.setText(process, true);
                helper.setTo(recipient);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            log.info("Sending email with thymeleaf success...");
            mailSender.send(message);

        }
   }

    @Override
    @Async
    public void sendEmailWithAttachment(Mail mail) {
        for (String recipient: mail.getTo()){
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = null;
            try {
                helper = new MimeMessageHelper(message, true);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

            try {
                helper.setFrom("samodurov1970@gmail.com");
                helper.setTo(recipient);
                helper.setSubject("Testing Mail API With Attachment");
                helper.setText("Please find the attached document below");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

            ClassPathResource classPathResource = new ClassPathResource("LockBackground.jpg");
            try {
                helper.addAttachment(Objects.requireNonNull(classPathResource.getFilename()), classPathResource);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            log.info("Sending email with attachment success...");

            mailSender.send(message);
        }
    }
}
