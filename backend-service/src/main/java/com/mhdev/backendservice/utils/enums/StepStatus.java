package com.mhdev.backendservice.utils.enums;

import lombok.Getter;

@Getter
public enum StepStatus {
    N("New"),
    W("WIP"),
    C("Complete"),
    P("Pick"),
    R("Reject");

    private final String displayName;

    StepStatus(String displayName) {
        this.displayName = displayName;
    }
}
