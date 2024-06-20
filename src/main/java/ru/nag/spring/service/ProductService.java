package ru.nag.spring.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nag.spring.domain.Product;
import ru.nag.spring.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final EntityManager entityManager;

    public void save(Product product) {
        productRepository.save(product);
    }

    public List<Product> getProducts(Integer offset, Integer limit, String search, String productType, String brand, String ageCategory, String animalType) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> product = cq.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        if (search != null && !search.isEmpty()) {
            predicates.add(cb.like(cb.lower(product.get("productName")), "%" + search.toLowerCase() + "%"));
        }
        if (productType != null && !productType.isEmpty()) {
            predicates.add(cb.equal(product.get("productType"), productType));
        }
        if (brand != null && !brand.isEmpty()) {
            predicates.add(cb.equal(product.get("brand"), brand));
        }
        if (ageCategory != null && !ageCategory.isEmpty()) {
            predicates.add(cb.equal(product.get("ageCategory"), ageCategory));
        }
        if (animalType != null && !animalType.isEmpty()) {
            predicates.add(cb.equal(product.get("animalType"), animalType));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Product> query = entityManager.createQuery(cq);
        query.setFirstResult(offset);
        query.setMaxResults(limit);

        return query.getResultList();
    }

    public Product getProductById(Integer id) {
        return productRepository.getProductById(id);
    }

}
