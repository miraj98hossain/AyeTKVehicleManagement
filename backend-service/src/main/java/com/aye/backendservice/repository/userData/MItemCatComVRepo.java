package com.aye.backendservice.repository.userData;


import com.aye.entity.MItemCatComV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MItemCatComVRepo extends JpaRepository<MItemCatComV, Long>, JpaSpecificationExecutor<MItemCatComV> {


}
