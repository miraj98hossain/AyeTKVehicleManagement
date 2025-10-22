package com.mhdev.backendservice.repository;

import com.mhdev.backendservice.entity.Step;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StepRepository extends JpaRepository<Step,Long>,JpaSpecificationExecutor<Step> {

}
