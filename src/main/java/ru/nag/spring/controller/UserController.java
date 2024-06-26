package ru.nag.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nag.spring.domain.User;
import ru.nag.spring.dto.request.EmailRequest;
import ru.nag.spring.dto.request.NameAndSurnameRequest;
import ru.nag.spring.dto.request.PasswordRequest;
import ru.nag.spring.dto.response.UserResponse;
import ru.nag.spring.exception.OrderException.OrdersNotFoundException;
import ru.nag.spring.exception.UserException.UserNotFoundException;
import ru.nag.spring.jwt.JwtAuthentication;
import ru.nag.spring.service.AuthService;
import ru.nag.spring.service.UserService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;
    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<UserResponse> getUser() throws UserNotFoundException {
        JwtAuthentication authInfo = authService.getAuthInfo();
        User user = userService.getUserById(UUID.fromString(authInfo.getId()));

        UserResponse userResponse = userService.convertUserToResponse(user);
        return ResponseEntity.ok(userResponse);
    }

    @PatchMapping("/user/change-name-and-surname")
    public ResponseEntity<UserResponse> changeUserNameAndSurname(@RequestBody NameAndSurnameRequest newData) throws UserNotFoundException {
        JwtAuthentication authInfo = authService.getAuthInfo();

        User user = userService.getUserById(UUID.fromString(authInfo.getId()));
        user.setName(newData.getName());
        user.setSurname(newData.getSurname());
        userService.save(user);

        UserResponse userResponse = userService.convertUserToResponse(user);
        return ResponseEntity.ok(userResponse);
    }

    @PatchMapping("/user/change-email")
    public ResponseEntity<UserResponse> changeUserEmail(@RequestBody EmailRequest newData) throws UserNotFoundException {
        JwtAuthentication authInfo = authService.getAuthInfo();

        User user = userService.getUserById(UUID.fromString(authInfo.getId()));
        user.setEmail(newData.getEmail());
        userService.save(user);

        UserResponse userResponse = userService.convertUserToResponse(user);
        return ResponseEntity.ok(userResponse);
    }

    @PatchMapping("/user/change-password")
    public ResponseEntity<String> changeUserPassword(@RequestBody PasswordRequest newData) throws UserNotFoundException {
        JwtAuthentication authInfo = authService.getAuthInfo();

        User user = userService.getUserById(UUID.fromString(authInfo.getId()));
        String newPassword = newData.getPassword();
        userService.updateUserPassword(user, newPassword);

        return ResponseEntity.ok("Password has been successful updated");
    }

    @DeleteMapping("/user")
    public ResponseEntity<String> deleteUser() throws UserNotFoundException, OrdersNotFoundException {
        JwtAuthentication authInfo = authService.getAuthInfo();
        userService.deleteUserById(UUID.fromString(authInfo.getId()));
        return ResponseEntity.ok("User deleted: " + authInfo.getId());
    }

}
