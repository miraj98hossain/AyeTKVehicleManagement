package com.aye.backendservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "VEHICLE_CITY_CLASS", schema = "MAPPS")
public class VehicleCityClass {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vh_cty_class_gen"

    )
    @SequenceGenerator(
            name = "vh_cty_class_gen",
            sequenceName = "VH_CTY_CLASS_SEQ",
            allocationSize = 1
    )
    @Column(name = "VH_CTY_CLS_ID")
    private Long vhCtyClassId;
    @Column(name = "VH_CTY_CLS_NAME")
    private String vhCtyClassName;
}
