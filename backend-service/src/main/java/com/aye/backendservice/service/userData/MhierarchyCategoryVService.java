package com.aye.backendservice.service.userData;


import com.aye.entity.user.userData.MhierarchyCategoryV;

import java.util.List;

/**
 * Created by Munna on 5/22/2023.
 */
public interface MhierarchyCategoryVService {

    List<MhierarchyCategoryV> findByOrgSetIdAndHeaderId(Integer orgSetId, Integer headerId);
}
