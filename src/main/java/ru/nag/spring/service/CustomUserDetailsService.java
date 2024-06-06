package ru.nag.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nag.spring.entity.User;
import ru.nag.spring.exception.UserAlreadyExistsException;
import ru.nag.spring.exception.UserNotFoundException;
import ru.nag.spring.repository.UserRepository;

import java.util.UUID;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository,
                                    PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email);
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public void saveUser(User user) throws UserAlreadyExistsException {
        if (userRepository.existsUserByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("User with this email already exists");
        }
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );
        userRepository.save(user);
    }

    public User getUserById(UUID id) throws UserNotFoundException {
        return userRepository.getUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User with this id not found"));
    }
}
