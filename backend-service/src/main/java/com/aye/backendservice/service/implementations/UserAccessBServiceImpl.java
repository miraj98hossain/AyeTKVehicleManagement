package com.aye.backendservice.service.implementations;

import com.aye.RestfulServer.model.*;
import com.aye.RestfulServer.model.common.TrnsType;
import com.aye.RestfulServer.model.om.InvOrgSubInvV;
import com.aye.RestfulServer.model.om.InventoryInformations;
import com.aye.RestfulServer.model.om.OrdTrnsTypesV;
import com.aye.RestfulServer.model.om.OrgHierarchy;
import com.aye.RestfulServer.service.*;
import com.aye.backendservice.mapper.*;
import com.aye.backendservice.service.ApiRequestResponseMaker;
import com.aye.backendservice.service.UserAccessBService;
import com.aye.commonlib.dto.request.*;
import com.aye.commonlib.dto.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserAccessBServiceImpl implements UserAccessBService {
    @Autowired
    OrgHierarchyService orgHierarchyService;
    @Autowired
    InvInformationsService invInfoService;
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
    private CommonColumnServiceImpl commonColumnService;
    @Autowired
    private UserSubInvAccessMapper subInvMapper;
    @Autowired
    private UserAccessInvOrgMapper userAccessInvOrgMapper;
    @Autowired
    private UserAccessService userAccessService;


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
    public ApiRequestResponse findUserAccessById(Integer id) {
        UserAccessResponse userAccessesRes = this.userAccessMapper.toResponseDto(this.userAccessService.findById(id));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Successfully Fetched  UserAccess",
                ApiRequestResponseDetail.ObjectType.O, "userAccess",
                UserAccessResponse.class.getName(), userAccessesRes
        );
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

    @Override
    public ApiRequestResponse saveDtlLine(UserAccessRequest userAccessRequest) {
        UserAccess userAccess = this.userAccessMapper.dtoToUserAccess(userAccessRequest);
        Muser muser = this.mUserService.findById(userAccessRequest.getUserId());
        UserAccessTemplt userAccessTemplt = this.usrAcsTempService.findById(userAccessRequest.getUserAccessTempltId());
        userAccess.setUser(muser);
        userAccess.setUserAccessTemplt(userAccessTemplt);
        this.userAccessService.saveDtlLine(userAccess);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Successfully Created UserAccess",
                null, "",
                null, null
        );
    }

    @Override
    public ApiRequestResponse saveUserAccessTemp(UserAccessTempltRequest userAccessTempRequest) throws Exception {
        UserAccessTemplt userAccessTemplt = this.tempMapper.dtoToEntity(userAccessTempRequest);
        this.usrAcsTempService.save(userAccessTemplt);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Success",
                null, "",
                null, null
        );
    }

    @Override
    public ApiRequestResponse saveUserAccessTempDtl(UserAccessTemltDtlRequest usrAcsTempDtlReq) throws Exception {
        UserAccessTemplt userAccessTemplt = this.usrAcsTempService.findById(usrAcsTempDtlReq.getUserAccessTempltId());
        UserAccessTemltDtl userAccessTemltDtl = this.tempDtlMapper.dtoToEntity(usrAcsTempDtlReq);
        userAccessTemltDtl.setUserAccessTemplt(userAccessTemplt);
        userAccessTemltDtl.setRequestGroupHeader(null);
        this.usrAcsTempService.saveDtlLine(userAccessTemltDtl);
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
    public ApiRequestResponse findUsrAccessInvOrgById(Long id) {
        UserAccessInvOrgResponse invOrg = this.userAccessInvOrgMapper
                .toResponseDto(this.usrAcsInvOrgService.findById(id));
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Successfully Fetched  Inv Orgs",
                ApiRequestResponseDetail.ObjectType.O, "userAccInvOrg",
                UserAccessInvOrgResponse.class.getName(), invOrg
        );
    }

    @Override
    public ApiRequestResponse saveUsrAccessInvOrg(UserAccessInvOrgRequest usrAccInvOrgReq, String userName) {
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

    @Override
    public ApiRequestResponse deleteUsrAccessInvOrg(Long id) {
        this.usrAcsInvOrgService.delete(id);
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

    @Override
    public ApiRequestResponse saveUserTransactionTypes(UserTransactionTypesRequest ut, String userName) {
        UserAccessInvOrg userAccessInvOrg = this.usrAcsInvOrgService.findById(ut.getUserAccessInvOrgId());
        if (ut.getId() != null) {
            var db = this.transSubInvAcService.findUserTransactionTypesById(ut.getId());
            db.setName(ut.getName());
            db.setUserAccessInvOrg(userAccessInvOrg);
            db.setTrnsType(TrnsType.valueOf(ut.getTrnsType()));
            db.setTrnsTypeId(ut.getTrnsTypeId());
            db.setEndDate(ut.getEndDate());
            db.setStartDate(ut.getStartDate());
            db.setDescription(ut.getDescription());
            this.transSubInvAcService.saveUserTransactionTypes(db, userName);
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
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Successfully",
                null, null,
                null, null
        );
    }

    @Override
    public ApiRequestResponse DeleteUserTransactionTypes(Long utId) {
        this.transSubInvAcService.DeleteUserTransactionTypes(utId);
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Successfully",
                null, null,
                null, null
        );
    }

    //===============UserSubInvAccess======
    @Override
    public ApiRequestResponse findByTransactionTypes(UserTransactionTypes userTransactionTypes) {
        return null;
    }

    @Override
    public ApiRequestResponse findUserSubInvAccessById(Long id) {
        return null;
    }

    @Override
    public ApiRequestResponse saveUserSubInvAccess(UserSubInvAccess us, String userName) {
        return null;
    }

    @Override
    public ApiRequestResponse deleteUserSubInvAccess(UserSubInvAccess us) {
        return null;
    }

    @Override
    public ApiRequestResponse searchOrdTrnsTypesV(Long orgId, Long invOrgId, String type) {
        OrgHierarchy orgHierarchy = this.orgHierarchyService.findById(orgId);
        InventoryInformations inv = this.invInfoService.findOne(invOrgId);
        OrdTrnsTypesV ordTrnsTypesV = new OrdTrnsTypesV();
        ordTrnsTypesV.setOrgHierarchy(orgHierarchy);
        ordTrnsTypesV.setInvOrgs(inv);
        ordTrnsTypesV.setTypeName(type);
        List<OrdTrnsTypesVResponse> ordTrnsTypesVs = this.transSubInvAcService.searchOrdTrnsTypesV(ordTrnsTypesV)
                .stream().map(this.ordTrnsTypesVMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(
                HttpStatus.OK.name(), "Successfully Fetched Types",
                ApiRequestResponseDetail.ObjectType.A, "transTypesList",
                OrdTrnsTypesVResponse.class.getName(), ordTrnsTypesVs
        );
    }

    @Override
    public ApiRequestResponse searchInvOrgSubInv(InvOrgSubInvV v) {
        return null;
    }

}
