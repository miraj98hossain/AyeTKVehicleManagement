package com.mhdev.webservice.dto.responsedto;

import lombok.Data;

@Data
public class RegistrationResDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String phone;
}
