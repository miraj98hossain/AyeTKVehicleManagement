package com.mhdev.backendservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "VEHICLE_CITY", schema = "MAPPS")
public class VehicleCity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vh_cty_gen"

    )
    @SequenceGenerator(
            name = "vh_cty_gen",
            sequenceName = "VH_CTY_SEQ",
            allocationSize = 1
    )
    @Column(name = "VH_CTY_ID")
    private Long vhCtyId;
    @Column(name = "VH_CTY_NAME")
    private String vhCtyName;
}
