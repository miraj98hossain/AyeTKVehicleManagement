package com.aye.backendservice.service.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class NoGenService {

    private static final Logger log = LoggerFactory.getLogger(NoGenService.class);

    @PersistenceContext
    private EntityManager entityManager;

    private Long getNextVal(String sequenceName) {
        try {
            Query query = entityManager.createNativeQuery("SELECT " + sequenceName + ".NEXTVAL FROM DUAL");
            BigDecimal result = (BigDecimal) query.getSingleResult();
            return result.longValue();
        } catch (Exception e) {
            log.error("Error generating next value for sequence: {}", sequenceName, e);
            throw new IllegalStateException("Failed to generate transaction number", e);
        }
    }

    public String createTransNo() {
        return "TRN" + getNextVal("STP_TRANS_NO");
    }

    public String createTransLNo() {
        return "TRNL" + getNextVal("STP_TRANSL_NO");
    }

    public String createTransDNo() {
        return "TRND" + getNextVal("STP_TRANSD_NO");
    }

    public String createTransDtlLNo() {
        return "TRNDL" + getNextVal("STP_TRANSDL_NO");
    }
}
