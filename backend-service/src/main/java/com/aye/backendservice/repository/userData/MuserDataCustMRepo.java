package com.aye.backendservice.repository.userData;


import com.aye.entity.user.userData.MuserDataCustM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//import com.aye.RestfulServer.model.userData.MuserDataCust;

public interface MuserDataCustMRepo extends JpaRepository<MuserDataCustM, Integer> {

    @Query("select cust from MuserDataCustM cust where cust.autoNumber=:autoNumber ")
    List<MuserDataCustM> getByAutonumber(@Param("autoNumber") String autoNumber);

    @Query("select cust from MuserDataCustM cust where cust.autoNumber=:autoNumber and cust.customerName like :CustName||'%'")
    List<MuserDataCustM> findByAutonumberName(@Param("autoNumber") String autoNumber, @Param("CustName") String CustName);

    List<MuserDataCustM> findByAutoNumberAndCustomerNameContaining(String autoNumber, String CustName);
//	List<MuserDataCustM> findByAutoNumberAndOrgIdAndCustomerNameContaining(String autoNumber, Long orgId, String CustName);

    @Query(
            "select cust from MuserDataCustM cust " +
                    "where cust.autoNumber = :autoNumber " +
                    "and cust.orgId = :OrgId " +
                    "and ( " +
                    "     cust.customerName like CONCAT('%', :CustName, '%') " +
                    "  or cust.accountNumber like CONCAT('%', :CustName, '%') " +
                    "    )"
    )
    List<MuserDataCustM> findByAutoNumberOrgIdCustomerName(@Param("autoNumber") String autoNumber, @Param("OrgId") Long OrgId, @Param("CustName") String CustName);


//	@Query(value="select x.*,y.* from M_user_custH x,M_user_custD y where x.party_id=y.party_id and "
//			+ "x.auronumber=y.autonumber and x.autonumber= :autoNumber",nativeQuery = true)
//	List<MuserDataCustM> findByCustomerEmbdAutoNumber(@Param("autoNumber")String autoNumber);

    //findByCustomerEmbdAutoNumber(String AutoNumber);
//	@Query("select count(*) from MuserDataCustM cust where cust.AutoNUmber=:autoNumber and  cust.CustAcctId=:custAccountId")
//	Integer getCustExist(@Param("custAccountId")Integer custAccountId,@Param("autoNumber")String autoNumber);
//	
//	@Query("select cust.ShipToId  from MuserDataCust cust where cust.AutoNUmber=:autoNumber and  cust.CustAcctId=:custAccountId")
//	Integer getShipto(@Param("custAccountId")Integer custAccountId,@Param("autoNumber")String autoNumber);
//
//	@Query("select cust.BillToId  from MuserDataCust cust where cust.AutoNUmber=:autoNumber and  cust.CustAcctId=:custAccountId")
//	Integer getBillto(@Param("custAccountId")Integer custAccountId,@Param("autoNumber")String autoNumber);
}
