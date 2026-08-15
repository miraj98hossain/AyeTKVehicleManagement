package com.aye.backendservice.service.orgManagement;


import com.aye.entity.UserCodeAccess;

import java.util.List;


public interface UserCodeAccessService {

    UserCodeAccess save(UserCodeAccess userCodeAccess);

    UserCodeAccess findById(Long userCodeAccessId);

    List<UserCodeAccess> findAllByUser(Integer userId);

    List<UserCodeAccess> findAllUserItemAccess(String userName);

    List<UserCodeAccess> findAllByUser(String userName);

    void deleteById(Long userCodeAccessId);
}
