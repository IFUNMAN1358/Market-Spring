package ru.nag.spring.controller;

import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nag.spring.domain.JwtRequest;
import ru.nag.spring.domain.JwtResponse;
import ru.nag.spring.domain.RefreshJwtRequest;
import ru.nag.spring.exception.UserNotFoundException;
import ru.nag.spring.exception.WrongPasswordException;
import ru.nag.spring.service.AuthService;


@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthControllerOld {

    private final AuthService authService;

    @PostMapping("/auth/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) throws UserNotFoundException, WrongPasswordException {
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/auth/token")
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) throws UserNotFoundException {
        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody RefreshJwtRequest request) throws UserNotFoundException, AuthException {
        final JwtResponse token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

}
