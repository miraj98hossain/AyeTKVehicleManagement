package com.aye.backendservice.service.userData;


import com.aye.entity.user.userData.MuserBank;
import com.aye.enums.PaymentType;

import java.util.List;

public interface MuserBankService {
    List<MuserBank> findAll();

    List<MuserBank> findbyOrg(Long orgId);

    List<MuserBank> findbyOrgAndRefType(Long orgId, PaymentType refType);

    //public boolean isBankExists(Long orgId,Integer bankId);
    boolean isBankExists(Long orgId, Integer bankId, PaymentType refType);

    List<MuserBank> findByBankName(Long orgId, String accountName);
}
