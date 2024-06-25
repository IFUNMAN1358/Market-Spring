package ru.nag.spring.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id", insertable = false, updatable = false, nullable = false)
    private Integer orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "order_products",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "total_amount", nullable = false)
    private Integer totalAmount;

    @Column(name = "customer_name", nullable = false)
    private String name;

    @Column(name = "customer_surname", nullable = false)
    private String surname;

    @Column(name = "customer_email", nullable = false)
    private String email;

    @Column(name = "customer_phone", nullable = false)
    private String phone;

    @Column(name = "customer_postal_code", nullable = false)
    private String postalCode;

    @Column(name = "customer_city", nullable = false)
    private String city;

    @Column(name = "customer_street", nullable = false)
    private String street;

    @Column(name = "customer_house_number", nullable = false)
    private String houseNumber;

    @PrePersist
    public void prePersist() {
        this.orderDate = LocalDateTime.now();
    }
}
