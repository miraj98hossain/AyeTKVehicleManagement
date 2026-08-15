package com.aye.backendservice.repository.user;


import com.aye.entity.user.Mrole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MroleRepo extends JpaRepository<Mrole, Integer> {

    Mrole findByRole(String role);
}
