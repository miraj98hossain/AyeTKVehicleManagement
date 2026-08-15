package com.aye.backendservice.service.userData;


import com.aye.entity.user.userData.MuserHierarchyWoutCustV;

/**
 * Created by Munna on 5/22/2023.
 */
public interface MuserHierarchyWoutCustVService {

    MuserHierarchyWoutCustV findByFromUserId(Long fromUserId);

    MuserHierarchyWoutCustV findByFromUserIdAndHierarchyCatId(Long fromUserId, Long hierarchyCatId);
}
