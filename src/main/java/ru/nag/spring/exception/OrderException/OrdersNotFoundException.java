package ru.nag.spring.exception.OrderException;

public class OrdersNotFoundException extends Exception {
    public OrdersNotFoundException(String message) {
        super(message);
    }
}
