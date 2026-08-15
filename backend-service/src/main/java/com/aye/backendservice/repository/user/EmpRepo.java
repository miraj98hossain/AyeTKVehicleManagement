package com.aye.backendservice.repository.user;


import com.aye.entity.user.Emp;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Toufiq on 10/7/2019.
 */
public interface EmpRepo extends JpaRepository<Emp, Long> {
    Emp findByMuserId(Integer id);

    Emp findByEmpId(Integer id);

    Emp findByMuserIdAndOrgId(Integer id, Integer orgId);
}
