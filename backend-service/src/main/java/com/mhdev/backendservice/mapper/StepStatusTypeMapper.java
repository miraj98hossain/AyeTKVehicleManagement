package com.mhdev.backendservice.mapper;


import com.mhdev.backendservice.utils.enums.StepStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface StepStatusTypeMapper {

    @Named("toStepStatusType")
    default StepStatus toStepStatusType(String displayName) {
        if (displayName == null) throw new IllegalArgumentException("Step status type display name:" + null);
        for (StepStatus type : StepStatus.values()) {
            if (type.getDisplayName().equalsIgnoreCase(displayName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown Step status type display name" + displayName);
    }

    @Named("toDisplayName")
    default String toDisplayName(StepStatus stepStatus) {
        return stepStatus != null ? stepStatus.getDisplayName() : null;
    }
}
