package com.aye.backendservice.service.specification;


import com.aye.dtoLib.dto.request.StepTransFilter;
import com.aye.entitylib.entity.vehicleproject.StepTrans;
import com.aye.entitylib.entity.vehicleproject.StepTransDetails;
import com.aye.entitylib.entity.vehicleproject.StepTransDetailsLines;
import com.aye.entitylib.entity.vehicleproject.StepTransLines;
import com.aye.enums.StepStatus;
import com.aye.enums.StepTransStatus;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class StepTransSpecification {
    private final String STEP_STATUS = "stepStatus";
    private final String STEP_TRANS_STATUS = "stepTransStatus";
    private final String VEHICLE_NUMBER = "vehicleNumber";
    private final String DRIVER_PHONE_NO = "driverPhoneNo";
    private final String DRIVER_NAME = "driverName";
    private final String TRANSPORT_NAME = "transportName";
    private final String CREATED_BY = "createdBy";
    private final String CREATED_AT = "createdAt";
    private final String ITEM_NAME = "orderedItem";
    private final String CUST_NAME = "custName";
    private final String STEP_TRANS_DETAILS = "stepTransDetails";
    private final String STEP_TRANS_DETAILS_LINES = "stepTransDetailsLines";
    private final String STEP_TRANS = "stepTrans";
    private final String STEP_SETUP_DETAILS = "stepSetupDetails";
    private final String STEP_SETUP_DETAILS_ID = "stepSetupDetailsId";


    public Specification<StepTransLines> filterBy(StepTransFilter stepTransFilter, List<Long> setupDetailIds) {
        return Specification
                .<StepTransLines>unrestricted()
                .and(hasStepStatus(stepTransFilter.getStepStatus()))
                .and(hasSearchKeyword(stepTransFilter.getSearchKeyword()))

                .and(hasStepDetailsId(setupDetailIds))
                .and(hasFromDate(stepTransFilter.getFromDate()))
                .and(hasToDate(stepTransFilter.getToDate()));
    }

    private Specification<StepTransLines> hasSearchKeyword(String searchKeyword) {
        return (root, query, cb) -> {
            if (searchKeyword == null || searchKeyword.isEmpty()) {
                return cb.conjunction();
            }
            Join<StepTransLines, StepTrans> transJoin = root.join(STEP_TRANS, JoinType.INNER);

            Join<StepTrans, StepTransDetails> transDetailsJoin = transJoin.join(STEP_TRANS_DETAILS, JoinType.LEFT);

            Join<StepTransDetails, StepTransDetailsLines> transDetailsLineJoin = transDetailsJoin.join(STEP_TRANS_DETAILS_LINES, JoinType.LEFT);

            String pattern = "%" + searchKeyword.toLowerCase() + "%";
            return cb.or(
                    cb.like(cb.lower(transJoin.get(VEHICLE_NUMBER)), pattern),
                    cb.like(cb.lower(transJoin.get(DRIVER_PHONE_NO)), pattern),
                    cb.like(cb.lower(transJoin.get(DRIVER_NAME)), pattern),
                    cb.like(cb.lower(transJoin.get(TRANSPORT_NAME)), pattern),
                    cb.like(cb.lower(transDetailsJoin.get(CUST_NAME)), pattern),
                    cb.like(cb.lower(transDetailsLineJoin.get(ITEM_NAME)), pattern)
            );
        };
    }

    private Specification<StepTransLines> hasStepTransStatus(StepTransStatus stepTransStatus) {
        return ((root, query, cb) -> stepTransStatus == null ? cb.conjunction() : cb.equal(root.get(STEP_TRANS_STATUS), stepTransStatus));
    }

    private Specification<StepTransLines> hasStepStatus(StepStatus stepStatus) {
        return ((root, query, cb) -> stepStatus == null ? cb.conjunction() : cb.equal(root.get(STEP_STATUS), stepStatus));
    }

    private Specification<StepTransLines> hasFromDate(Date formDate) {
        return ((root, query, cb) -> formDate == null ? cb.conjunction() : cb.greaterThanOrEqualTo(root.get(CREATED_AT).as(java.util.Date.class), formDate));
    }

    private Specification<StepTransLines> hasToDate(Date toDate) {
        return ((root, query, cb) -> toDate == null ? cb.conjunction() : cb.lessThanOrEqualTo(root.get(CREATED_AT).as(java.util.Date.class), toDate));
    }

    private Specification<StepTransLines> hasStepDetailsId(List<Long> setupDetailIds) {
        return ((root, query, cb) -> setupDetailIds == null || setupDetailIds.isEmpty() ? cb.conjunction() : root.get(STEP_SETUP_DETAILS).get(STEP_SETUP_DETAILS_ID).in(setupDetailIds));
    }

}