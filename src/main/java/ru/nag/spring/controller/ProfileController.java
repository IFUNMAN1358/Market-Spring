package ru.nag.spring.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.nag.spring.exception.UserNotFoundException;
import ru.nag.spring.service.UserService;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;


//    @GetMapping("/profile")
//    @PreAuthorize("hasAuthority('USER')")
//    public ResponseEntity<String> getProfile() {
//        final JwtAuthentication authInfo = authService.getAuthInfo();
//        return ResponseEntity.ok("Hello user " + authInfo.getPrincipal() + "!");
//    }

}
