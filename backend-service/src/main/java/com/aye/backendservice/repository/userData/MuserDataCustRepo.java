package com.aye.backendservice.repository.userData;


import com.aye.entity.user.userData.MuserDataCust;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MuserDataCustRepo extends JpaRepository<MuserDataCust, Integer>, JpaSpecificationExecutor<MuserDataCust> {

    @Query("select cust from MuserDataCust cust where cust.autoNumber=:autoNumber ")
    List<MuserDataCust> getByAutonumber(@Param("autoNumber") String autoNumber);

    List<MuserDataCust> findByOrgId(Long orgId);


    @Query("select count(*) from MuserDataCust cust where cust.autoNumber=:autoNumber and  cust.custAcctId=:custAccountId and cust.orgId=:orgId")
    Integer getCustExist(@Param("custAccountId") Integer custAccountId, @Param("autoNumber") String autoNumber, @Param("orgId") Long orgId);

    @Query("select count(*) from MuserDataCust cust where cust.autoNumber=:autoNumber and  cust.custAcctId=:custAccountId and cust.orgId=:orgId and cust.shipToId=:shipToId")
    Integer getCustExist(@Param("custAccountId") Integer custAccountId, @Param("autoNumber") String autoNumber, @Param("orgId") Long orgId, @Param("shipToId") Integer shipToId);


    @Query("select cust.shipToId  from MuserDataCust cust where cust.autoNumber=:autoNumber and  cust.custAcctId=:custAccountId and cust.orgId=:orgId")
    Integer getShipto(@Param("custAccountId") Integer custAccountId, @Param("autoNumber") String autoNumber, @Param("orgId") Long orgId);

    @Query("select cust.billToId  from MuserDataCust cust where cust.autoNumber=:autoNumber and  cust.custAcctId=:custAccountId and cust.orgId=:orgId")
    Integer getBillto(@Param("custAccountId") Integer custAccountId, @Param("autoNumber") String autoNumber, @Param("orgId") Long orgId);
}
