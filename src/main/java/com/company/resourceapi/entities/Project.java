package com.company.resourceapi.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Table(name = "project")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(of = "id")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "external_id", nullable = false)
    @NotBlank
    private String externalId;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "sdlc_system_id")
    @NotNull
    private SdlcSystem sdlcSystem;

    @Column(name = "created_date", nullable = false)
    @CreatedDate
    private Instant createdDate;

    @Column(name = "last_modified_date", nullable = false)
    @LastModifiedDate
    private Instant lastModifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSdlcSystem(SdlcSystem sdlcSystem) {
        this.sdlcSystem = sdlcSystem;
    }

    @PrePersist
    public void persist() {
        System.out.println("persist");
    }
    @PreUpdate
    public void update() {
        System.out.println("update");
    }

}
