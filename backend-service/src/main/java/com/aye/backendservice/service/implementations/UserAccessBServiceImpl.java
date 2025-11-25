package com.aye.backendservice.service.implementations;

import com.aye.RestfulServer.model.*;
import com.aye.RestfulServer.model.om.OrgHierarchy;
import com.aye.RestfulServer.service.*;
import com.aye.backendservice.mapper.UserAccessMapper;
import com.aye.backendservice.mapper.UserAccessTemltDtlMapper;
import com.aye.backendservice.mapper.UserAccessTempltMapper;
import com.aye.backendservice.service.ApiRequestResponseMaker;
import com.aye.backendservice.service.UserAccessBService;
import com.aye.commonlib.dto.request.UserAccessRequest;
import com.aye.commonlib.dto.request.UserAccessTemltDtlRequest;
import com.aye.commonlib.dto.request.UserAccessTempltRequest;
import com.aye.commonlib.dto.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccessBServiceImpl implements UserAccessBService {
    @Autowired
    private UserAccessTempltService usrAcsTempService;
    @Autowired
    private MuserService mUserService;
    @Autowired
    private UserAccessTempltMapper tempMapper;
    @Autowired
    private UserAccessTemltDtlMapper tempDtlMapper;
    @Autowired
    private UserAccessMapper userAccessMapper;
    @Autowired
    private UserAccessService userAccessService;
    @Autowired
    private OrgHierarchyService orgHierarService;
    @Autowired
    private UserMenuService menuService;
    @Autowired
    private RequestGroupHeaderService reqGrpHdrService;

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
        OrgHierarchy orgHierarchy = this.orgHierarService.findById(usrAcsTempDtlReq.getOrgHierarchyId());
        UserMenu userMenu = this.menuService.findByMenuId(usrAcsTempDtlReq.getUserMenuId());
        RequestGroupHeader requestGroupHeader = this.reqGrpHdrService.findById(usrAcsTempDtlReq.getRequestGroupHeaderId());
        UserAccessTemltDtl userAccessTemltDtl = this.tempDtlMapper.dtoToEntity(usrAcsTempDtlReq);
        userAccessTemltDtl.setUserAccessTemplt(userAccessTemplt);
        userAccessTemltDtl.setUserMenu(userMenu);
        userAccessTemltDtl.setOrgHierarchy(orgHierarchy);
        userAccessTemltDtl.setRequestGroupHeader(requestGroupHeader);
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

}
