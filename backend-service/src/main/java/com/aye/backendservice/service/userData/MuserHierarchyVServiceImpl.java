package com.aye.backendservice.service.userData;


import com.aye.backendservice.repository.userData.MuserHierarchyVRepo;
import com.aye.entity.user.userData.MuserHierarchyV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Munna on 5/22/2023.
 */
@Service
public class MuserHierarchyVServiceImpl implements MuserHierarchyVService {

    @Autowired
    private MuserHierarchyVRepo muserHierarchyVRepo;


    @Override
    public MuserHierarchyV findByFromUserId(Long fromUserId) {
        return this.muserHierarchyVRepo.findByFromUserId(fromUserId);
    }

    @Override
    public MuserHierarchyV findByFromUserIdAndHierarchyCatId(Long fromUserId, Long hierarchyCatId) {
        return this.muserHierarchyVRepo.findByFromUserIdAndHierarchyCatId(fromUserId, hierarchyCatId);
    }
}
