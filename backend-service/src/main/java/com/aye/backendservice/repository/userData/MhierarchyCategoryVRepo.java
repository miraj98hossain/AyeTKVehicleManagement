package com.aye.backendservice.repository.userData;


import com.aye.entity.user.userData.MhierarchyCategoryV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Munna on 5/22/2023.
 */
public interface MhierarchyCategoryVRepo extends JpaRepository<MhierarchyCategoryV, Integer> {

    List<MhierarchyCategoryV> findByOrgSetIdAndHeaderId(Integer orgSetId, Integer headerId);

}
