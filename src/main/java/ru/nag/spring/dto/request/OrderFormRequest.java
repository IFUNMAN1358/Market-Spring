package ru.nag.spring.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderFormRequest {

    private String name;
    private String surname;
    private String email;

    private String phone;

    private String postalCode;
    private String city;
    private String street;
    private String houseNumber;

}
