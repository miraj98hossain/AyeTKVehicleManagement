package com.aye.backendservice.repository.userData;


import com.aye.entity.user.EmpResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by toufiq on 6/27/2021.
 */
public interface EmpResourceRepo extends JpaRepository<EmpResource, Integer> {
    List<EmpResource> findByOrgIdOrderByIdDesc(Long orgId);

    List<EmpResource> findDistinctEmpResourceRepoByOrgIdOrderByIdDesc(Long orgId);

}
