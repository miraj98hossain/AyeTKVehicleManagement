package com.aye.backendservice.entity;

import com.aye.RestfulServer.model.geo.Geo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by toufiq on 5/28/2021.
 */
@Getter
@Entity
@Table(name = "M_TRNS_COUNT")
public class MtrnsCount {

    @Setter
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "HIBERNATE_SEQUENCE"
    )
    @SequenceGenerator(
            name = "HIBERNATE_SEQUENCE",
            sequenceName = "HIBERNATE_SEQUENCE",
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "COUNT")
    private Long count;

    @Column(name = "POSITIONFROMLASTWEEK")
    private Long positionFromLastWeek;

    @Enumerated(EnumType.STRING)
    @Column(name = "TRNS_SOURCE")
    private Geo.GeoRefTrnsSource trnsSource;
}
