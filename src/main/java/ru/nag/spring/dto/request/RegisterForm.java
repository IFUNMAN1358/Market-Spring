package ru.nag.spring.dto.request;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RegisterForm {
    String name;
    String surname;
    String email;
    String password;
}
