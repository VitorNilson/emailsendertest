package com.example.demo.service;

import com.example.demo.mod.SendMailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService{

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(
            SendMailDTO sendMailDTO) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(sendMailDTO.getTo());
        message.setText(sendMailDTO.getText());

        emailSender.send(message);

    }

}
