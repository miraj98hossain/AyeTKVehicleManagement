package com.aye.backendservice.repository.concurrency;


import com.aye.entity.RequestGroupHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Toufiq on 3/7/2019.
 */
public interface RequestGroupHeaderRepo extends JpaRepository<RequestGroupHeader, Long>, JpaSpecificationExecutor<RequestGroupHeader> {

}
