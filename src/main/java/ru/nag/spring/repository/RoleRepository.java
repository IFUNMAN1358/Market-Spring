package ru.nag.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nag.spring.domain.Role;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findRoleById(Integer id);
    Role getRoleById(Integer id);

    Optional<Role> findRoleByName(String name);
    Role getRoleByName(String name);

}
