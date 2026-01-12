package com.aye.backendservice.service;

import com.aye.RestfulServer.model.Muser;
import com.aye.RestfulServer.model.om.BeforeTripV;
import com.aye.RestfulServer.model.om.BeforeTripWDsV;
import com.aye.RestfulServer.service.MuserService;
import com.aye.backendservice.entity.StepTrans;
import com.aye.backendservice.entity.StepTransDetails;
import com.aye.backendservice.entity.StepTransDetailsLines;
import com.aye.backendservice.mapper.StepTransDetailsLinesMapper;
import com.aye.backendservice.mapper.StepTransDetailsMapper;
import com.aye.backendservice.repository.StepTransDetailsLinesRepository;
import com.aye.backendservice.repository.StepTransDetailsRepository;
import com.aye.commonlib.dto.request.StepTransDetailsLinesRequest;
import com.aye.commonlib.dto.response.ApiRequestResponse;
import com.aye.commonlib.dto.response.ApiRequestResponseDetail;
import com.aye.commonlib.dto.response.StepTransDetailsLinesResponse;
import com.aye.commonlib.dto.response.StepTransDetailsResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class StepTransDetailsServiceImpl implements StepTransDetailsService {
    @Autowired
    NoGenService noGenService;
    @Autowired
    private StepTransDetailsRepository stepTrnsDtlRepo;
    @Autowired
    private StepTransDetailsLinesRepository stepTrnsDtlLnRepo;
    @Autowired
    private StepTransDetailsMapper stepTransDetailsMapper;
    @Autowired
    private StepTransDetailsLinesMapper stepTrnsDtlLnMapper;
    @Autowired
    private MuserService muserService;
//    @Autowired
//    private BeforeTripVService beforeTripVService;
//    @Autowired
//    private BeforeTripWDsVService beforeTripWDsVService;


//    @Override
//    @Transactional
//    public ApiRequestResponse save(StepTransDetailsRequest stepTransDRequest, String userName) {
//        Muser muser = muserService.findByUserName(userName);
//        //*********** Update Section
//        if (stepTransDRequest.getStepTransDtlId() != null) {
//            var dbTransDtl = stepTrnsDtlRepo.findById(stepTransDRequest.getStepTransDtlId()).orElseThrow(
//                    () -> new EntityNotFoundException("StepTransDtl Not Found with id: " + stepTransDRequest.getStepTransDtlId())
//            );
//            stepTransDetailsMapper.dtoToEntity(stepTransDRequest, dbTransDtl);
//            dbTransDtl.setUpdatedBy(Long.valueOf(muser.getId()));
//            dbTransDtl = stepTrnsDtlRepo.save(dbTransDtl);
//            return ApiRequestResponseMaker.make(
//                    HttpStatus.OK.name(),
//                    "Success",
//                    ApiRequestResponseDetail.ObjectType.O,
//                    "stepTransDtl",
//                    StepTransDetailsResponse.class.getName(),
//                    stepTransDetailsMapper.toResponseDto(dbTransDtl)
//            );
//        }
//        final StepTransDetails stepTransDetails = stepTransDetailsMapper.dtoToEntity(stepTransDRequest);
//        //***************** Checking Schedule no
//        if (stepTransDRequest.getScheduleNo() != null && !stepTransDRequest.getScheduleNo().isEmpty()) {
//            // fetch existing records
//            List<StepTransDetails> dbExistList =
//                    stepTrnsDtlRepo.findAllByScheduleNo(stepTransDRequest.getScheduleNo());
//            // extract existing delivery numbers
//            Set<Long> existingDeliveryNos = dbExistList.stream()
//                    .map(StepTransDetails::getOrderNumber)
//                    .collect(Collectors.toSet());
//            List<StepTransDetails> stDtls = new ArrayList<>();
//            var list = beforeTripWDsVService
//                    .findAllByScheduleNumber(stepTransDRequest.getScheduleNo());
//
//            Map<Long, List<BeforeTripWDsV>> groupedOrderNumber = list.stream()
//                    .collect(groupingBy(BeforeTripWDsV::getOrderNumber));
//            list.forEach(element -> {
//                // skip if delivery already exists
//                if (existingDeliveryNos.contains(element.getOrderNumber())) {
//                    return;
//                }
//                //adding to check duplicate entry.
//                existingDeliveryNos.add(element.getOrderNumber());
//
//                StepTransDetails steptrnDtl = bfrTrpWDSToStpTrnDtl(element, stepTransDetails.getStepTrans(), muser);
//                //getting the item data from grouped map
//                List<StepTransDetailsLines> sdls =
//                        groupedOrderNumber.get(steptrnDtl.getOrderNumber())
//                                .stream()
//                                .map(beforeTripWDsV -> {
//                                    return bfrTrpWDSToStpTrnDtlLn(beforeTripWDsV, steptrnDtl, muser);
//                                })
//                                .collect(Collectors.toCollection(ArrayList::new));
//
//                steptrnDtl.getStepTransDetailsLines().addAll(sdls);
//                stDtls.add(steptrnDtl);
//                sdls.clear();
//            });
//            var res = stepTrnsDtlRepo.saveAll(stDtls);
//            return ApiRequestResponseMaker.make(
//                    HttpStatus.OK.name(),
//                    "Success",
//                    ApiRequestResponseDetail.ObjectType.A,
//                    "stepTransDtl",
//                    StepTransDetailsResponse.class.getName(),
//                    res.stream().map(this.stepTransDetailsMapper::toResponseDto)
//                            .collect(Collectors.toList())
//            );
//        }
//        //***************** Checking Delivery no
//        if (stepTransDRequest.getOrderNumber() != null) {
//
//            List<StepTransDetails> dbExistList =
//                    stepTrnsDtlRepo.findAllByOrderNumber(stepTransDRequest.getOrderNumber());
//            // extract existing delivery numbers
//            Set<Long> existingDeliveryNos = dbExistList.stream()
//                    .map(StepTransDetails::getOrderNumber)
//                    .collect(Collectors.toSet());
//            List<StepTransDetails> stDtls = new ArrayList<>();
//            var list = beforeTripVService.findAllByOrderNumber(stepTransDRequest.getOrderNumber());
//            Map<Long, List<BeforeTripV>> groupedOrderNumber = list.stream()
//                    .collect(groupingBy(BeforeTripV::getOrderNumber));
//            list.forEach(element -> {
//
//                // skip if delivery already exists
//                if (existingDeliveryNos.contains(element.getOrderNumber())) {
//                    return;
//                }
//                //adding to check duplicate entry.
//                existingDeliveryNos.add(element.getOrderNumber());
//                StepTransDetails steptrnDtl = bfrTrpToStpTrnDtl(element, stepTransDetails.getStepTrans(), muser);
////                steptrnDtl.setStepTrans(stepTransDetails.getStepTrans());
////                steptrnDtl.setCreatedBy(Long.valueOf(muser.getId()));
////                steptrnDtl.setStepTransDtlNo(noGenService.createTransDNo());
////                steptrnDtl.setCustAccountId(element.getCustAccountId());
////                steptrnDtl.setCustName(element.getPartyName());
////                steptrnDtl.setDeliveryNo(element.getOrderNumber());
//
//                //getting the item data from grouped map
//                List<StepTransDetailsLines> sdls =
//                        groupedOrderNumber.get(steptrnDtl.getOrderNumber())
//                                .stream()
//                                .map(beforeTripV -> {

    /// /                                    StepTransDetailsLines sdl = new StepTransDetailsLines();
    /// /                                    sdl.setStepTransDetails(steptrnDtl);
    /// /                                    sdl.setStepTransDtlLnNo(noGenService.createTransDtlLNo());
    /// /                                    sdl.setInvItemId(beforeTripV.getInventoryItemId());
    /// /                                    sdl.setOrderedItem(beforeTripV.getOrderedItem());
    /// /                                    sdl.setOrderedQuantity(beforeTripV.getOrderedQuantity());
    /// /                                    sdl.setCreatedBy(Long.valueOf(muser.getId()));
    /// /                                    return sdl;
//                                    return bfrTrpToStpTrnDtlLn(beforeTripV, steptrnDtl, muser);
//                                })
//                                .collect(Collectors.toCollection(ArrayList::new));
//                steptrnDtl.getStepTransDetailsLines().addAll(sdls);
//                stDtls.add(steptrnDtl);
//                sdls.clear();
//            });
//            var res = stepTrnsDtlRepo.saveAll(stDtls);
//            return ApiRequestResponseMaker.make(
//                    HttpStatus.OK.name(),
//                    "Success",
//                    ApiRequestResponseDetail.ObjectType.A,
//                    "stepTransDtl",
//                    StepTransDetailsResponse.class.getName(),
//                    res.stream().map(this.stepTransDetailsMapper::toResponseDto)
//                            .collect(Collectors.toList())
//            );
//
//        }
//        stepTransDetails.setCreatedBy(Long.valueOf(muser.getId()));
//        stepTransDetails.setStepTransDtlNo(noGenService.createTransDNo());
//        var res = stepTrnsDtlRepo.save(stepTransDetails);
//        return ApiRequestResponseMaker.make(
//                HttpStatus.OK.name(),
//                "Success",
//                ApiRequestResponseDetail.ObjectType.O,
//                "stepTransDtl",
//                StepTransDetailsResponse.class.getName(),
//                stepTransDetailsMapper.toResponseDto(res)
//        );
//    }
    private StepTransDetailsLines bfrTrpWDSToStpTrnDtlLn(BeforeTripWDsV beforeTripWDsV, StepTransDetails stpTrnDtl, Muser muser) {
        StepTransDetailsLines sdl = new StepTransDetailsLines();
        sdl.setStepTransDetails(stpTrnDtl);
        sdl.setStepTransDtlLnNo(noGenService.createTransDtlLNo());
        sdl.setInvItemId(beforeTripWDsV.getInventoryItemId());
        sdl.setOrderedItem(beforeTripWDsV.getOrderedItem());
        sdl.setOrderedQuantity(beforeTripWDsV.getOrderedQuantity());
        sdl.setCreatedBy(Long.valueOf(muser.getId()));
        return sdl;
    }

    private StepTransDetails bfrTrpWDSToStpTrnDtl(BeforeTripWDsV beforeTripWDsV, StepTrans stepTrans, Muser muser) {
        StepTransDetails steptrnDtl = new StepTransDetails();
        steptrnDtl.setStepTrans(stepTrans);
        steptrnDtl.setCreatedBy(Long.valueOf(muser.getId()));
        steptrnDtl.setStepTransDtlNo(noGenService.createTransDNo());
        steptrnDtl.setCustAccountId(beforeTripWDsV.getCustAccountId());
        steptrnDtl.setCustName(beforeTripWDsV.getPartyName());
        steptrnDtl.setScheduleNo(beforeTripWDsV.getScheduleNumber());
        steptrnDtl.setOrderNumber(beforeTripWDsV.getOrderNumber());
        return steptrnDtl;
    }


    private StepTransDetailsLines bfrTrpToStpTrnDtlLn(BeforeTripV beforeTripV, StepTransDetails stpTrnDtl, Muser muser) {
        StepTransDetailsLines sdl = new StepTransDetailsLines();
        sdl.setStepTransDetails(stpTrnDtl);
        sdl.setStepTransDtlLnNo(noGenService.createTransDtlLNo());
        sdl.setInvItemId(beforeTripV.getInventoryItemId());
        sdl.setOrderedItem(beforeTripV.getOrderedItem());
        sdl.setOrderedQuantity(beforeTripV.getOrderedQuantity());
        sdl.setCreatedBy(Long.valueOf(muser.getId()));
        return sdl;
    }

    private StepTransDetails bfrTrpToStpTrnDtl(BeforeTripV beforeTripV, StepTrans stepTrans, Muser muser) {
        StepTransDetails steptrnDtl = new StepTransDetails();
        steptrnDtl.setStepTrans(stepTrans);
        steptrnDtl.setCreatedBy(Long.valueOf(muser.getId()));
        steptrnDtl.setStepTransDtlNo(noGenService.createTransDNo());
        steptrnDtl.setCustAccountId(beforeTripV.getCustAccountId());
        steptrnDtl.setCustName(beforeTripV.getPartyName());
        steptrnDtl.setOrderNumber(beforeTripV.getOrderNumber());
        return steptrnDtl;
    }


    @Override
    @Transactional(readOnly = true)
    public ApiRequestResponse findById(Long stepTransDtlId) {
        StepTransDetails stepTransDetails = stepTrnsDtlRepo.findById(stepTransDtlId).orElseThrow(
                () -> new EntityNotFoundException("StepTransDtl Not Found with id: " + stepTransDtlId)
        );
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.O,
                "stepTransDtl",
                StepTransDetailsResponse.class.getName(),
                stepTransDetailsMapper.toResponseDto(stepTransDetails)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public ApiRequestResponse findAllByStepTransId(Long stepTransId) {
        List<StepTransDetails> stepTransDetails = stepTrnsDtlRepo.findAllByStepTrans_StepTransId(stepTransId);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.A,
                "stepTransDtlList",
                StepTransDetailsResponse.class.getName(),
                stepTransDetails.stream().map(stepTransDetailsMapper::toResponseDto).toList()
        );
    }

    @Transactional
    @Override
    public ApiRequestResponse deleteById(Long stepTransDtlId) {
        this.stepTrnsDtlRepo.deleteById(stepTransDtlId);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Successfully Deleted",
                null, null, null, null
        );
    }

    @Transactional
    @Override
    public void deleteAllByStepTransId(Long stepTransId) {
        this.stepTrnsDtlRepo.deleteAllByStepTrans_StepTransId(stepTransId);
    }

    //***Line Section*********************
    @Override
    @Transactional
    public ApiRequestResponse saveStDtlLine(StepTransDetailsLinesRequest stepTrnsDtlLnsReq, String userName) {
        Muser muser = muserService.findByUserName(userName);
        if (stepTrnsDtlLnsReq.getStepTransDtlLnId() != null) {
            var dbTransDtl = stepTrnsDtlLnRepo.findById(stepTrnsDtlLnsReq.getStepTransDtlLnId()).orElseThrow(
                    () -> new EntityNotFoundException("Step Trans Details Line Not Found with id: " + stepTrnsDtlLnsReq.getStepTransDtlLnId())
            );
            stepTrnsDtlLnMapper.dtoToEntity(stepTrnsDtlLnsReq, dbTransDtl);
            dbTransDtl.setUpdatedBy(Long.valueOf(muser.getId()));
            dbTransDtl = stepTrnsDtlLnRepo.save(dbTransDtl);
            return ApiRequestResponseMaker.make(
                    HttpStatus.OK.name(),
                    "Success",
                    ApiRequestResponseDetail.ObjectType.O,
                    "stepTransDtlLn",
                    StepTransDetailsLinesResponse.class.getName(),
                    stepTrnsDtlLnMapper.toResponseDto(dbTransDtl)
            );
        }
        StepTransDetailsLines stepTransDetails = stepTrnsDtlLnMapper.dtoToEntity(stepTrnsDtlLnsReq);
        stepTransDetails.setCreatedBy(Long.valueOf(muser.getId()));
        stepTransDetails.setStepTransDtlLnNo(noGenService.createTransDtlLNo());
        stepTransDetails = stepTrnsDtlLnRepo.save(stepTransDetails);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.O,
                "stepTransDtlLn",
                StepTransDetailsLinesResponse.class.getName(),
                stepTrnsDtlLnMapper.toResponseDto(stepTransDetails)
        );

    }

    @Override
    @Transactional(readOnly = true)
    public ApiRequestResponse findStDtlLineById(Long stepTransDtlLnId) {
        StepTransDetailsLines stepTransDetailsLines = stepTrnsDtlLnRepo.findById(stepTransDtlLnId).orElseThrow(
                () -> new EntityNotFoundException("Step Trans Details Line Not Found with id: " + stepTransDtlLnId)
        );
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.O,
                "stepTransDtlLn",
                StepTransDetailsLinesResponse.class.getName(),
                stepTrnsDtlLnMapper.toResponseDto(stepTransDetailsLines)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public ApiRequestResponse findAllByStTrnDtlId(Long stepTransDtlId) {
        List<StepTransDetailsLines> stepTransDetailsLines = stepTrnsDtlLnRepo
                .findAllByStepTransDetails_StepTransDtlId(stepTransDtlId);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Success",
                ApiRequestResponseDetail.ObjectType.A,
                "stepTransDtlLnList",
                StepTransDetailsLinesResponse.class.getName(),
                stepTransDetailsLines.stream().map(stepTrnsDtlLnMapper::toResponseDto).toList()
        );
    }

    @Transactional
    @Override
    public ApiRequestResponse deleteLineById(Long stepTransDtlLnId) {
        this.stepTrnsDtlLnRepo.deleteById(stepTransDtlLnId);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(),
                "Successfully Deleted",
                null, null, null, null
        );
    }

}
