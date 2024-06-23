package com.jackmu.slowcapsules.service.security;

import com.jackmu.slowcapsules.model.security.User;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Profile("!local-profile")
public class EmailServiceAws implements EmailService{
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendResetTokenEmail(String contextPath, String token, User user) {
//        String url = contextPath + "/user/changePassword?token=" + token;
//        String message = messages.getMessage("message.resetPassword",
//                null, locale);
//        return constructEmail("Reset Password", message + " \r\n" + url, user);
    }

    @Override
    public MimeMessage constructEmail(String subject, String body, User user) {
//        SimpleMailMessage email = new SimpleMailMessage();
//        email.setSubject(subject);
//        email.setText(body);
//        email.setTo(user.getEmail());
//        email.setFrom(env.getProperty("support.email"));
//        return email;
        return null;
    }
}
