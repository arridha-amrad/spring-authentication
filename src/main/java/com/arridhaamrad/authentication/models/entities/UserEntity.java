package com.arridhaamrad.authentication.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_users")
@Getter
@Setter
public class UserEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false, unique = true)
   private String username;

   @Column(nullable = false, unique = true)
   private String email;

   @Column(nullable = false)
   private String password;

   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(
        name = "tbl_user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
   )
   private Set<RoleEntity> roles = new HashSet<>();
}
