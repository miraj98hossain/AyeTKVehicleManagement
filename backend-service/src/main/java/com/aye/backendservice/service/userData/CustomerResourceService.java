package com.aye.backendservice.service.userData;


import com.aye.entity.user.CustomerResourceHeader;
import com.aye.entity.user.CustomerResourceLine;
import com.aye.entity.user.EmpResource;

import java.util.List;

/**
 * Created by toufiq on 6/27/2021.
 */
public interface CustomerResourceService {

    List<CustomerResourceHeader> findResourceHeaderByOrgId(Long orgId);

    CustomerResourceHeader findResourceHeaderById(Integer id);

    CustomerResourceHeader findByOrgIdAndCustAccountId(Long orgId, Integer cstAcctId) throws Exception;

    void saveResourceHeader(CustomerResourceHeader resourceHeader);


    List<CustomerResourceLine> findByCustomerResourceHeader(CustomerResourceHeader customerResourceHeader);

    List<CustomerResourceLine> findActiveByCustomerResourceHeader(CustomerResourceHeader customerResourceHeader);

    CustomerResourceLine findResourceLineById(Integer lineId);

    //    CustomerResourceLine findResourceLineByHeaderAndLineId(CustomerResourceHeader header,Integer lineId);
    void saveResourceLine(CustomerResourceLine resourceLine);

    List<EmpResource> findEmpResourceByOrgId(Long orgId);

    EmpResource findEmpResourceById(Integer id);

    void saveEmpResource(EmpResource empResource);

    EmpResource findLastRecordByOrg(Long orgId);

    List<CustomerResourceHeader> searchCustomerResourceHeader(CustomerResourceHeader s);

    List<CustomerResourceLine> searchCustomerResourceLine(CustomerResourceLine s);


}
