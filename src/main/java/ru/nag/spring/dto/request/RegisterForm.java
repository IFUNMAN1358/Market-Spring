package ru.nag.spring.dto.request;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RegisterForm {
    private String name;
    private String surname;
    private String email;
    private String password;
}
