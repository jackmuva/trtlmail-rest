package com.jackmu.slowcapsules.controller.security;

import com.jackmu.slowcapsules.controller.WriterController;
import com.jackmu.slowcapsules.model.security.JwtAuthResponse;
import com.jackmu.slowcapsules.model.security.LoginDTO;
import com.jackmu.slowcapsules.model.security.RegisterDTO;
import com.jackmu.slowcapsules.model.security.User;
import com.jackmu.slowcapsules.service.security.AuthService;
import com.jackmu.slowcapsules.service.security.CustomUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;
    private CustomUserDetailsService customUserDetailsService;
    private static final Logger LOGGER = Logger.getLogger(AuthController.class.getName());

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDTO loginDTO){
        String token = authService.login(loginDTO);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) throws Exception{
        return authService.register(registerDTO);
    }

    @PostMapping(value = {"/resetPassword"})
    public ResponseEntity<String> resetPassword(@RequestParam("email") String userEmail) throws Exception {
        User user = customUserDetailsService.loadUserByEmail(userEmail);
        if (user == null) {
            throw new Exception();
        }
        String token = UUID.randomUUID().toString();
        customUserDetailsService.createPasswordResetTokenForUser(user, token);
//        mailSender.send(constructResetTokenEmail(getAppUrl(request),
//                request.getLocale(), token, user));
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
