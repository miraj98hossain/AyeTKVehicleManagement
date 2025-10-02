package com.mhdev.crud.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "_user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private Long userId;
   private String firstName;
   private String lastName;
   @Column(unique = true)
   private String phone;
   private String password;
}
