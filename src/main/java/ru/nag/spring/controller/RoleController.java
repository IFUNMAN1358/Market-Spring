package ru.nag.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nag.spring.domain.Role;
import ru.nag.spring.domain.User;
import ru.nag.spring.dto.request.RoleRequest;
import ru.nag.spring.dto.response.UserResponse;
import ru.nag.spring.exception.UserException.RoleNotFoundException;
import ru.nag.spring.exception.UserException.UserNotFoundException;
import ru.nag.spring.jwt.JwtAuthentication;
import ru.nag.spring.service.AuthService;
import ru.nag.spring.service.RoleService;
import ru.nag.spring.service.UserService;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class RoleController {

    private final UserService userService;
    private final AuthService authService;
    private final RoleService roleService;

    @GetMapping("/role/{ident}")
    public ResponseEntity<UserResponse> getUserByEmailOrId(@PathVariable String ident) throws UserNotFoundException {
        JwtAuthentication authInfo = authService.getAuthInfo();

        User user = userService.getUserByEmailOrId(ident);

        UserResponse userResponse = userService.convertUserToResponse(user);
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/role/{ident}")
    public ResponseEntity<UserResponse> getRole(@PathVariable String ident,
                                                @RequestBody RoleRequest newRole) throws UserNotFoundException, RoleNotFoundException {
        JwtAuthentication authInfo = authService.getAuthInfo();

        User user = userService.getUserByEmailOrId(ident);

        Role addingRole = roleService.getRoleByName(newRole.getRole());

        Set<Role> roles = user.getRoles(true);
        roles.add(addingRole);

        user.setRoles(roles);
        userService.save(user);

        UserResponse userResponse = userService.convertUserToResponse(user);
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("/role/{ident}")
    public ResponseEntity<UserResponse> deleteRole(@PathVariable String ident,
                                                   @RequestBody RoleRequest roleToDelete) throws UserNotFoundException, RoleNotFoundException {
        JwtAuthentication authInfo = authService.getAuthInfo();

        User user = userService.getUserByEmailOrId(ident);
        Role role = roleService.getRoleByName(roleToDelete.getRole());

        Set<Role> roles = user.getRoles(true);
        roles.remove(role);

        user.setRoles(roles);
        userService.save(user);

        UserResponse userResponse = userService.convertUserToResponse(user);
        return ResponseEntity.ok(userResponse);
    }

}
