package ru.nag.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nag.spring.domain.Product;
import ru.nag.spring.service.ProductService;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/catalog")
    public ResponseEntity<List<Product>> showCatalog(
            @RequestParam Integer offset,
            @RequestParam Integer limit,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String productType,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String ageCategory,
            @RequestParam(required = false) String animalType
    ) {
        List<Product> products = productService.getProducts(offset, limit, search, productType, brand, ageCategory, animalType);
        return ResponseEntity.ok(products);
    }

    @PostMapping("/catalog") // PRODUCT_MANAGER
    public ResponseEntity<String> createProduct() { // need request body
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/catalog/{id}") // ALL
    public ResponseEntity<String> showProduct(@PathVariable String id) {
        return ResponseEntity.ok("ok");
    }

    @PatchMapping("/catalog/{id}") // PRODUCT_MANAGER
    public ResponseEntity<String> updateProduct(@PathVariable String id) { // need request body
        return ResponseEntity.ok("ok");
    }

    @DeleteMapping("/catalog/{id}") // PRODUCT_MANAGER
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        return ResponseEntity.ok("ok");
    }

}
