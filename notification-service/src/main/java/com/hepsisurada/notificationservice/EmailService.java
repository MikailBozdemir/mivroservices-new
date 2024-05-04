package com.hepsisurada.notificationservice;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;



import jakarta.annotation.PostConstruct;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    JavaMailSenderImpl mailSender;

    @Autowired
    EmailConfiguration emailConfiguration;
    @Autowired
    MessageSource messageSource;

    @PostConstruct
    public void initialize() {
        //System.err.println(hoaxifyProperties.getEmail().password());
        this.mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailConfiguration.getEmail().host());
        mailSender.setPort(emailConfiguration.getEmail().port());
        mailSender.setUsername(emailConfiguration.getEmail().username());
        mailSender.setPassword(emailConfiguration.getEmail().password());

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable", "true");

    }
    String activationEmail="""
            <html>
                <body>
                    <h1>${title}</h1>
                    <div>${message}</div>
                </body>
            </html>
            """;

    public void sendOrderNotificationEmail(String email, String orderNumber) {
        var title ="Bilgilendirme";
        var messageBody=orderNumber+" numarali siparişiniz alinmistir";
       
        var mailBody=activationEmail.replace("${message}",messageBody).replace("${title}",title);
     
        MimeMessage mimeMessage =mailSender.createMimeMessage();
        MimeMessageHelper message =new MimeMessageHelper(mimeMessage,"UTF-8");

        try {
        message.setFrom(emailConfiguration.getEmail().from());
        message.setTo(email);
        message.setSubject(title);
        message.setText(mailBody,true);
       
            
        } catch (Exception e) {
           e.printStackTrace();
        }
        

        this.mailSender.send(mimeMessage);
    }
}

