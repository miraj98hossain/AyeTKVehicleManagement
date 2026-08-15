package com.aye.backendservice.repository.userData;


import com.aye.entity.user.EmpCusHierc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Toufiq on 10/7/2019.
 */
public interface EmpCusHiercRepo extends JpaRepository<EmpCusHierc, Integer>, JpaSpecificationExecutor<EmpCusHierc> {
    List<EmpCusHierc> findByParent(Integer p);

    List<EmpCusHierc> findByValueId(Integer p);

//

    @Query("select distinct x.hierarchyCategory from EmpCusHierc x where x.valueId=:valueId and x.value<>'CUST'")
    Integer findDistinctHierarchyCategoryByValueId(@Param("valueId") Integer p);

    @Query("select distinct x.hierarchyCategory from EmpCusHierc x where x.valueId=:valueId and x.value<>'CUST' and x.OrgAccess=:OrgAccess")
    Integer findDistinctHierarchyCategoryByValueIdAndOrgId(@Param("valueId") Integer p, @Param("OrgAccess") Integer OrgAccess);


//    List<OrderHeader.orderHeaderInf1> findByOrgIdAndOrderNumber(@Param("orgId")Long orgId,@Param("orderNumber")String orderNumber);

}
