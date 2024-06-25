package ru.nag.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nag.spring.domain.Role;
import ru.nag.spring.repository.RoleRepository;
import ru.nag.spring.exception.UserException.RoleNotFoundException;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role getRoleByName(String name) throws RoleNotFoundException {
        return roleRepository.findRoleByName(name).orElseThrow(
                () -> new RoleNotFoundException("Role not found")
        );
    }

}
