package com.aye.backendservice.repository.orgManagement;


import com.aye.entity.UserAccessTemplt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAccessTempltRepo extends JpaRepository<UserAccessTemplt, Integer> {
    List<UserAccessTemplt> findByIdOrderById(Integer id);

    UserAccessTemplt findByTempDesc(String templtnumber);

    List<UserAccessTemplt> findByTempNumber(String tempNumber);


}
