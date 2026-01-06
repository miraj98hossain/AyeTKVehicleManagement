package com.aye.backendservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: Miraj
 * @date: 05/01/2026
 * @time: 15:58
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "IP_INFO", schema = "MAPPS")
public class IPInfo {
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
    @Column(name = "id")
    private Long id;
    @Column(name = "ip_address", nullable = false)
    private String ipAddress;
    @Column(name = "name", nullable = false)
    private String name;
}
