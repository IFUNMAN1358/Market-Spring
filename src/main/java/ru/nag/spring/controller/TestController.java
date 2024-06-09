package ru.nag.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nag.spring.domain.Role;
import ru.nag.spring.domain.User;
import ru.nag.spring.exception.UserNotFoundException;
import ru.nag.spring.jwt.AuthService;
import ru.nag.spring.jwt.EmailRequest;
import ru.nag.spring.jwt.JwtAuthentication;
import ru.nag.spring.service.UserService;

import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping
@RequiredArgsConstructor
public class TestController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/test")
    public ResponseEntity<String> TEST(@RequestBody EmailRequest request) throws UserNotFoundException {
        User user = userService.getUserByEmail(request.getEmail());
        Set<String> roles = user.getRoles();
        System.out.println(roles);
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
