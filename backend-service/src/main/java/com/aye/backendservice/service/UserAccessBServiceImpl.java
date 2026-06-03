package com.aye.backendservice.service;


import com.aye.RestfulServer.service.*;
import com.aye.backendservice.applicationEvent.UserAccessCacheGenerateEvent;
import com.aye.backendservice.applicationEvent.UserAccessCacheSyncEvent;
import com.aye.backendservice.applicationEvent.UserAccessTemplateCacheSyncEvent;
import com.aye.dtoLib.dto.request.*;
import com.aye.dtoLib.dto.response.ApiRequestResponse;
import com.aye.dtoLib.dto.response.ApiRequestResponseDetail;
import com.aye.dtoLib.dto.response.order.OrdTrnsTypesVDto;
import com.aye.dtoLib.dto.response.userOrg.*;
import com.aye.entitylib.entity.*;
import com.aye.entitylib.entity.order.OrdTrnsTypesV;
import com.aye.entitylib.entity.user.Muser;
import com.aye.enums.RoleTypes;
import com.aye.enums.TrnsType;
import com.aye.mapper.order.OrdTrnsTypesVMapper;
import com.aye.mapper.userOrg.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.aye.backendservice.utils.RedisKey.USER_LOGIN_RESPONSE_HASH_KEY;
import static com.aye.backendservice.utils.RedisKey.USER_LOGIN_RESPONSE_KEY;

@Service
public class UserAccessBServiceImpl implements UserAccessBService {

    @Autowired
    private OrgHierarchyService orgHierarchyService;
    @Autowired
    private InvInformationsService invInfoService;
    @Autowired
    private UserAccessTempltService usrAcsTempService;
    @Autowired
    private UserAccessInvOrgService usrAcsInvOrgService;
    @Autowired
    private MuserService mUserService;
    @Autowired
    private UserTrnsAndSubinvAccessService transSubInvAcService;
    @Autowired
    private OrdTrnsTypesVMapper ordTrnsTypesVMapper;
    @Autowired
    private UserAccessTempltMapper tempMapper;
    @Autowired
    private UserAccessTemltDtlMapper tempDtlMapper;
    @Autowired
    private UserAccessMapper userAccessMapper;
    @Autowired
    private UserTransactionTypesMapper transTypesMapper;
    @Autowired
    private UserAccessInvOrgMapper userAccessInvOrgMapper;
    @Autowired
    private UserAccessService userAccessService;
    @Autowired
    private UserTrnsAndSubinvAccessBService userTrnsAndSubinvAccessBService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ApiRequestResponse getAllTemplet() {
        List<UserAccessTempltResponse> list = this.usrAcsTempService.getAllTemplet()
                .stream().map(tempMapper::toResponseDto).toList();

        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Successfully Fetched All UserAccessTemplate",
                ApiRequestResponseDetail.ObjectType.A, "AllTemplate",
                UserAccessTempltResponse.class.getName(), list
        );

    }

    @Override
    public void generateCache() {
        this.eventPublisher.publishEvent(new UserAccessCacheGenerateEvent(this));
//        List<UserAccess> allList = userAccessService.getAllUserAccess();
//
//        Map<Integer, List<Integer>> map = allList.stream()
//                .collect(Collectors.groupingBy(
//                        ua -> ua.getUserAccessTemplt().getId(),
//                        Collectors.mapping(e -> e.getUser().getId(), Collectors.toList())
//                ));
//
//        map.forEach((tempId, list) -> {
//
//            String key = "USER-ACCESS-TEMPLATE:TEMPLATE:" + tempId;
//
//            list.stream()
//                    .map(String::valueOf)
//                    .forEach(userId ->
//                            redisTemplate.opsForSet().add(key, userId)
//                    );
//        });
    }


    @Override
    public ApiRequestResponse findUserAccessById(Integer id) {
        UserAccessResponse userAccessesRes = this.userAccessMapper.toResponseDto(this.userAccessService.findById(id));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Successfully Fetched  UserAccess",
                ApiRequestResponseDetail.ObjectType.O, "userAccess",
                UserAccessResponse.class.getName(), userAccessesRes
        );
    }

    @Override
    public ApiRequestResponse getUserAccessByUserName(String username, String roleType) {
        Muser curUser = this.mUserService.findByUserName(username.toUpperCase());
        var list = this.userAccessService.getUserAccess(curUser, RoleTypes.valueOf(roleType))
                .stream().map(tempDtlMapper::toResponseDto).toList();
        ApiRequestResponse response = new ApiRequestResponse();
        response.setHttpStatus(HttpStatus.OK.name());
        response.setMessage("Success");
        List<ApiRequestResponseDetail> detailsResList = new ArrayList<>();
        ApiRequestResponseDetail details = ApiRequestResponseDetail.builder()
                .objectTag("manus")
                .object(list)
                .mapperClass(UserAccessTemltDtlResponse.class.getName())
                .objectType(ApiRequestResponseDetail.ObjectType.A)
                .build();
        detailsResList.add(details);
        response.setApiRequestResponseDetails(detailsResList);
        return response;
    }

    @Override
    public ApiRequestResponse getUserAccessFromCacheByUserName(String username, String roleType) {
        try {
            RoleTypes roleTypes = RoleTypes.valueOf(roleType);
            Muser curUser = this.mUserService.findByUserName(username.toUpperCase());
            Object object = redisTemplate.opsForHash().get(USER_LOGIN_RESPONSE_KEY, USER_LOGIN_RESPONSE_HASH_KEY + ":" + curUser.getId());
            assert object != null;
            List<UserAccessTemltDtlResponse> list =
                    objectMapper.readValue(
                            object.toString(),
                            new TypeReference<List<UserAccessTemltDtlResponse>>() {
                            }
                    );
            list = list.stream()
                    .filter(e -> e.getUserMenu().getPageType()
                            .equals(roleTypes))
                    .collect(Collectors.toList());
            ApiRequestResponse response = new ApiRequestResponse();
            response.setHttpStatus(HttpStatus.OK.name());
            response.setMessage("Success");
            List<ApiRequestResponseDetail> detailsResList = new ArrayList<>();
            ApiRequestResponseDetail details = ApiRequestResponseDetail.builder()
                    .objectTag("manus")
                    .object(list)
                    .mapperClass(UserAccessTemltDtlResponse.class.getName())
                    .objectType(ApiRequestResponseDetail.ObjectType.A)
                    .build();
            detailsResList.add(details);
            response.setApiRequestResponseDetails(detailsResList);
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ApiRequestResponse findByUserId(Integer userId) {
        List<UserAccessResponse> userAccessesRes = this.userAccessService.findByUserId(userId)
                .stream().map(userAccessMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Successfully Fetched All UserAccess",
                ApiRequestResponseDetail.ObjectType.A, "AllUserAccess",
                UserAccessResponse.class.getName(), userAccessesRes
        );
    }

    @Transactional
    @Override
    public ApiRequestResponse saveDtlLine(UserAccessRequest userAccessRequest) {
        UserAccess userAccess = this.userAccessMapper.dtoToUserAccess(userAccessRequest);
        Muser muser = this.mUserService.findById(userAccessRequest.getUserId());
        UserAccessTemplt userAccessTemplt = this.usrAcsTempService.findById(userAccessRequest.getUserAccessTempltId());
        userAccess.setUser(muser);
        userAccess.setUserAccessTemplt(userAccessTemplt);
        this.userAccessService.saveDtlLine(userAccess);
        this.eventPublisher.publishEvent(new UserAccessCacheSyncEvent(this, muser.getId(), userAccessTemplt.getId()));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Successfully Created UserAccess",
                null, "",
                null, null
        );
    }

    @Transactional
    @Override
    public ApiRequestResponse saveUserAccessTemp(UserAccessTempltRequest userAccessTempRequest) throws Exception {
        UserAccessTemplt userAccessTemplt = this.tempMapper.dtoToEntity(userAccessTempRequest);
        userAccessTemplt = this.usrAcsTempService.save(userAccessTemplt);
        this.eventPublisher.publishEvent(new UserAccessTemplateCacheSyncEvent(this, userAccessTemplt.getId()));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                null, "",
                null, null
        );
    }

    @Override
    @Transactional
    public ApiRequestResponse saveUserAccessTempDtl(UserAccessTemltDtlRequest usrAcsTempDtlReq) throws Exception {
        UserAccessTemplt userAccessTemplt = this.usrAcsTempService.findById(usrAcsTempDtlReq.getUserAccessTempltId());
        UserAccessTemltDtl userAccessTemltDtl = this.tempDtlMapper.dtoToEntity(usrAcsTempDtlReq);
        userAccessTemltDtl.setUserAccessTemplt(userAccessTemplt);
        userAccessTemltDtl.setRequestGroupHeader(null);
        this.usrAcsTempService.saveDtlLine(userAccessTemltDtl);
        this.eventPublisher.publishEvent(new UserAccessTemplateCacheSyncEvent(this, userAccessTemplt.getId()));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                null, "",
                null, null
        );
    }

    @Override
    public ApiRequestResponse findTempById(Integer id) {
        UserAccessTempltResponse templtResponse = this.tempMapper
                .toResponseDto(this.usrAcsTempService.findById(id));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "userAccessTemplt",
                UserAccessTempltResponse.class.getName(), templtResponse
        );
    }

    @Override
    public ApiRequestResponse findTempDtlByDtlId(Integer id) {
        UserAccessTemltDtlResponse accessTemltDtlResponse = this.tempDtlMapper.toResponseDto(
                this.usrAcsTempService.findByDtlId(id)
        );
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.O, "userAccessTempltDtl",
                UserAccessTemltDtlResponse.class.getName(), accessTemltDtlResponse
        );
    }

    @Override
    public ApiRequestResponse findByTempHdrId(Integer id) {
        List<UserAccessTemltDtlResponse> dtlResponseList =
                this.usrAcsTempService.findByTempHdrId(id)
                        .stream().map(this.tempDtlMapper::toResponseDto).toList();

        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "dtlResponseList",
                UserAccessTemltDtlResponse.class.getName(), dtlResponseList
        );
    }

    //***********************Inventory Orgs**********************************
    @Override
    public ApiRequestResponse findAllUsrAccessOrg() {
        List<UserAccessInvOrgResponse> invOrg = this.usrAcsInvOrgService.findAll()
                .stream().map(userAccessInvOrgMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Successfully Fetched  Inv Orgs",
                ApiRequestResponseDetail.ObjectType.A, "userAccInvOrgList",
                UserAccessInvOrgResponse.class.getName(), invOrg
        );
    }

    @Override
    public ApiRequestResponse findByInvOrgs(Long inventoryInformation) {
        List<UserAccessInvOrgResponse> invOrg = this.usrAcsInvOrgService.findByInvOrgs(inventoryInformation)
                .stream().map(userAccessInvOrgMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Successfully Fetched  Inv Orgs",
                ApiRequestResponseDetail.ObjectType.A, "userAccInvOrgList",
                UserAccessInvOrgResponse.class.getName(), invOrg
        );
    }

    @Override
    public ApiRequestResponse findUsrAccInvOrgByTempDtlId(Integer tempDtlId) {
        List<UserAccessInvOrgResponse> invOrg = this.usrAcsInvOrgService.findByUserAccessTemltDtl(tempDtlId)
                .stream().map(userAccessInvOrgMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Successfully Fetched  Inv Orgs",
                ApiRequestResponseDetail.ObjectType.A, "userAccInvOrgList",
                UserAccessInvOrgResponse.class.getName(), invOrg
        );
    }

    @Override
    public ApiRequestResponse findUsrAccessInvOrgById(Long id) throws Exception {
        UserAccessInvOrgResponse invOrg = this.userAccessInvOrgMapper
                .toResponseDto(this.usrAcsInvOrgService.findById(id));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Successfully Fetched  Inv Orgs",
                ApiRequestResponseDetail.ObjectType.O, "userAccInvOrg",
                UserAccessInvOrgResponse.class.getName(), invOrg
        );
    }

    @Transactional
    @Override
    public ApiRequestResponse saveUsrAccessInvOrg(UserAccessInvOrgRequest usrAccInvOrgReq, String userName) throws Exception {
        Muser muser = this.mUserService.findByUserName(userName);
        CommonColumn cc = new CommonColumn();
        UserAccessTemltDtl userAccessTemltDtl = this.usrAcsTempService.findByDtlId(usrAccInvOrgReq.getUserAccessTemltDtlId());
        UserAccessInvOrg userAccessInvOrg = this.userAccessInvOrgMapper.dtoToEntity(usrAccInvOrgReq);
        if (userAccessInvOrg.getId() != null) {
            var db = this.usrAcsInvOrgService.findById(userAccessInvOrg.getId());
            cc = db.getColumn();
            cc.setLastUpdateBy(muser.getId());
            cc.setLastUpdateDate(new Date());
            db.setColumn(cc);
            db.setInvOrgs(userAccessInvOrg.getInvOrgs());
            db.setUserAccessTemltDtl(userAccessTemltDtl);
            this.usrAcsInvOrgService.save(db);
            this.eventPublisher.publishEvent(new UserAccessTemplateCacheSyncEvent(this, userAccessTemltDtl.getUserAccessTemplt().getId()));
            return ApiRequestResponseMaker.make(
                    HttpStatus.OK.name(), "Successfully",
                    null, null,
                    null, null
            );
        }
        //*****************************Create New ***************************************************************
        userAccessInvOrg.setUserAccessTemltDtl(userAccessTemltDtl);
        cc.setCreationDate(new Date());
        cc.setCreatedBy(muser);
        userAccessInvOrg.setColumn(cc);
        this.usrAcsInvOrgService.save(userAccessInvOrg);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Successfully",
                null, null,
                null, null
        );
    }

    @Transactional
    @Override
    public ApiRequestResponse deleteUsrAccessInvOrg(Long id) {
        UserAccessInvOrg userAccessInvOrg = this.usrAcsInvOrgService.findById(id);
        Integer userAccessTempId = userAccessInvOrg.getUserAccessTemltDtl().getUserAccessTemplt().getId();
        this.usrAcsInvOrgService.delete(id);
        this.eventPublisher.publishEvent(new UserAccessTemplateCacheSyncEvent(this, userAccessTempId));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Successfully",
                null, null,
                null, null
        );
    }

    //***********************Trans Types**********************************
    @Override
    public ApiRequestResponse findByInvAccess(Long userAccessInvOrgId) {
        List<UserTransactionTypesResponse> list = this.transSubInvAcService.findByInvAccess(userAccessInvOrgId)
                .stream().map(transTypesMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Successfully Fetched Types",
                ApiRequestResponseDetail.ObjectType.A, "transTypesList",
                UserTransactionTypesResponse.class.getName(), list
        );
    }

    @Override
    public ApiRequestResponse findUserTransactionTypesById(Long id) {
        UserTransactionTypesResponse obj = this.transTypesMapper
                .toResponseDto(this.transSubInvAcService.findUserTransactionTypesById(id));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Successfully Fetched Types",
                ApiRequestResponseDetail.ObjectType.O, "TransType",
                UserTransactionTypesResponse.class.getName(), obj
        );
    }

    @Transactional
    @Override
    public ApiRequestResponse saveUserTransactionTypes(UserTransactionTypesRequest ut, String userName) throws Exception {
        UserAccessInvOrg userAccessInvOrg = this.usrAcsInvOrgService.findById(ut.getUserAccessInvOrgId());
        Integer userAccessTempId = userAccessInvOrg.getUserAccessTemltDtl().getUserAccessTemplt().getId();
        if (ut.getId() != null) {
            var db = this.transSubInvAcService.findUserTransactionTypesById(ut.getId());
            db.setName(ut.getName());
            db.setUserAccessInvOrg(userAccessInvOrg);
            db.setTrnsType(ut.getTrnsType());
            db.setTrnsTypeId(ut.getTrnsTypeId());
            db.setEndDate(ut.getEndDate());
            db.setStartDate(ut.getStartDate());
            db.setDescription(ut.getDescription());
            db.setIsMandatory(ut.getIsMandatory());
            this.transSubInvAcService.saveUserTransactionTypes(db, userName);
            this.eventPublisher.publishEvent(new UserAccessTemplateCacheSyncEvent(this, userAccessTempId));
            return ApiRequestResponseMaker.make(
                    HttpStatus.OK.name(), "Successfully",
                    null, null,
                    null, null
            );
        }
        //*****************************Create New ***************************************************************
        UserTransactionTypes userTransactionTypes = this.transTypesMapper.dtoToEntity(ut);
        userTransactionTypes.setUserAccessInvOrg(userAccessInvOrg);
        this.transSubInvAcService.saveUserTransactionTypes(userTransactionTypes, userName);
        this.eventPublisher.publishEvent(new UserAccessTemplateCacheSyncEvent(this, userAccessTempId));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Successfully",
                null, null,
                null, null
        );
    }

    @Transactional
    @Override
    public ApiRequestResponse DeleteUserTransactionTypes(Long utId) {
        UserTransactionTypes userTransactionTypes = this.transSubInvAcService.findUserTransactionTypesById(utId);
        Integer userAccessTempId = userTransactionTypes.getUserAccessInvOrg().getUserAccessTemltDtl().getUserAccessTemplt().getId();
        this.transSubInvAcService.DeleteUserTransactionTypes(utId);
        this.eventPublisher.publishEvent(new UserAccessTemplateCacheSyncEvent(this, userAccessTempId));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Successfully",
                null, null,
                null, null
        );
    }

    //===============UserSubInvAccess======
    @Override
    public ApiRequestResponse searchOrdTrnsTypesV(Long orgId, Long invOrgId, String type) {
        OrgHierarchy orgHierarchy = this.orgHierarchyService.findById(orgId);
        InventoryInformations inv = this.invInfoService.findOne(invOrgId);
        OrdTrnsTypesV ordTrnsTypesV = new OrdTrnsTypesV();
        ordTrnsTypesV.setOrgHierarchy(orgHierarchy);
        ordTrnsTypesV.setInvOrgs(inv);
        ordTrnsTypesV.setTypeName(type);
        List<OrdTrnsTypesVDto> ordTrnsTypesVs = this.transSubInvAcService.searchOrdTrnsTypesV(ordTrnsTypesV)
                .stream().map(this.ordTrnsTypesVMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Successfully Fetched Types",
                ApiRequestResponseDetail.ObjectType.A, "transTypesList",
                OrdTrnsTypesVDto.class.getName(), ordTrnsTypesVs
        );
    }

    @Override
    public ApiRequestResponse searchInvOrgSubInv(Long orgId, Long invOrgId, String subInvName) {
        return this.userTrnsAndSubinvAccessBService.searchInvOrgSubInv(orgId, invOrgId, subInvName);
    }

    @Override
    public ApiRequestResponse findByTransactionTypes(Long userTranTypeId) {
        return this.userTrnsAndSubinvAccessBService.findByTransactionTypes(userTranTypeId);
    }

    @Override
    public ApiRequestResponse saveUserSubInvAccess(UserSubInvAccessRequest us, String userName) {
        return this.userTrnsAndSubinvAccessBService.saveUserSubInvAccess(us, userName);
    }

    @Override
    public ApiRequestResponse findByUserTransTypeAccessByInvOrgAndTransType(Long invOrgId, Integer userId, TrnsType transType) {
        return this.userTrnsAndSubinvAccessBService.findByUserTransTypeAccessByInvOrgAndTransType(invOrgId, userId, transType);
    }


    @Override
    public ApiRequestResponse findUserSubInvAccessById(Long id) {
        return this.userTrnsAndSubinvAccessBService.findUserSubInvAccessById(id);
    }

}
