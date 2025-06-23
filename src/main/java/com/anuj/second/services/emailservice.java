package com.anuj.second.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static java.rmi.server.LogStream.log;
@Service
@Slf4j
public class emailservice {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to,String subject, String body){
        try{
            SimpleMailMessage mail=new SimpleMailMessage();
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(body);
            javaMailSender.send(mail);
        }
        catch(Exception e){
            log.error("Unable to send message due to "+e);
        }
    }
}
