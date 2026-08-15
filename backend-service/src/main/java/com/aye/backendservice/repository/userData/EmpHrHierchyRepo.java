package com.aye.backendservice.repository.userData;


import com.aye.entity.user.EmpHrHierchy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by toufiq on 5/23/2021.
 */
public interface EmpHrHierchyRepo extends JpaRepository<EmpHrHierchy, Long>, JpaSpecificationExecutor<EmpHrHierchy> {
    List<EmpHrHierchy> findByParentId(Integer parentId);
}
