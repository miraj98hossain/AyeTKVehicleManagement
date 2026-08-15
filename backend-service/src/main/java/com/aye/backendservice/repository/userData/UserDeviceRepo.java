package com.aye.backendservice.repository.userData;


import com.aye.entity.user.Muser;
import com.aye.entity.user.UserDevice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by toufiq on 6/5/2021.
 */
public interface UserDeviceRepo extends JpaRepository<UserDevice, Long> {

    UserDevice findByMuserAndIsActive(Muser u, Boolean ia);

    UserDevice findByDeviceIdAndIsActive(String u, Boolean ia);
}
