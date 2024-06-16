package ru.nag.spring.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class UserResponse {

    private String id;
    private String name;
    private String surname;
    private String email;
    private Set<String> roles;

}
