package com.aye.backendservice.repository.userData;


import com.aye.entity.user.userData.MuserHierarchyV;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Munna on 5/22/2023.
 */
public interface MuserHierarchyVRepo extends JpaRepository<MuserHierarchyV, Integer> {

    MuserHierarchyV findByFromUserId(Long fromUserId);

    MuserHierarchyV findByFromUserIdAndHierarchyCatId(Long fromUserId, Long hierarchyCatId);

}
