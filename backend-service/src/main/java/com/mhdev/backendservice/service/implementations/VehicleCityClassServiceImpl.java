package com.mhdev.backendservice.service.implementations;


import com.mhdev.backendservice.mapper.VehicleCityClassMapper;
import com.mhdev.backendservice.repository.VehicleCityClassRepository;
import com.mhdev.backendservice.service.VehicleCityClassService;
import com.mhdev.commonlib.dto.response.ApiRequestResponse;
import com.mhdev.commonlib.dto.response.ApiRequestResponseDetail;
import com.mhdev.commonlib.dto.response.VehicleCityClassResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleCityClassServiceImpl implements VehicleCityClassService {
    @Autowired
    private VehicleCityClassRepository vehicleCityClassRepository;
    @Autowired
    private VehicleCityClassMapper vehicleCityClassMapper;

    @Override
    public ApiRequestResponse getAllVehicleCityClass() {
        ApiRequestResponse response = new ApiRequestResponse();

        List<VehicleCityClassResponse> vehicleCityList = vehicleCityClassRepository.findAll()
                .stream()
                .map(vehicleCityClassMapper::toResponseDto)
                .toList();

        response.setHttpStatus(HttpStatus.OK.name());
        response.setMessage("Successfully fetched city names");
        ApiRequestResponseDetail details = ApiRequestResponseDetail.builder()
                .objectTag("vhCtyClsResList")
                .object(vehicleCityList)
                .mapperClass(VehicleCityClassResponse.class.getName())
                .objectType(ApiRequestResponseDetail.ObjectType.A)
                .build();
        response.getApiRequestResponseDetails().add(details);
        return response;
    }
}
