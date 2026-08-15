package com.aye.backendservice.service.orgManagement;


import com.aye.entity.AppModule;
import com.aye.entity.UserAccess;
import com.aye.entity.UserAccessTemltDtl;
import com.aye.entity.UserAccessTemplt;
import com.aye.entity.user.Muser;
import com.aye.enums.RoleTypes;

import java.util.List;

/**
 * Created by Administrator on 2/17/2019.
 */
public interface UserAccessService {
    List<UserAccess> getAllUserAccess();

    List<UserAccess> findByUserId(Muser user);

    List<UserAccess> findByUserId(Integer userId);

    void saveDtlLine(UserAccess userAccess);

    UserAccess findById(Integer id);


    UserAccess findByUserAccessTempletAndUser(UserAccessTemplt userAccessTemplt, Muser user);

    List<UserAccessTemltDtl> findUserAccessTempDtlByMenuId(Integer menuId);

    List<UserAccess> findActiveAccessByUsAndAppModule(Muser lUser, AppModule am);

    List<UserAccessTemltDtl> getUserAccess(Muser user, RoleTypes roleTypes);

    List<UserAccessTemltDtl> getUserAccess(Muser user);

//    List<UserAccessTemltDtl> getUserAccess(String user);

}


