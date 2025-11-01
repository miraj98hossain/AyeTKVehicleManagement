package com.mhdev.backendservice.entity;

import com.mhdev.backendservice.utils.enums.StepStatus;
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
@Table(name = "STEP_TRANS_LINES", schema = "MAPPS")
public class StepTransLines {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "step_trans_lines_seq_gen"
    )
    @SequenceGenerator(
            name = "step_trans_lines_seq_gen",
            sequenceName = "STEP_TRANS_LINES_SEQ",
            allocationSize = 1
    )
    @Column(name = "STEP_TRANS_LINES_ID")
    private Long stepTransLinesId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STEP_TRANS_ID", nullable = false, referencedColumnName = "STEP_TRANS_ID")
    private StepTrans stepTrans;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STEP_ID", nullable = false, referencedColumnName = "STEP_ID")
    private Step step;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "STEP_STATUS", nullable = false)
    private StepStatus stepStatus;

    @Column(name = "REMARKS")
    private String remarks;
    @Column(name = "PARENT_LINE_ID", nullable = false)
    private Long parentLineId;
    @Column(name = "STAGE", nullable = false)
    private Integer stage;
    @OneToMany(mappedBy = "stepTransLines", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<StepTransTimeline> stepTransTimelineList = new ArrayList<>();


    @Column(name = "CREATED_BY", nullable = false, updatable = false)
    private Long createdBy;
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private Date createdAt;
    @Column(name = "UPDATED_BY", insertable = false)
    private Long updatedBy;
    @Column(name = "UPDATED_AT", insertable = false)
    private Date updatedAt;
}
