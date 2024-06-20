package ru.nag.spring.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NameAndSurnameRequest {
    private String name;
    private String surname;
}
