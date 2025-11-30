package com.aye.backendservice.entity;

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


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STEP_TRANS_LINES_ID", nullable = false, updatable = false, unique = true, referencedColumnName = "STEP_TRANS_LINES_ID")
    private StepTransLines stepTransLines;

//    @JoinColumn(name = "STEP_SETUP_DETAILS_ID", nullable = false, updatable = false, unique = true, referencedColumnName = "STEP_SETUP_DETAILS_ID")
//    @ManyToOne(fetch = FetchType.EAGER)
//    private StepSetupDetails stepSetupDetails;

    @Column(name = "IGN_TIME_N")
    private LocalDateTime ignTimeN;

    @Column(name = "IGN_TIME_P")
    private LocalDateTime ignTimeP;

    @Column(name = "IGN_TIME_W")
    private LocalDateTime ignTimeW;

    @Column(name = "IGN_TIME_C")
    private LocalDateTime ignTimeC;

    @Column(name = "IGN_TIME_R")
    private LocalDateTime ignTimeR;

}
