package ru.nag.spring.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nag.spring.domain.Order;
import ru.nag.spring.domain.Role;
import ru.nag.spring.domain.User;
import ru.nag.spring.dto.response.UserResponse;
import ru.nag.spring.exception.OrderException.OrdersNotFoundException;
import ru.nag.spring.exception.UserException.RoleNotFoundException;
import ru.nag.spring.exception.UserException.UserAlreadyExistsException;
import ru.nag.spring.exception.UserException.UserNotFoundException;
import ru.nag.spring.repository.UserRepository;

import java.util.*;


@Service
@RequiredArgsConstructor
public class UserService{

    private final OrderService orderService;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void registerUser(User user) throws UserAlreadyExistsException, RoleNotFoundException {
        if (userRepository.existsUserByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("User with this email already exists");
        }
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        Role role = roleService.getRoleByName("ROLE_USER");

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        user.setRoles(roles);
        userRepository.save(user);
    }

    public User getUserById(UUID id) throws UserNotFoundException {
        return userRepository.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User with this id not found"));
    }

    public User getUserByEmail(String email) throws UserNotFoundException {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with this email not found"));
    }

    public User getUserByEmailOrId(String ident) throws UserNotFoundException {
        try {
            UUID uuid = UUID.fromString(ident);
            return userRepository.findUserById(uuid)
                    .orElseThrow(() -> new UserNotFoundException("User not found with id"));
        } catch (IllegalArgumentException e) {
            return userRepository.findUserByEmail(ident)
                    .orElseThrow(() -> new UserNotFoundException("User not found with email"));
        }
    }

    public void updateUserPassword(User user, String newPassword) {
        user.setPassword(
                passwordEncoder.encode(newPassword)
        );
        userRepository.save(user);
    }

    @Transactional
    public void deleteUserById(UUID id) throws UserNotFoundException, OrdersNotFoundException {
        Optional<User> userOptional = userRepository.findUserById(id);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User with this id not found");
        }

        User user = userOptional.get();

        List<Order> userOrders = orderService.getAllByUser(user);
        for (Order order : userOrders) {
            order.setProducts(new ArrayList<>());
            orderService.save(order);
        }
        orderService.deleteAllByUser(user);

        user.getRoles().clear();
        user.getProducts().clear();

        userRepository.save(user);
        userRepository.deleteUserById(id);


    }

    public UserResponse convertUserToResponse(User user) {
        return new UserResponse(
            user.getId().toString(),
            user.getName(),
            user.getSurname(),
            user.getEmail(),
            user.getRoles()
        );
    }

}
