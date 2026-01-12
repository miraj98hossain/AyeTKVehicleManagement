package com.aye.backendservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "STEP_TRANS_DETAILS_LINES", schema = "MAPPS")
public class StepTransDetailsLines {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "step_trans_dtl_ln_seq_gen"
    )
    @SequenceGenerator(
            name = "step_trans_dtl_ln_seq_gen",
            sequenceName = "STEP_TRANS_DTL_LN_SEQ",
            allocationSize = 1
    )
    @Column(name = "STEP_TRANS_DTL_LN_ID")
    private Long stepTransDtlLnId;

    @Column(name = "STEP_TRANS_DTL_LN_NO", unique = true, nullable = false)
    private String stepTransDtlLnNo;

    @JoinColumn(name = "STEP_TRANS_DTL_ID", referencedColumnName = "STEP_TRANS_DTL_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private StepTransDetails stepTransDetails;

    @Column(name = "INVENTORY_ITEM_ID")
    private Long invItemId;

    @Column(name = "ORDERED_ITEM")
    private String orderedItem;

    @Column(name = "ORDERED_QUANTITY")
    private BigDecimal orderedQuantity;


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
