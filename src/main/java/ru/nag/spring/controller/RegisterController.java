package ru.nag.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nag.spring.domain.User;
import ru.nag.spring.dto.request.RegisterForm;
import ru.nag.spring.exception.UserException.UserAlreadyExistsException;
import ru.nag.spring.service.UserService;


@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> toRegister(@RequestBody RegisterForm registerForm) {
        try {
            User user = new User();
            user.setName(registerForm.getName());
            user.setSurname(registerForm.getSurname());
            user.setEmail(registerForm.getEmail());
            user.setPassword(registerForm.getPassword());
            userService.registerUser(user);
            return ResponseEntity.ok("User has been registered");
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
