package com.aye.backendservice.repository.orgManagement;


import com.aye.entity.projection.BeforeTripVProjection;
import com.aye.entity.schedule.BeforeTripV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author: Miraj
 * @date: 30/12/2025
 * @time: 11:20
 * @project: AyeTKVehicleManagement
 */
public interface BeforeTripVRepo extends JpaRepository<BeforeTripV, Long>,
        JpaSpecificationExecutor<BeforeTripV> {

//    @Query("""
//                SELECT
//                    Distinct
//                    b.orderNumber AS orderNumber,
//                    b.orderedDate AS orderedDate,
//                    b.partyName AS partyName
//                FROM BeforeTripV b
//                WHERE b.orgId = :orgId
//                  AND b.invOrgId = :invOrgId
//                  AND b.orderNumberMode LIKE %:mode%
//                  AND TO_CHAR(b.orderNumber) LIKE CONCAT('%', :searchWords, '%')
//            """)
//    List<BeforeTripVProjection> getDeliveryNumbers(Long orgId, Long invOrgId, String mode, Long searchWords);

    //    @Query("""
//                SELECT
//                    Distinct
//                    b.orderNumber AS orderNumber,
//                    b.orderedDate AS orderedDate,
//                    b.partyName AS partyName
//                FROM BeforeTripV b
//                WHERE b.orgId = :orgId
//                  AND b.invOrgId = :invOrgId
//                  AND TO_CHAR(b.orderNumber) LIKE CONCAT('%', :searchWords, '%')
//            """)
//    List<BeforeTripVProjection> getDeliveryNumbers(Long orgId, Long invOrgId, String mode, Long searchWords);
    @Query("""
                SELECT DISTINCT
                    b.orderNumber AS orderNumber,
                    b.orderedDate AS orderedDate,
                    b.partyName AS partyName,
                    b.orderNumberMode AS orderNumberMode
                FROM BeforeTripV b
                WHERE b.orgId = :orgId
                  AND b.invOrgId = :invOrgId
                  AND TO_CHAR(b.orderNumber) LIKE '%' || :searchWords || '%'
            """)
    List<BeforeTripVProjection> getDeliveryNumbers(Long orgId, Long invOrgId, String mode, Long searchWords);

    List<BeforeTripV> findAllByOrderNumber(Long orderNumber);
}
