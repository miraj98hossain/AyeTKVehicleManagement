package com.aye.backendservice.repository.orgManagement;


import com.aye.entity.UserCodeAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserCodeAccessRepo extends JpaRepository<UserCodeAccess, Long>, JpaSpecificationExecutor<UserCodeAccess> {

    List<UserCodeAccess> findAllByUser_Id(Integer userId);

    List<UserCodeAccess> findAllByUser_UserName(String userUserName);
}
