package org.lnu.software.testing.auto.service.controller.auth;

import lombok.AllArgsConstructor;
import org.lnu.software.testing.auto.service.dto.user.credentials.UserCredentials;
import org.lnu.software.testing.auto.service.service.auth.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("login")
    public void login(@RequestBody UserCredentials userCredentials) {
        authService.login(userCredentials);
    }
}
