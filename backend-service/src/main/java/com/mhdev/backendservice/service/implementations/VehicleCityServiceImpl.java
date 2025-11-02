package com.mhdev.backendservice.service.implementations;


import com.mhdev.backendservice.mapper.VehicleCityMapper;
import com.mhdev.backendservice.repository.VehicleCityRepository;
import com.mhdev.backendservice.service.VehicleCityClassService;
import com.mhdev.backendservice.service.VehicleCityService;
import com.mhdev.commonlib.dto.response.ApiRequestResponse;
import com.mhdev.commonlib.dto.response.ApiRequestResponseDetail;
import com.mhdev.commonlib.dto.response.VehicleCityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleCityServiceImpl implements VehicleCityService {
    @Autowired
    private VehicleCityRepository vehicleCityRepository;
    @Autowired
    private VehicleCityMapper vehicleCityMapper;
    @Autowired
    private VehicleCityClassService vehicleCityClassService;

    @Override
    public ApiRequestResponse getAllVehicleCity() {
        ApiRequestResponse response = new ApiRequestResponse();
        List<VehicleCityResponse> vehicleCityList = vehicleCityRepository.findAll()
                .stream()
                .map(vehicleCityMapper::toResponseDto)
                .toList();
        var vehClsRes = vehicleCityClassService.getAllVehicleCityClass();
        response.setHttpStatus(HttpStatus.OK.name());
        response.setMessage("Successfully fetched city names");
        ApiRequestResponseDetail details = ApiRequestResponseDetail.builder()
                .objectTag("vhCtyResList")
                .object(vehicleCityList)
                .mapperClass(VehicleCityResponse.class.getName())
                .objectType(ApiRequestResponseDetail.ObjectType.A)
                .build();
        response.getApiRequestResponseDetails().add(details);
        response.getApiRequestResponseDetails().addAll(vehClsRes.getApiRequestResponseDetails());
        return response;
    }
}
