package ru.nag.spring.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupportRequest {

    private String senderName;
    private String senderSurname;
    private String senderEmail;
    private String issue;

}
