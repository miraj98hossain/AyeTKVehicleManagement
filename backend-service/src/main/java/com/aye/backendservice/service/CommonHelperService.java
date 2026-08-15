package com.aye.backendservice.service;


import com.aye.backendservice.service.orgManagement.UserCodeAccessService;
import com.aye.entity.UserCodeAccess;
import com.aye.entity.order.MItemCatVNew;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Miraj
 * @date: 27/07/2026
 * @time: 11:17 am
 */
@Service
@RequiredArgsConstructor
public class CommonHelperService {
    private final UserCodeAccessService userCodeAccessService;
    private final MItemCatVService mItemCatVService;


    public List<Long> getUserAccessedItems(String userName) {
        List<String> itemAccessList = userCodeAccessService.findAllUserItemAccess(userName).stream()
                .map(UserCodeAccess::getItemCatComb).toList();
        List<Long> itemCatVList = mItemCatVService.findAllByItemCombinations(itemAccessList)
                .stream()
                .map(MItemCatVNew::getInventoryItemId)
                .toList();
        return itemCatVList;
    }
}
