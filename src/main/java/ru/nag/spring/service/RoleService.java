package ru.nag.spring.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import ru.nag.spring.domain.Role;
import ru.nag.spring.repository.RoleRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RoleService implements GrantedAuthority {

    private final RoleRepository roleRepository;

    @Override
    public String getAuthority() {
        return "";
    }

}
