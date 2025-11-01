package com.mhdev.backendservice.entity;

import com.mhdev.backendservice.utils.enums.StepStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "STEP_TRANS_TIMELINE", schema = "MAPPS")
public class StepTransTimeline {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "step_trans_timeline_seq_gen"

    )
    @SequenceGenerator(
            name = "step_trans_timeline_seq_gen",
            sequenceName = "STEP_TRAN_TIMELINE_SEQ",
            allocationSize = 1

    )
    @Column(name = "STEP_TRANS_TL_ID")
    private Long stepTransTLId;

//    @Column(name = "STEP_TRANS_ID", nullable = false)
//    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    private StepTrans stepTrans;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STEP_TRANS_LINES_ID", nullable = false, updatable = false, referencedColumnName = "STEP_TRANS_LINES_ID")
    private StepTransLines stepTransLines;

    @JoinColumn(name = "STEP_ID", nullable = false, referencedColumnName = "STEP_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Step step;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "STEP_STATUS", nullable = false)
    private StepStatus stepStatus;

    @Column(name = "IGNITION_TIME")
    private LocalDateTime ignitionTime;

}
