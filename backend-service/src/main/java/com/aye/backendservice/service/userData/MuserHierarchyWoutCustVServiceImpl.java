package com.aye.backendservice.service.userData;


import com.aye.backendservice.repository.userData.MuserHierarchyWoutCustVRepo;
import com.aye.entity.user.userData.MuserHierarchyWoutCustV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Munna on 5/22/2023.
 */

@Service
public class MuserHierarchyWoutCustVServiceImpl implements MuserHierarchyWoutCustVService {

    @Autowired
    private MuserHierarchyWoutCustVRepo muserHierarchyWoutCustVRepo;


    @Override
    public MuserHierarchyWoutCustV findByFromUserId(Long fromUserId) {
        return this.muserHierarchyWoutCustVRepo.findByFromUserId(fromUserId);
    }

    @Override
    public MuserHierarchyWoutCustV findByFromUserIdAndHierarchyCatId(Long fromUserId, Long hierarchyCatId) {
        return this.muserHierarchyWoutCustVRepo.findByFromUserIdAndHierarchyCatId(fromUserId, hierarchyCatId);
    }
}
