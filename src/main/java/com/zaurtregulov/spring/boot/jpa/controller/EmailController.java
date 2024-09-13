package com.zaurtregulov.spring.boot.jpa.controller;

import com.zaurtregulov.spring.boot.jpa.entity.Mail;
import com.zaurtregulov.spring.boot.jpa.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController //это контроллер приложения который управляет Rest запросами и возвращает ответы в формате JSON
@RequestMapping("/email") //аннотация, которая указывает, что URL-адрес начинается с "/api"
@Slf4j
public class EmailController {


    private EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/simple")
    public void sendSimpleMail(@RequestBody Mail mail) {
        log.info("Sending simple mail success...");
        emailService.sendSimpleMail(mail);
    }

    @PostMapping("/html")
    public void sendHtmlMail(@RequestBody Mail mail) {
        log.info("Sending html mail success...");
        emailService.sendHtmlMail(mail);
    }

    @PostMapping("/thymeleaf")
    public void sendEmailWithThymeleaf(@RequestBody Mail mail) {
        log.info("Sending email with thymeleaf success...");
        emailService.sendEmailWithThymeleaf(mail);
    }

    @PostMapping("/attachment")
    public void sendEmailWithAttachment(@RequestBody Mail mail) {
        log.info("Sending email with attachment success...");
        emailService.sendEmailWithAttachment(mail);
    }
}
