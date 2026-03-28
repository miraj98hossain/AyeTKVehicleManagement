package com.aye.dtoLib.dto.response.userData;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MuserHierarchyVResDto {

    private Long fromUserId;


    private String fromUserName;


    private Long fromNUserId;


    private Long fromEmpId;


    private Long toUserId;


    private String toUserName;


    private Long toNUserId;


    private Long toEmpId;


    private Long notInApproval;


    private Long hierarchyCatId;


}
