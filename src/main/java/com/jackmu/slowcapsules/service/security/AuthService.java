package com.jackmu.slowcapsules.service.security;

import com.jackmu.slowcapsules.model.security.LoginDTO;
import com.jackmu.slowcapsules.model.security.RegisterDTO;

public interface AuthService {
    String login(LoginDTO loginDto);
    String register(RegisterDTO registerDTO) throws Exception;
}
