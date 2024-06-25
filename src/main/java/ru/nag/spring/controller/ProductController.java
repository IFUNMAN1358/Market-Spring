package ru.nag.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nag.spring.domain.Product;
import ru.nag.spring.dto.request.ProductRequest;
import ru.nag.spring.exception.ProductException.ProductNotFoundException;
import ru.nag.spring.jwt.JwtAuthentication;
import ru.nag.spring.service.AuthService;
import ru.nag.spring.service.ImageService;
import ru.nag.spring.service.ProductService;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final AuthService authService;
    private final ImageService imageService;

    @GetMapping("/catalog") // ALL
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

    @GetMapping("/catalog/{id}") // ALL
    public ResponseEntity<Product> showProduct(@PathVariable Integer id) throws ProductNotFoundException {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    //////////////////////////////////////////////////////////////////////////////////


    @PostMapping("/catalog")
    public ResponseEntity<String> createProduct(@ModelAttribute ProductRequest productForm) {
        JwtAuthentication authInfo = authService.getAuthInfo();

        String imageUrl = imageService.saveImage(productForm.getImage());

        Product product = new Product();
        product.setImageUrl(imageUrl);

        productService.updateProductFromForm(product, productForm);
        productService.saveProduct(product);

        return ResponseEntity.ok("Product created");
    }

    @PatchMapping("/catalog/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Integer id,
                                                @ModelAttribute ProductRequest productForm) throws ProductNotFoundException {
        JwtAuthentication authInfo = authService.getAuthInfo();
        Product product = productService.getProductById(id);

        String oldImageUrl = product.getImageUrl();
        imageService.deleteProductImageUrl(oldImageUrl);

        String imageUrl = imageService.saveImage(productForm.getImage());
        product.setImageUrl(imageUrl);

        productService.updateProductFromForm(product, productForm);
        productService.saveProduct(product);

        return ResponseEntity.ok("Product updated");
    }

    @DeleteMapping("/catalog/{id}") // PRODUCT_MANAGER
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) throws ProductNotFoundException {
        JwtAuthentication authInfo = authService.getAuthInfo();

        Product product = productService.getProductById(id);
        imageService.deleteProductImageUrl(product.getImageUrl());
        productService.deleteProduct(product);

        return ResponseEntity.ok("Product deleted");
    }

}
