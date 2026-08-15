package com.aye.backendservice.repository.userData;


import com.aye.entity.user.userData.MuserHierarchyWoutCustV;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Munna on 5/22/2023.
 */
public interface MuserHierarchyWoutCustVRepo extends JpaRepository<MuserHierarchyWoutCustV, Integer> {

    MuserHierarchyWoutCustV findByFromUserId(Long fromUserId);

    MuserHierarchyWoutCustV findByFromUserIdAndHierarchyCatId(Long fromUserId, Long hierarchyCatId);

}
