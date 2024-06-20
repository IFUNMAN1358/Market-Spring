package ru.nag.spring.dto.request;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductRequest {

    private String productName;
    private String productType;
    private String brand;
    private String ageCategory;
    private String animalType;
    private Integer productWeight;
    private Integer stockQuantity;
    private String description;
    private String productIngredients;
    private String countryOfOrigin;
    private Integer expDateMonths;
    private Integer price;
    private MultipartFile image;

}
