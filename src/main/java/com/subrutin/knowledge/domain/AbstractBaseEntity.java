package com.subrutin.knowledge.domain;

import java.io.Serializable;
import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.subrutin.knowledge.audit.Auditable;
import com.subrutin.knowledge.audit.AuditableListener;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Version;
import jakarta.persistence.EntityListeners;

@MappedSuperclass
@EntityListeners(AuditableListener.class)
public abstract class AbstractBaseEntity implements Auditable, Serializable {

    @CreationTimestamp
    @Column(name = "created_date", nullable = false, updatable = false)
    private Instant createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date", nullable = false)
    private Instant updatedDate;

    @Column(name = "created_by", nullable = false, updatable = false)
    String createdBy;

    @Column(name = "updated_by", nullable = false)
    String updatedBy;

    @Version
    @Column(name = "version")
    private Integer version;

    @Column(name = "deleted", columnDefinition = "boolean default false not null")
    private Boolean deleted;

    

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Instant updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @PrePersist
    public void prePersist() {
        this.deleted = Boolean.FALSE;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
