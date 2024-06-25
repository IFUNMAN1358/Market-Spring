package ru.nag.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nag.spring.domain.Order;
import ru.nag.spring.domain.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Optional<List<Order>> findAllByUser(User user);

    void deleteAllByUser(User user);

}
