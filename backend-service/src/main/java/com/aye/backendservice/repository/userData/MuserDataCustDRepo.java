package com.aye.backendservice.repository.userData;

//import java.util.List;


import com.aye.entity.user.userData.MuserDataCustD;
import org.springframework.data.jpa.repository.JpaRepository;

//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import com.aye.RestfulServer.model.userData.MuserDataCust;
//import com.aye.RestfulServer.model.userData.MuserDataCustM;

public interface MuserDataCustDRepo extends JpaRepository<MuserDataCustD, Integer> {

//	@Query("select cust from MuserDataCustM cust where cust.AutoNUmber=:autoNumber ")
//	List<MuserDataCustM> getByAutonumber(@Param("autoNumber")String autoNumber);

//	@Query("select count(*) from MuserDataCustM cust where cust.AutoNUmber=:autoNumber and  cust.CustAcctId=:custAccountId")
//	Integer getCustExist(@Param("custAccountId")Integer custAccountId,@Param("autoNumber")String autoNumber);
//	
//	@Query("select cust.ShipToId  from MuserDataCust cust where cust.AutoNUmber=:autoNumber and  cust.CustAcctId=:custAccountId")
//	Integer getShipto(@Param("custAccountId")Integer custAccountId,@Param("autoNumber")String autoNumber);
//
//	@Query("select cust.BillToId  from MuserDataCust cust where cust.AutoNUmber=:autoNumber and  cust.CustAcctId=:custAccountId")
//	Integer getBillto(@Param("custAccountId")Integer custAccountId,@Param("autoNumber")String autoNumber);
}
