package com.aye.backendservice.service.userData;


import com.aye.backendservice.repository.userData.MhierarchyCategoryVRepo;
import com.aye.entity.user.userData.MhierarchyCategoryV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Munna on 5/22/2023.
 */
@Service
public class MhierarchyCategoryVServiceImpl implements MhierarchyCategoryVService {

    @Autowired
    private MhierarchyCategoryVRepo mhierarchyCategoryVRepo;

    @Override
    public List<MhierarchyCategoryV> findByOrgSetIdAndHeaderId(Integer orgSetId, Integer headerId) {
        return this.mhierarchyCategoryVRepo.findByOrgSetIdAndHeaderId(orgSetId, headerId);
    }


}
