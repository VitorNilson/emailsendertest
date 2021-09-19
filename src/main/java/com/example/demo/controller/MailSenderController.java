package com.example.demo.controller;

import com.example.demo.mod.SendMailDTO;
import com.example.demo.service.EmailService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Properties;

@RestController
@RequestMapping("/api")
public class MailSenderController {


    @Autowired
    private EmailService emailService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/sendmail")
    public void sendMail(@RequestBody SendMailDTO sendMailDTO){
            emailService.sendSimpleMessage(sendMailDTO);
    }
}

