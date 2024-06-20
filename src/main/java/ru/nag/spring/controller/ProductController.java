package ru.nag.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nag.spring.domain.Product;
import ru.nag.spring.dto.request.ProductRequest;
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
    public ResponseEntity<Product> showProduct(@PathVariable Integer id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    //////////////////////////////////////////////////////////////////////////////////


    @PostMapping("/catalog")
    public ResponseEntity<String> createProduct(@ModelAttribute ProductRequest productForm) {
        JwtAuthentication authInfo = authService.getAuthInfo();

        String imageUrl = imageService.saveImage(productForm.getImage());

        Product product = new Product();
        product.setProductName(productForm.getProductName());
        product.setProductType(productForm.getProductType());
        product.setBrand(productForm.getBrand());
        product.setAgeCategory(productForm.getAgeCategory());
        product.setAnimalType(productForm.getAnimalType());
        product.setProductWeight(productForm.getProductWeight());
        product.setStockQuantity(productForm.getStockQuantity());
        product.setDescription(productForm.getDescription());
        product.setProductIngredients(productForm.getProductIngredients());
        product.setCountryOfOrigin(productForm.getCountryOfOrigin());
        product.setExpDateMonths(productForm.getExpDateMonths());
        product.setPrice(productForm.getPrice());
        product.setImageUrl(imageUrl);

        productService.save(product);

        return ResponseEntity.ok("Product created");
    }

    @PatchMapping("/catalog/{id}") // PRODUCT_MANAGER
    public ResponseEntity<String> updateProduct(@PathVariable String id) {
        JwtAuthentication authInfo = authService.getAuthInfo();
        return ResponseEntity.ok("ok");
    }

    @DeleteMapping("/catalog/{id}") // PRODUCT_MANAGER
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        JwtAuthentication authInfo = authService.getAuthInfo();
        return ResponseEntity.ok("ok");
    }

}
