package com.aye.commonlib.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class ExecutablesRequest {

    Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be up to 100 characters")
    private String name;

    @NotBlank(message = "File name is required")
    @Size(max = 150, message = "File name must be up to 150 characters")
    private String fileName;

    @NotNull(message = "App module ID is required")
    private Long appModuleId;

    @NotBlank(message = "Executable path is required")
    @Size(max = 255, message = "Executable path must be up to 255 characters")
    private String execPath;

    @NotBlank(message = "Executable type is required")
    @Size(max = 50, message = "Executable type must be up to 50 characters")
    private String execType;

    @NotBlank(message = "Output type is required")
    @Size(max = 50, message = "Output type must be up to 50 characters")
    private String outpuType;

    @NotNull(message = "On request print flag is required")
    private Boolean onReqPrint;

    @NotBlank(message = "Printer name is required")
    @Size(max = 100, message = "Printer name must be up to 100 characters")
    private String printerName;
}

