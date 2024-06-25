package ru.nag.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nag.spring.domain.Order;
import ru.nag.spring.domain.User;
import ru.nag.spring.repository.OrderRepository;
import ru.nag.spring.exception.OrderException.OrdersNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void save(Order order) {
        orderRepository.save(order);
    }

    public void deleteAllByUser(User user) {
        orderRepository.deleteAllByUser(user);
    }

    public List<Order> getAllByUser(User user) throws OrdersNotFoundException {
        return orderRepository.findAllByUser(user).orElseThrow(
                () -> new OrdersNotFoundException("Orders not found")
        );
    }

}
