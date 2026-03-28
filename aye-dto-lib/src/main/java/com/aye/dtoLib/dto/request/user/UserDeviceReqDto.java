package com.aye.dtoLib.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDeviceReqDto {


    private Long id;

    private Integer userId;

    private Boolean isActive;

}