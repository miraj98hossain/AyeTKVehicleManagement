package com.aye.backendservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @JoinColumn(name = "STEP_TRANS_ID", referencedColumnName = "STEP_TRANS_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private StepTrans stepTrans;

    @OneToMany(mappedBy = "stepTransDetails", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<StepTransDetailsLines> stepTransDetailsLines = new ArrayList<>();
    @Column(name = "CUST_ACCOUNT_ID")
    private Long custAccountId;
    @Column(name = "CUST_NAME")
    private String custName;
    @Column(name = "ORDER_NUMBER")
    private Long orderNumber;
    @Column(name = "SCHEDULE_NO")
    private String scheduleNo;


    //**Auditing Columns

    @Column(name = "CREATED_BY", nullable = false, updatable = false)
    private Long createdBy;
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private Date createdAt;
    @Column(name = "UPDATED_BY", insertable = false)
    private Long updatedBy;
    @Column(name = "UPDATED_AT", insertable = false)
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        Date now = new Date();
        this.createdAt = now;
        this.updatedAt = now;

    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }
}
