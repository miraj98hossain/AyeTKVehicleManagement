package com.mhdev.backendservice.utils.enums;

import lombok.Getter;

@Getter
public enum StepStatus {
    PENDING("Pending"),
    WORK_IN_PROGRESS("Work in progress"),
    COMPLETED("Completed"),
    REJECTED("Rejected");

    private final String displayName;
     StepStatus(String displayName){
        this.displayName=displayName;
    }
}
