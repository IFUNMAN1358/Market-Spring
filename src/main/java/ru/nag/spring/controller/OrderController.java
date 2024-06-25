package ru.nag.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.nag.spring.domain.Order;
import ru.nag.spring.domain.Product;
import ru.nag.spring.domain.User;
import ru.nag.spring.dto.request.OrderFormRequest;
import ru.nag.spring.exception.OrderException.OrdersNotFoundException;
import ru.nag.spring.exception.UserException.UserNotFoundException;
import ru.nag.spring.jwt.JwtAuthentication;
import ru.nag.spring.service.AuthService;
import ru.nag.spring.service.OrderService;
import ru.nag.spring.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final AuthService authService;

    @GetMapping("/order")
    public ResponseEntity<List<Order>> getUserOrders() throws UserNotFoundException, OrdersNotFoundException {
        JwtAuthentication authInfo = authService.getAuthInfo();

        User user = userService.getUserById(UUID.fromString(authInfo.getId()));
        List<Order> userOrders = orderService.getAllByUser(user);

        return ResponseEntity.ok(userOrders);
    }

    @PostMapping("/order")
    public ResponseEntity<String> createOrder(@RequestBody OrderFormRequest orderData) throws UserNotFoundException {
        JwtAuthentication authInfo = authService.getAuthInfo();

        User user = userService.getUserById(UUID.fromString(authInfo.getId()));

        Order order = new Order();
        Integer totalAmount = 0;

        List<Product> products = user.getProducts();
        for (Product product : products) {
            totalAmount += product.getPrice();
        }

        order.setUser(user);
        order.setProducts(products);
        order.setTotalAmount(totalAmount);
        order.setName(orderData.getName());
        order.setSurname(orderData.getSurname());
        order.setEmail(orderData.getEmail());
        order.setPhone(orderData.getPhone());
        order.setPostalCode(orderData.getPostalCode());
        order.setCity(orderData.getCity());
        order.setStreet(orderData.getStreet());
        order.setHouseNumber(orderData.getHouseNumber());

        user.setProducts(new ArrayList<>());
        userService.save(user);
        orderService.save(order);

        return ResponseEntity.ok("Order created");
    }

}
