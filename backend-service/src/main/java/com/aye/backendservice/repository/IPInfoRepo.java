package com.aye.backendservice.repository;

import com.aye.backendservice.entity.IPInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: Miraj
 * @date: 05/01/2026
 * @time: 16:04
 */
public interface IPInfoRepo extends JpaRepository<IPInfo, Long> {
}
