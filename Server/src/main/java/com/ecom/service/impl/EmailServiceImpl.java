package com.ecom.service.impl;

import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;


@Service
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String toEmail, String subject, String body,  List<String> attachmentPath ) throws MessagingException, UnsupportedEncodingException {

        var mimeMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage,true);
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setFrom("l.qin3@partner.sea.samsung.com","Lang Qin");
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body);
        for (var filepath: attachmentPath
             ) {
            FileSystemResource file  = new FileSystemResource(new File(filepath));
            mimeMessageHelper.addAttachment(file.getFilename(),file);
        }
        javaMailSender.send(mimeMailMessage);
    }

    public void sendEmail(String toEmail, String subject, String body) throws MessagingException {

        var mimeMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage);
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setFrom("l.qin3@partner.sea.samsung.com");
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body);
        //"Late Origin Scan for " + LocalDate.now().toString()
        javaMailSender.send(mimeMailMessage);
    }

    public void sendEmailHTML(String toEmail, String subject, String body) throws MessagingException , UnsupportedEncodingException{

        var mimeMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage);
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setFrom("l.qin3@partner.sea.samsung.com","Lang Qin");
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body,true);
        //"Late Origin Scan for " + LocalDate.now().toString()
        javaMailSender.send(mimeMailMessage);
    }
}
