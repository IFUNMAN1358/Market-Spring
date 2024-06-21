package ru.nag.spring.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nag.spring.domain.Role;
import ru.nag.spring.domain.User;
import ru.nag.spring.exception.UserException.UserAlreadyExistsException;
import ru.nag.spring.exception.UserException.UserNotFoundException;
import ru.nag.spring.repository.RoleRepository;
import ru.nag.spring.repository.UserRepository;

import java.util.*;


@Service
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void registerUser(User user) throws UserAlreadyExistsException {
        if (userRepository.existsUserByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("User with this email already exists");
        }
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        Role role = roleRepository.getRoleByName("ROLE_USER");

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

    public void updateUserPassword(User user, String newPassword) {
        user.setPassword(
                passwordEncoder.encode(newPassword)
        );
        userRepository.save(user);
    }

    @Transactional
    public void deleteUserById(UUID id) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findUserById(id);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User with this id not found");
        }

        User user = userOptional.get();

        user.getRoles().clear();
        userRepository.save(user);

        userRepository.deleteUserById(id);
    }

}
