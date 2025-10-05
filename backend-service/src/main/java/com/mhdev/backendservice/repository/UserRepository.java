package com.mhdev.backendservice.repository;

import com.mhdev.backendservice.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser,Long> {
    Optional<AppUser> findByPhone(String phone);
}
