package com.jackmu.slowcapsules.service.security;

import com.jackmu.slowcapsules.model.security.User;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

@Service
//@Profile("!local-profile")
public class EmailServiceAws implements EmailService {
    @Autowired
    private JavaMailSender mailSender;
    private static final String PASSWORD_RESET_ENDPOINT = "/user/changePassword?token=";
    private static final Logger LOGGER = Logger.getLogger(EmailServiceAws.class.getName());

    @Override
    public void sendResetTokenEmail(String uri, String token, User user) {
        LOGGER.info("token: " + token + " for user: " + user.getEmail());

        constructEmail(uri + PASSWORD_RESET_ENDPOINT + token, user);
    }

    @Override
    public MimeMessage constructEmail(String body, User user) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setBcc(user.getEmail());
            helper.setFrom("trtlpost@trtlpost.com", "Trtlpost");
            helper.setSubject("TrtlPost Account Password Recovery");
            helper.setText("<a href = \"" + body + "\">" + body + "</a>", true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
