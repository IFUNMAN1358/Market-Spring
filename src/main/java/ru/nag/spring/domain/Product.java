package ru.nag.spring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Integer id;

    @Column(name = "product_name", nullable = false, length = 100)
    private String productName;

    @Column(name = "product_type", nullable = false, length = 100)
    private String productType;

    @Column(name = "brand", nullable = false, length = 50)
    private String brand;

    @Column(name = "age_category", nullable = false, length = 30)
    private String ageCategory;

    @Column(name = "animal_type", nullable = false, length = 30)
    private String animalType;

    @Column(name = "product_weight", nullable = false)
    private Integer productWeight;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @Column(name = "product_ingredients", nullable = false, length = 1000)
    private String productIngredients;

    @Column(name = "country_of_origin", nullable = false, length = 30)
    private String countryOfOrigin;

    @Column(name = "exp_date_months", nullable = false)
    private Integer expDateMonths;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

}
