package com.mhdev.backendservice.dto.responsedto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationResDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String phone;
}
