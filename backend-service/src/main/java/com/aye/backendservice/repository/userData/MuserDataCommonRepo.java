package com.aye.backendservice.repository.userData;

//import java.util.List;

//import org.springframework.data.jpa.repository.JpaRepository;


import com.aye.entity.user.userData.MuserDataCommon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface MuserDataCommonRepo extends JpaRepository<MuserDataCommon, Integer> {

    @Query("select common.transactionTypeId from MuserDataCommon common where common.autoNumber=:AutoNumber")
    Integer findByAutoNumber(@Param("AutoNumber") String AutoNumber);
//	@Param("invItemId")Integer invItemId,@Param("Uom")String Uom,@Param("invOrgId")Integer invOrgId);
    //Multiple Order Type Cannot be assign to single User

    @Procedure(procedureName = "M_COMMON.M_USERDATACOMMON", outputParameterName = "p_out_msg")
    String mUserDataCommon(String p_auto_number, Integer p_org_id);
}
