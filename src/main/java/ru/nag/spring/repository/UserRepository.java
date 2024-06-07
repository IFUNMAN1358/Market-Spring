package ru.nag.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nag.spring.domain.User;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsUserByEmail(String email);

    Optional<User> findUserByEmail(String email);
    User getUserByEmail(String email);

    Optional<User> findUserById(UUID id);
    User getUserById(UUID id);

}
