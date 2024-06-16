package ru.nag.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nag.spring.domain.User;
import ru.nag.spring.dto.response.UserResponse;
import ru.nag.spring.exception.UserNotFoundException;
import ru.nag.spring.jwt.JwtAuthentication;
import ru.nag.spring.service.AuthService;
import ru.nag.spring.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;
    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<UserResponse> user() throws UserNotFoundException {
        final JwtAuthentication authInfo = authService.getAuthInfo();
        User user = userService.getUserById(UUID.fromString(authInfo.getId()));
         UserResponse userResponse = new UserResponse(
            user.getId().toString(),
            user.getName(),
            user.getSurname(),
            user.getEmail(),
            user.getRoles()
        );
        return ResponseEntity.ok(userResponse);
    }

}
