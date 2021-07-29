package com.arridhaamrad.authentication.models.entities;

import com.arridhaamrad.authentication.customAnnotations.UserEmailUniqueConstraint;
import com.arridhaamrad.authentication.models.AuditModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "tbl_users")
@Getter
@Setter
public class UserEntity extends AuditModel implements UserDetails {
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

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      List<GrantedAuthority> authorities = roles.stream()
          .map(role -> new SimpleGrantedAuthority(role.getName().name()))
          .collect(Collectors.toList());
      return authorities;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }
}
