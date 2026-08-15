package com.aye.backendservice.repository.orgManagement;


import com.aye.entity.projection.BeforeTripWDsVProjection;
import com.aye.entity.schedule.BeforeTripWDsV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author: Miraj
 * @date: 30/12/2025
 * @time: 10:38
 * @project: AyeTKVehicleManagement
 */
public interface BeforeTripWDsVRepo extends JpaRepository<BeforeTripWDsV, Long>, JpaSpecificationExecutor<BeforeTripWDsV> {
    @Query("""
                SELECT DISTINCT
                    b.scheduleId AS scheduleId,
                    b.scheduleNumber AS scheduleNumber,
                    b.partyName AS partyName
                FROM BeforeTripWDsV b
                WHERE b.orgId = :orgId
                  AND b.invOrgId = :invOrgId
                  AND b.scheduleNumber LIKE CONCAT('%', :searchWords, '%')
            """)
    List<BeforeTripWDsVProjection> findScheduleId(
            @Param("orgId") Long orgId,
            @Param("invOrgId") Long invOrgId,
            @Param("searchWords") String searchWords
    );


    List<BeforeTripWDsV> findAllByScheduleNumber(String scheduleNumber);
}
