package ru.nag.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nag.spring.entity.User;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsUserByEmail(String email);

    User getUserByEmail(String email);

    Optional<User> getUserById(UUID id);


}
