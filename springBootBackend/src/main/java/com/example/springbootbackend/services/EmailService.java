package com.example.springbootbackend.services;

import com.example.springbootbackend.model.CompanyEmailConstants;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender{

    private final static Logger LOGGER = LoggerFactory
            .getLogger(EmailService.class);

    private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String email) {
        try {
            System.out.println("try......");
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            System.out.println("try......");
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            System.out.println("try......");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom(CompanyEmailConstants.EMAIL);
            System.out.println("try......");
            mailSender.send(mimeMessage);
            System.out.println("Message is sent");
        } catch (MessagingException e) {
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }
    }
}
