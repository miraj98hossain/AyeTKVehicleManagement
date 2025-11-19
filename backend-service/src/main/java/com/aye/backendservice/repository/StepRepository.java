package com.aye.backendservice.repository;

import com.aye.backendservice.entity.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StepRepository extends JpaRepository<Step, Long>, JpaSpecificationExecutor<Step> {
//    @Query(value = "SELECT S from Step S")
//    List<Step> findAllStep();
}
