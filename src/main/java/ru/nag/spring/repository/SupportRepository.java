package ru.nag.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nag.spring.domain.Support;

import java.util.Optional;

@Repository
public interface SupportRepository extends JpaRepository<Support, Integer> {

    Optional<Support> findSupportById(Integer id);

}
