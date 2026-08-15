package com.aye.backendservice.service.userData;


import com.aye.entity.user.Muser;
import com.aye.entity.user.UserDevice;

import java.util.Map;

/**
 * Created by toufiq on 6/5/2021.
 */
public interface UserDeviceService {

    UserDevice findActiveUserDevice(Muser u);

    UserDevice findActiveUserDeviceByDeviceId(String dId);

    void save(UserDevice ud);

    void delete(UserDevice ud);

    //    void regisstryUserDevice(Map<String,String> headers,Muser u);
    String getUserAppVerion(Map<String, String> headers) throws Exception;

    void processUserDeviceId(Map<String, String> headers, Muser u) throws Exception;
}
