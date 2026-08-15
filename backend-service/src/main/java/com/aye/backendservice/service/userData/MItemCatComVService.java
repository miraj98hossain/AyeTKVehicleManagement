package com.aye.backendservice.service.userData;


import com.aye.entity.MItemCatComV;

import java.util.List;

public interface MItemCatComVService {

    List<MItemCatComV> getAllItemCatComb();

    List<MItemCatComV> filterItemCatComb(Long orgId, String searchWords);

}
