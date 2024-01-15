package com.jackmu.slowcapsules.service.security;

import com.jackmu.slowcapsules.model.security.LoginDTO;
import com.jackmu.slowcapsules.model.security.RegisterDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    String login(LoginDTO loginDto);
    ResponseEntity<String> register(RegisterDTO registerDTO) throws Exception;
}
