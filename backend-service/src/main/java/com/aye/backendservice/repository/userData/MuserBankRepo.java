package com.aye.backendservice.repository.userData;


import com.aye.entity.user.userData.MuserBank;
import com.aye.enums.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MuserBankRepo extends JpaRepository<MuserBank, Integer> {

    //	@Query("SELECT bank FROM MuserBank bank WHERE bank.orgId = :orgId ")
//	List<MuserBank> findByOrgId(@Param("orgId")Long orgId);
    List<MuserBank> findByOrgId(Long orgId);

    List<MuserBank> findByOrgIdAndRefType(Long orgId, PaymentType refType);

    @Query("SELECT bank FROM MuserBank bank WHERE bank.orgId = :orgId and bank.bankId = :bankId")
    MuserBank isBankExists(@Param("orgId") Long orgId, @Param("bankId") Integer bankId);

    MuserBank findByOrgIdAndBankIdAndRefType(Long orgId, Integer bankId, PaymentType refType);


    @Query("SELECT bank FROM MuserBank bank WHERE bank.orgId = :orgId and bank.bankAccountName like '%'||:accountName||'%'")
    List<MuserBank> findByBankName(@Param("orgId") Long orgId, @Param("accountName") String accountName);
}
