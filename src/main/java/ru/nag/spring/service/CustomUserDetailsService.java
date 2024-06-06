package ru.nag.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nag.spring.entity.User;
import ru.nag.spring.exception.UserAlreadyExistsException;
import ru.nag.spring.repository.UserRepository;


@Service
public class CustomUserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository,
                                    PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
}
