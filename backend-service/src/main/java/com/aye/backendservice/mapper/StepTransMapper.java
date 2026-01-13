package com.aye.backendservice.mapper;


import com.aye.commonlib.dto.request.StepTransRequest;
import com.aye.commonlib.dto.response.StepTransResponse;
import com.aye.entitylib.entity.StepTrans;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ReferenceMapper.class,
                StepSetupMapper.class,
                StepTransLinesMapper.class}
        , builder = @Builder(disableBuilder = true))
public interface StepTransMapper {
    StepTrans toEntity(Long id);
    //Long toId(StepTrans value);

    @Mapping(source = "stepSetupId", target = "stepSetup")
    @Mapping(target = "vehicleNumber", expression = "java(combineVehicleInfo(stepTransRequest))")
    StepTrans toEntity(StepTransRequest stepTransRequest);

    @Mapping(source = "stepSetupId", target = "stepSetup")
    @Mapping(target = "vehicleNumber", expression = "java(combineVehicleInfo(stepTransRequest))")
    void toEntity(StepTransRequest stepTransRequest, @MappingTarget StepTrans stepTrans);

    @Mapping(source = "stepSetup.stepSetupId", target = "stepSetupId")
    @Mapping(source = "stepTransLinesList", target = "stepTransLinesResponseList")
    StepTransResponse toResponseDto(StepTrans stepTrans);


    default String combineVehicleInfo(StepTransRequest request) {
        if (request == null) return null;

        String vehicleNumber = request.getVehicleNumber();
        String vhNumber = null;

        if (nonNull(vehicleNumber) && vehicleNumber.length() > 2) {
            vhNumber = vehicleNumber.substring(0, 2) + "-" + vehicleNumber.substring(2);
        } else {
            vhNumber = vehicleNumber;
        }
        return String.join("-",
                nonNull(request.getVehicleCity()) ? request.getVehicleCity() : "",
                nonNull(request.getVehicleCityClass()) ? request.getVehicleCityClass() : ""
        ) + " " + (nonNull(vhNumber) ? vhNumber : "").replaceAll("(^-|-$)", "");
    }

    private boolean nonNull(String str) {
        return str != null && !str.isBlank();
    }
}
