package ru.nag.spring.service;


import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nag.spring.domain.JwtAuthentication;
import ru.nag.spring.domain.JwtRequest;
import ru.nag.spring.domain.JwtResponse;
import ru.nag.spring.domain.User;
import ru.nag.spring.exception.UserNotFoundException;
import ru.nag.spring.exception.WrongPasswordException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final Map<String, String> refreshStorage = new HashMap<>();
    private final JwtProvider jwtProvider;


    public JwtResponse login(@NonNull JwtRequest loginForm) throws UserNotFoundException, WrongPasswordException {
        final User user = userService.getUserByEmail(loginForm.getEmail());

        if (passwordEncoder.matches(loginForm.getPassword(), user.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateRefreshToken(user);

            refreshStorage.put(user.getId().toString(), refreshToken);
            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new WrongPasswordException("Invalid password");
        }
    }

    public JwtResponse getAccessToken(@NonNull String refreshToken) throws UserNotFoundException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {

            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String id = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(id);

            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userService.getUserById(UUID.fromString(id));
                final String accessToken = jwtProvider.generateAccessToken(user);
                return new JwtResponse(accessToken, null);
            }
        }
        return new JwtResponse(null, null);
    }

    public JwtResponse refresh(@NonNull String refreshToken) throws UserNotFoundException, AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {

            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String id = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(id);

            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userService.getUserById(UUID.fromString(id));
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshStorage.put(user.getId().toString(), newRefreshToken);
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException("Невалидный JWT токен");
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
