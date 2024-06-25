package com.jackmu.slowcapsules.controller.security;

import com.jackmu.slowcapsules.util.GenericHttpResponse;
import com.jackmu.slowcapsules.model.security.*;
import com.jackmu.slowcapsules.service.security.AuthService;
import com.jackmu.slowcapsules.service.security.EmailService;
import com.jackmu.slowcapsules.service.security.PasswordResetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;
    private PasswordResetService passwordResetService;
    private EmailService emailService;
    private static final Logger LOGGER = Logger.getLogger(AuthController.class.getName());

    public AuthController(AuthService authService, PasswordResetService passwordResetService, EmailService emailService) {
        this.authService = authService;
        this.passwordResetService = passwordResetService;
        this.emailService = emailService;
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
    public ResponseEntity<String> resetPassword(HttpServletRequest request, @RequestParam("email") String userEmail) throws Exception {
        User user = passwordResetService.loadUserByEmail(userEmail);
        if (user == null) {
            throw new Exception();
        }
        String token = UUID.randomUUID().toString();
        passwordResetService.createPasswordResetTokenForUser(user, token);
        emailService.sendResetTokenEmail(token, user);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @GetMapping("/changePassword")
    public GenericHttpResponse showChangePasswordPage(@RequestParam("token") String token) {
        String result = passwordResetService.validatePasswordResetToken(token);
        if(result != null) {
            return new GenericHttpResponse(HttpStatus.NOT_ACCEPTABLE.value(), "expired or invalid token");
        } else {
            return new GenericHttpResponse(HttpStatus.OK.value(), "Redirecting to password reset");
        }
    }

    @PostMapping("/user/savePassword")
    public ResponseEntity<String> savePassword(@Valid PasswordDto passwordDto) {
        String result = passwordResetService.validatePasswordResetToken(passwordDto.getToken());

        if(result != null) {
            return new ResponseEntity<String>(HttpStatus.OK);
        }

        Optional<User> user = passwordResetService.getUserByPasswordResetToken(passwordDto.getToken());
        if(user.isPresent()) {
            passwordResetService.changeUserPassword(user.get(), passwordDto.getNewPassword());
            return new ResponseEntity<String>(HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }
}
