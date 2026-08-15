package com.aye.backendservice.repository.orgManagement;


import com.aye.entity.InvOrgSubInvV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by toufiq on 11/6/2021.
 */
public interface InvOrgSubInvVRepo extends JpaRepository<InvOrgSubInvV, Long>, JpaSpecificationExecutor<InvOrgSubInvV> {
}
