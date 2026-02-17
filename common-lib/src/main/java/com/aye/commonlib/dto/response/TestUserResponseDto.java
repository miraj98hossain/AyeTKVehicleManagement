package com.aye.commonlib.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestUserResponseDto {

    private Long id;
    private String username;
    private String email;
    private String createdAt;
    private String status;
    private BigDecimal amount;
}
