package com.aye.commonlib.dto.response.userData;


import com.aye.commonlib.dto.response.MUserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by toufiq on 6/5/2021.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDeviceResDto {


    private Long id;

    private MUserResponse muser;

    private Boolean isActive;

}
