package ru.nag.spring.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nag.spring.domain.Role;
import ru.nag.spring.domain.User;
import ru.nag.spring.exception.UserAlreadyExistsException;
import ru.nag.spring.exception.UserNotFoundException;
import ru.nag.spring.repository.RoleRepository;
import ru.nag.spring.repository.UserRepository;


import java.util.*;


@Service
@RequiredArgsConstructor
public class UserService{

    private final String USER_ROLE = "USER";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void saveUser(User user) throws UserAlreadyExistsException {
        if (userRepository.existsUserByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("User with this email already exists");
        }
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );


        Role role = roleRepository.getRoleByName(USER_ROLE);

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        userRepository.save(user);
    }

    public User getUserById(UUID id) throws UserNotFoundException {
        return userRepository.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User with this id not found"));
    }

    public User getUserByEmail(String email) throws UserNotFoundException {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with this email not found"));
    }

}
