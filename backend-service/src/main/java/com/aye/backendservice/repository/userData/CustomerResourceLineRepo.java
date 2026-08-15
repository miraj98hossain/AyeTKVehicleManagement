package com.aye.backendservice.repository.userData;


import com.aye.entity.user.CustomerResourceHeader;
import com.aye.entity.user.CustomerResourceLine;
import com.aye.enums.RegularData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by toufiq on 6/27/2021.
 */
public interface CustomerResourceLineRepo extends JpaRepository<CustomerResourceLine, Integer>, JpaSpecificationExecutor<CustomerResourceLine> {

    //    CustomerResourceLine findByCustomerResourceHeaderAndId(CustomerResourceHeader customerResourceHeader, Integer id);
    List<CustomerResourceLine> findByCustomerResourceHeader(CustomerResourceHeader customerResourceHeader);

    List<CustomerResourceLine> findByCustomerResourceHeaderAndResourceStatus(CustomerResourceHeader customerResourceHeader, RegularData.ResourceStatus resourceStatus);
}
