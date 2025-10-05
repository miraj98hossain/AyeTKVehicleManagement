package com.mhdev.backendservice.dto.responsedto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RegistrationResDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String phone;
}
