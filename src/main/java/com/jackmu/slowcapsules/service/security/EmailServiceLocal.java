package com.jackmu.slowcapsules.service.security;

import com.jackmu.slowcapsules.model.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

@Service
@Profile("local-profile")
public class EmailServiceLocal implements EmailService {

    private static final String PASSWORD_RESET_ENDPOINT = "https://localhost:3000/changePassword/";
    private static final Logger LOGGER = Logger.getLogger(EmailServiceAws.class.getName());

    public void sendResetToken(String token, User user) {
        LOGGER.info("url: " + PASSWORD_RESET_ENDPOINT + token);
    }
}

