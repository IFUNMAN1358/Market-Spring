package ru.nag.spring.config.Security;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import ru.nag.spring.domain.Role;
import ru.nag.spring.repository.RoleRepository;

import java.util.Optional;


@Configuration
public class RoleInitializer {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        addRoleIfNotExists(1, "ROLE_USER");
        addRoleIfNotExists(2, "ROLE_ADMIN");
    }

    private void addRoleIfNotExists(Integer id, String roleName) {
        Optional<Role> role = roleRepository.findRoleByName(roleName);
        if (role.isEmpty()) {
            Role newRole = new Role();
            newRole.setId(id);
            newRole.setName(roleName);
            roleRepository.save(newRole);
        }
    }
}