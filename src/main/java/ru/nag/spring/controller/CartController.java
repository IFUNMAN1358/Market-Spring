package ru.nag.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nag.spring.domain.Product;
import ru.nag.spring.domain.User;
import ru.nag.spring.dto.request.CartRequest;
import ru.nag.spring.exception.ProductException.ProductNotFoundException;
import ru.nag.spring.exception.UserException.UserNotFoundException;
import ru.nag.spring.jwt.JwtAuthentication;
import ru.nag.spring.service.AuthService;
import ru.nag.spring.service.ProductService;
import ru.nag.spring.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CartController {

    private final UserService userService;
    private final ProductService productService;
    private final AuthService authService;

    @GetMapping("/cart")
    public ResponseEntity<List<Product>> getCart() throws UserNotFoundException {
        JwtAuthentication authInfo = authService.getAuthInfo();

        User user = userService.getUserById(UUID.fromString(authInfo.getId()));

        List<Product> cartProducts = user.getProducts();

        return ResponseEntity.ok(cartProducts);
    }

    @PostMapping("/cart")
    public ResponseEntity<String> addCartItem(@RequestBody CartRequest cartRequest) throws UserNotFoundException, ProductNotFoundException {
        JwtAuthentication authInfo = authService.getAuthInfo();

        User user = userService.getUserById(UUID.fromString(authInfo.getId()));
        Product product = productService.getProductById(cartRequest.getProductId());

        user.getProducts().add(product);

        userService.save(user);

        return ResponseEntity.ok("Product added to cart: " + cartRequest.getProductId());
    }

    @DeleteMapping("/cart/{productId}")
    public ResponseEntity<String> deleteFromCart(@PathVariable Integer productId) throws UserNotFoundException, ProductNotFoundException {
        JwtAuthentication authInfo = authService.getAuthInfo();

        User user = userService.getUserById(UUID.fromString(authInfo.getId()));
        Product product = productService.getProductById(productId);

        user.getProducts().remove(product);

        userService.save(user);

        return ResponseEntity.ok("Product deleted from cart: " + productId);
    }
}
