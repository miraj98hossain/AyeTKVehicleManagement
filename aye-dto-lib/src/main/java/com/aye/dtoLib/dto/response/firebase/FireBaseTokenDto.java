package com.aye.dtoLib.dto.response.firebase;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FireBaseTokenDto {
    private Long id;
    @NotBlank(message = "UserId is required")
    private Integer userId;
    @NotBlank(message = "UserName is required")
    private String userName;
    @NotBlank(message = "Firebase token is required")
    private String fireBaseToken;

}