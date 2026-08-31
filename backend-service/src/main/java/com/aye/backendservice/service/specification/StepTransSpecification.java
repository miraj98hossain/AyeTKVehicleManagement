package com.aye.backendservice.service.specification;


import com.aye.dto.request.StepTransFilter;
import com.aye.entity.vehicleproject.StepTrans;
import com.aye.entity.vehicleproject.StepTransDetails;
import com.aye.entity.vehicleproject.StepTransDetailsLines;
import com.aye.enums.StepStatus;
import com.aye.enums.StepTransStatus;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class StepTransSpecification {
    private final String STEP_STATUS = "stepStatus";
    private final String STEP_TRANS_STATUS = "stepTransStatus";
    private final String VEHICLE_NUMBER = "vehicleNumber";
    private final String DRIVER_PHONE_NO = "driverPhoneNo";
    private final String DRIVER_NAME = "driverName";
    private final String TRANSPORT_NAME = "transportName";
    private final String STEP_NAME = "stepName";
    private final String CREATED_AT = "createdAt";
    private final String ITEM_NAME = "orderedItem";
    private final String CUST_NAME = "custName";
    private final String STEP_TRANS_DETAILS = "stepTransDetails";
    private final String STEP_TRANS_DETAILS_LINES = "stepTransDetailsLines";
    private final String STEP_TRANS = "stepTransId";
    private final String STEP_SETUP_DETAILS = "stepSetupDetails";
    private final String STEP_SETUP_DETAILS_ID = "stepSetupDetailsId";


    public Specification<StepTrans> filterBy(StepTransFilter stepTransFilter) {
        return Specification
                .<StepTrans>unrestricted()
                .and(hasStepTransStatus(stepTransFilter.getStepTransStatus()))
                .and(hasSearchKeyword(stepTransFilter.getSearchKeyword()))
                .and(hasFromDate(stepTransFilter.getFromDate()))
                .and(hasToDate(stepTransFilter.getToDate()))
                .and(orderBy());
    }

    private Specification<StepTrans> hasSearchKeyword(String searchKeyword) {
        return (root, query, cb) -> {
            if (searchKeyword == null || searchKeyword.isEmpty()) {
                return cb.conjunction();
            }
//            Join<StepTransLines, StepTrans> transJoin = root.join(STEP_TRANS, JoinType.INNER);
//            Join<StepTransLines, StepSetupDetails> setupDetailsJoin = root.join("stepSetupDetails", JoinType.INNER);
//            Join<StepSetupDetails, Step> stepJoin = setupDetailsJoin.join("step", JoinType.INNER);

            Join<StepTrans, StepTransDetails> transDetailsJoin = root.join(STEP_TRANS_DETAILS, JoinType.LEFT);

            Join<StepTransDetails, StepTransDetailsLines> transDetailsLineJoin = transDetailsJoin.join(STEP_TRANS_DETAILS_LINES, JoinType.LEFT);

            String pattern = "%" + searchKeyword.toLowerCase() + "%";
            return cb.or(
                    cb.like(cb.lower(root.get(VEHICLE_NUMBER)), pattern),
                    cb.like(cb.lower(root.get(DRIVER_PHONE_NO)), pattern),
                    cb.like(cb.lower(root.get(DRIVER_NAME)), pattern),
                    cb.like(cb.lower(root.get(TRANSPORT_NAME)), pattern),
                    cb.like(cb.lower(transDetailsJoin.get(CUST_NAME)), pattern),
                    cb.like(cb.lower(transDetailsLineJoin.get(ITEM_NAME)), pattern)
//                 ,cb.like(cb.lower(stepJoin.get(STEP_NAME)), pattern)
            );
        };
    }

    private Specification<StepTrans> hasStepTransStatus(StepTransStatus stepTransStatus) {
        return ((root, query, cb) -> stepTransStatus == null ? cb.conjunction() : cb.equal(root.get(STEP_TRANS_STATUS), stepTransStatus));
    }

    private Specification<StepTrans> hasStepStatus(StepStatus stepStatus) {
        return ((root, query, cb) -> stepStatus == null ? cb.conjunction() : cb.equal(root.get(STEP_STATUS), stepStatus));
    }

    private Specification<StepTrans> hasFromDate(LocalDate fromDate) {
        return ((root, query, cb) -> fromDate == null
                ? cb.conjunction()
                : cb.greaterThanOrEqualTo(root.get(CREATED_AT), fromDate.atStartOfDay()));
    }

    private Specification<StepTrans> hasToDate(LocalDate toDate) {
        return ((root, query, cb) -> toDate == null
                ? cb.conjunction()
                : cb.lessThan(root.get(CREATED_AT), toDate.plusDays(1).atStartOfDay()));
    }

    private Specification<StepTrans> orderBy() {
        return (root, query, cb) -> {
            assert query != null;
            query.orderBy(cb.asc(root.get(STEP_TRANS)));
            return cb.conjunction(); // no filtering, just ordering
        };
    }

}