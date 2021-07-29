package com.arridhaamrad.authentication.models.entities;

import com.arridhaamrad.authentication.models.enums.RoleEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tbl_roles")
@Getter
@Setter
public class RoleEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false, length = 20)
   @Enumerated(EnumType.STRING)
   private RoleEnum name;
}
