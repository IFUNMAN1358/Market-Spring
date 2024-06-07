package ru.nag.spring.service;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import ru.nag.spring.domain.JwtAuthentication;
import ru.nag.spring.domain.Role;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public final class JwtUtils {

    private final RoleService roleService;

    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setRoles(getRoles(claims));
        jwtInfoToken.setId(claims.get("id", String.class));
        //jwtInfoToken.setUsername(claims.getSubject());
        return jwtInfoToken;
    }

    private static Set<Role> getRoles(Claims claims) {
        final List<String> roles = claims.get("roles", List.class);
        return roles.stream()
                    .map(Role::new)
                    .collect(Collectors.toSet());
    }

}
