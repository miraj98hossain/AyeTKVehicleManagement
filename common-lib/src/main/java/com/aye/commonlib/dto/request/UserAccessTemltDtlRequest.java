package com.aye.commonlib.dto.request;

import com.aye.commonlib.dto.response.UserMenuResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;


@Data
public class UserAccessTemltDtlRequest {

    private Integer id;
    @NotNull(message = "Org hierarchy ID is required")
    private Integer orgHierarchyId;
    @NotNull(message = "Template ID is required")
    private Integer UserAccessTemltId;

    @NotNull(message = "User menu is required")
    private UserMenuResponse userMenu;

    @NotBlank(message = "Menu header name is required")
    @Size(max = 100, message = "Menu header name must be up to 100 characters")
    private String menuHeaderName;

    @NotBlank(message = "Menu level is required")
    @Size(max = 10, message = "Menu level must be up to 10 characters")
    private String menuLevel;

    @NotBlank(message = "Organization hierarchy readable name is required")
    @Size(max = 100, message = "OrgHer name must be up to 100 characters")
    private String orgHerName;

    @NotBlank(message = "Request group header name is required")
    @Size(max = 100, message = "Request group header name must be up to 100 characters")
    private String reqGrpHdrName;

    @NotBlank(message = "Start date is required")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Start date must be in format yyyy-MM-dd")
    private String startDate;

    @NotBlank(message = "End date is required")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "End date must be in format yyyy-MM-dd")
    private String endDate;

    @NotBlank(message = "Detail name is required")
    @Size(max = 200, message = "Detail name must be up to 200 characters")
    private String detailName;

    @NotNull(message = "User specific flag is required")
    private Boolean userSpecefic;

    @NotEmpty(message = "User access inventory organizations cannot be empty")
    private List<@Valid UserAccessInvOrgRequest> userAccessInvOrgs;
}

