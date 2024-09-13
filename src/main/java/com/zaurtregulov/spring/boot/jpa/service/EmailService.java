package com.zaurtregulov.spring.boot.jpa.service;

import com.zaurtregulov.spring.boot.jpa.entity.Mail;

// 4 СПОСОБА ОТПРАВКИ ПОЧТЫ
public interface EmailService {
    void sendSimpleMail(Mail mail);
    void sendHtmlMail(Mail mail);
    void sendEmailWithThymeleaf(Mail mail);
    void sendEmailWithAttachment(Mail mail);
}
