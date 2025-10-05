package com.mhdev.backendservice.dto.requestdto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginReqDto {
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Password is required")
    private String password;
}
