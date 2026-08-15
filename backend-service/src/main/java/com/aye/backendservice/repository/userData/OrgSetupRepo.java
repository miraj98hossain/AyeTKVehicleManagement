package com.aye.backendservice.repository.userData;


import com.aye.entity.OrgSetup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Toufiq on 8/18/2019.
 */
public interface OrgSetupRepo extends JpaRepository<OrgSetup, Integer> {
    OrgSetup findByOrgId(Long orgId);
}
