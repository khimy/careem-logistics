package org.sa.notification.services.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Created by Vivek on 22-02-2017.
 */
@Service
public class EmailEndPoint {

    @Autowired
    private JavaMailSender javaMailService;


    public void sendEmail(){
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo("vivek081141@gmail.com");
        mailMessage.setSubject("Registration");
        mailMessage.setText("Hello " +"" +"\n Your registration is successfull");
        javaMailService.send(mailMessage);
    }
}
