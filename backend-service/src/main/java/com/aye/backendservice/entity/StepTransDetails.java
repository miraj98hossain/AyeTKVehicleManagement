package com.aye.backendservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "STEP_TRANS_DETAILS", schema = "MAPPS")
public class StepTransDetails {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "step_trans_dtl_seq_gen"
    )
    @SequenceGenerator(
            name = "step_trans_dtl_seq_gen",
            sequenceName = "STEP_TRANS_DTL_SEQ",
            allocationSize = 1
    )
    @Column(name = "STEP_TRANS_DTL_ID")
    private Long stepTransDtlId;

    @Column(name = "STEP_TRANS_DTL_NO", unique = true, nullable = false)
    private String stepTransDtlNo;

    @JoinColumn(name = "STEP_TRANS_ID", nullable = false, referencedColumnName = "STEP_TRANS_ID")
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private StepTrans stepTrans;

    @Column(name = "CUST_NAME", nullable = false)
    private String custName;

    @Column(name = "ITEM", nullable = false)
    private String item;

    @Column(name = "QUANTITY", nullable = false)
    private Double quantity;
    @Column(name = "TRIP_NO")
    private Long tripNo;
    @Column(name = "SCHEDULE_NO")
    private Long scheduleNo;


    //**Auditing Columns

    @Column(name = "CREATED_BY", nullable = false, updatable = false)
    private Long createdBy;
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private Date createdAt;
    @Column(name = "UPDATED_BY", insertable = false)
    private Long updatedBy;
    @Column(name = "UPDATED_AT", insertable = false)
    private Date updatedAt;
}
