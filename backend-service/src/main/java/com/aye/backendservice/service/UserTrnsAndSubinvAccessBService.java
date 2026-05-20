package com.aye.backendservice.service;

import com.aye.RestfulServer.repo.InvOrgSubInvVRepo;
import com.aye.RestfulServer.repo.UserSubInvAccessRepo;
import com.aye.RestfulServer.repo.UserTransactionTypesRepo;
import com.aye.RestfulServer.service.MuserService;
import com.aye.RestfulServer.service.UserTrnsAndSubinvAccessService;
import com.aye.backendservice.applicationEvent.UserAccessTemplateCacheSyncEvent;
import com.aye.dtoLib.dto.request.UserSubInvAccessRequest;
import com.aye.dtoLib.dto.response.ApiRequestResponse;
import com.aye.dtoLib.dto.response.ApiRequestResponseDetail;
import com.aye.dtoLib.dto.response.userOrg.InvOrgSubInvVResponseDto;
import com.aye.dtoLib.dto.response.userOrg.UserSubInvAccessResponse;
import com.aye.dtoLib.dto.response.userOrg.UserTransactionTypesResponse;
import com.aye.entitylib.entity.CommonColumn;
import com.aye.entitylib.entity.InvOrgSubInvV;
import com.aye.entitylib.entity.UserSubInvAccess;
import com.aye.entitylib.entity.UserTransactionTypes;
import com.aye.enums.TrnsType;
import com.aye.mapper.userOrg.InvOrgSubInvVMapper;
import com.aye.mapper.userOrg.UserSubInvAccessMapper;
import com.aye.mapper.userOrg.UserTransactionTypesMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Miraj
 * @date: 28/01/2026
 * @time: 17:21
 */
@Service
public class UserTrnsAndSubinvAccessBService {
    @Autowired
    private UserTrnsAndSubinvAccessService userTrnsAndSubinvAccessService;
    @Autowired
    private MuserService muserService;
    @Autowired
    private InvOrgSubInvVRepo invOrgSubInvVRepo;
    @Autowired
    private UserSubInvAccessRepo userSubInvAccessRepo;
    @Autowired
    private InvOrgSubInvVMapper invOrgSubInvVMapper;
    @Autowired
    private UserSubInvAccessMapper userSubInvAccessMapper;
    @Autowired
    private UserTransactionTypesRepo userTransactionTypesRepo;
    @Autowired
    private UserTransactionTypesMapper userTransactionTypesMapper;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    ApiRequestResponse searchInvOrgSubInv(Long orgId, Long invOrgId, String subInvName) {
        List<InvOrgSubInvVResponseDto> invOrgSubInvVs = this.invOrgSubInvVRepo.findAll(new Specification<InvOrgSubInvV>() {
            @Override
            public Predicate toPredicate(Root<InvOrgSubInvV> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                predicates.add(cb.equal(root.get("orgHierarchy").get("id"), orgId));
                predicates.add(cb.equal(root.get("invOrgs").get("id"), invOrgId));
                if ((subInvName != null) && (subInvName != "")) {
                    predicates.add(cb.like(cb.upper(root.get("subInvName")), "%" + subInvName.toUpperCase() + "%"));

                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));

            }
        }).stream().map(this.invOrgSubInvVMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "invOrgSubInvRes",
                InvOrgSubInvVResponseDto.class.getName(),
                invOrgSubInvVs);


    }


    public ApiRequestResponse findByTransactionTypes(Long userTranTypeId) {
        var list = this.userSubInvAccessRepo.findByUserTransactionTypes_Id(userTranTypeId).stream()
                .map(this.userSubInvAccessMapper::toResponseDto).toList();
        return ApiRequestResponseMaker.make(HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "subInvAccessResponse",
                UserSubInvAccessResponse.class.getName(),
                list);
    }

    @Transactional
    public ApiRequestResponse saveUserSubInvAccess(UserSubInvAccessRequest us, String userName) {
        var user = this.muserService.findByUserName(userName);
        CommonColumn cc = new CommonColumn();
        UserTransactionTypes userTransactionTypes = userTransactionTypesRepo.findById(us.getUserTransactionTypeId()).orElseThrow(
                () -> new EntityNotFoundException("UserTransactionTypes Not Found with id" + us.getUserTransactionTypeId())
        );
        UserSubInvAccess userSubInvAccess;
        if (us.getId() != null) {
            userSubInvAccess = this.userSubInvAccessRepo.findById(us.getId()).orElseThrow(
                    () -> new EntityNotFoundException("Sub inv access not found with id" + us.getId())
            );
            cc = userSubInvAccess.getColumn();
            this.userSubInvAccessMapper.dtoToEntity(us, userSubInvAccess);
        } else {
            userSubInvAccess = this.userSubInvAccessMapper.dtoToEntity(us);
            userSubInvAccess.setUserTransactionTypes(userTransactionTypes);
        }


        if (us.getId() != null) {
            cc.setLastUpdateDate(new Date());
            cc.setLastUpdateBy(user.getId());
        } else {
            cc.setCreationDate(new Date());
            cc.setCreatedBy(user);
        }
        userSubInvAccess.setColumn(cc);
        Integer userAccessTempId = userTransactionTypes.getUserAccessInvOrg().getUserAccessTemltDtl().getUserAccessTemplt().getId();
        this.eventPublisher.publishEvent(new UserAccessTemplateCacheSyncEvent(this, userAccessTempId));
        this.userSubInvAccessRepo.save(userSubInvAccess);
        return ApiRequestResponseMaker.make(HttpStatus.OK.name(), "Success", null,
                null,
                null,
                null);

    }


    public ApiRequestResponse findUserSubInvAccessById(Long id) {
        UserSubInvAccess userSubInvAccess = this.userSubInvAccessRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("UserSubInvAccess Not Found with id" + id)
        );
        UserSubInvAccessResponse response = this.userSubInvAccessMapper.toResponseDto(userSubInvAccess);
        return ApiRequestResponseMaker.make(HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "subInvAccessResponse",
                UserSubInvAccessResponse.class.getName(),
                response);
    }


    public ApiRequestResponse findByUserTransTypeAccessByInvOrgAndTransType(Long invOrgId, Integer userId, TrnsType transType) {

        var list = this.userTrnsAndSubinvAccessService.findByUserTransTypeAccessByInvOrgAndTransType(invOrgId, userId, transType);
        return ApiRequestResponseMaker.make(HttpStatus.OK.name(), "Success",
                ApiRequestResponseDetail.ObjectType.A, "transTypeRes",
                UserTransactionTypesResponse.class.getName(),
                this.userTransactionTypesMapper.toResponseDto(list));
    }

}


