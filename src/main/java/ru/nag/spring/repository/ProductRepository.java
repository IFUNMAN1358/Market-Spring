package ru.nag.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nag.spring.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
