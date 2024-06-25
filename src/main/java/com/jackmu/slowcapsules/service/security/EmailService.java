package com.jackmu.slowcapsules.service.security;

import com.jackmu.slowcapsules.model.security.User;
import javax.mail.internet.MimeMessage;

public interface EmailService {
    void sendResetTokenEmail(String token, User user);
    MimeMessage constructEmail(String body, User user);
}
