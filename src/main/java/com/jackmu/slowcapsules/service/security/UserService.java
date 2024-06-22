package com.jackmu.slowcapsules.service.security;

import com.jackmu.slowcapsules.model.security.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    User loadUserByEmail(String email) throws UsernameNotFoundException;
    void createPasswordResetTokenForUser(User user, String token);
}
