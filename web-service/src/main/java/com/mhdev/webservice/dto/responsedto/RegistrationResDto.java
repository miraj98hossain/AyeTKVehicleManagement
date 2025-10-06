package com.mhdev.webservice.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class RegistrationResDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String phone;
}
