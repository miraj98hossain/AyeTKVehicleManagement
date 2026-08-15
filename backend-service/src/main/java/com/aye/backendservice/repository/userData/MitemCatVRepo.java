package com.aye.backendservice.repository.userData;


import com.aye.entity.MitemCatV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MitemCatVRepo extends JpaRepository<MitemCatV, Integer>, JpaSpecificationExecutor<MitemCatV> {

    List<MitemCatV> findAllByOrgId(Long orgId);

    List<MitemCatV> findByOrgIdAndInvOrgIdAndClassNameContainingAndItemClassInOrderByItemClass(Long OrgId, Long invOrgId, String className, List<String> categoryvals);


}
