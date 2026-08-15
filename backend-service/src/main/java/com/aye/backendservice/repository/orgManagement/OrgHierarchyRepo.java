package com.aye.backendservice.repository.orgManagement;


import com.aye.entity.OrgHierarchy;
import com.aye.enums.OrgType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Toufiq on 2/1/2019.
 */
public interface OrgHierarchyRepo extends JpaRepository<OrgHierarchy, Long> {
    //    @Lock(LockModeType.PESSIMISTIC_WRITE)
//    OrgHierarchy findById(Long id);
    List<OrgHierarchy> findByTypeOrderById(OrgType OrgType);

    List<OrgHierarchy> findByOrgHierarchy(OrgHierarchy hirearchy);


//    @Query(value = "SELECT x.*, NVL2 (y.Org_id, 1, 0) flag\n" +
//            "  FROM ORGANIZATION_HIERARCHY x, (select y.Org_id from INV_ITEM_M y where ITEM_ID=:item_Id) y\n" +
//            " WHERE x.ID = y.ORG_ID(+) AND x.ID =  :parentId",nativeQuery = true)
//    List<ItemOrg> findOrgwithItem(@Param("parentId") Long parentId,@Param("item_Id") Long item_Id);


//    @Query("SELECT new com.aye.web.model.Inv.common.ItemOrg( d.id,d.type,d.name,d.code,d.address,e.itemId)"
//            + " FROM OrgHierarchy d LEFT JOIN d.masterItems e where d.id=:parentId")
//    List<ItemOrg> fetchItemOrgLeftJoin(@Param("parentId") Long parentId);


}
