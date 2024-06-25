package com.jackmu.slowcapsules.service.security;

import com.jackmu.slowcapsules.model.security.User;
import javax.mail.internet.MimeMessage;

public interface EmailService {
    void sendResetToken(String token, User user);
}
