package com.aye.backendservice.service.userData;


import com.aye.entity.user.userData.MuserHierarchyV;

/**
 * Created by Munna on 5/22/2023.
 */
public interface MuserHierarchyVService {

    MuserHierarchyV findByFromUserId(Long fromUserId);

    MuserHierarchyV findByFromUserIdAndHierarchyCatId(Long fromUserId, Long hierarchyCatId);
}
