package ru.nag.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nag.spring.exception.UserNotFoundException;
import ru.nag.spring.service.AuthService;
import ru.nag.spring.jwt.JwtAuthentication;


@RestController
@RequestMapping
@RequiredArgsConstructor
public class TestController {

    private final AuthService authService;

    @GetMapping("/test")
    public ResponseEntity<String> TEST() throws UserNotFoundException {
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/user")
    public ResponseEntity<String> helloUser() {
        final JwtAuthentication authInfo = authService.getAuthInfo();
        return ResponseEntity.ok("Hello user " + authInfo.getPrincipal() + "!");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> helloAdmin() {
        final JwtAuthentication authInfo = authService.getAuthInfo();
        return ResponseEntity.ok("Hello admin " + authInfo.getPrincipal() + "!");
    }

}
