package com.arridhaamrad.authentication.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
    value = {"createdAt", "updatedAt"},
    allowGetters = true
)
@Setter
@Getter
public abstract class AuditModel implements Serializable {
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "createdAt", nullable = false, updatable = false)
   @CreatedDate
   private Date createdAt;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "updatedAt", nullable = false, updatable = true)
   @LastModifiedDate
   private Date updatedAt;
}
