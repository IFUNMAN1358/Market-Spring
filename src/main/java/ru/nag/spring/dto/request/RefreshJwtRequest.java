package ru.nag.spring.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshJwtRequest {

    private String refreshToken;

}
