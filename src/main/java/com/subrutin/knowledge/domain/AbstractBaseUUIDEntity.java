package com.subrutin.knowledge.domain;

import java.util.UUID;

import com.fasterxml.uuid.Generators;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;

@MappedSuperclass
public abstract class AbstractBaseUUIDEntity extends AbstractBaseEntity {

    @Column(name = "secure_id", unique = true, nullable = false)
    private UUID secureId;

    @Override
    @PrePersist
    public void prePersist() {
        this.setDeleted(Boolean.FALSE);
        if (this.secureId == null) {
            secureId = Generators.timeBasedEpochGenerator().generate();
        }
    }

    public UUID getSecureId() {
        return secureId;
    }

    public void setSecureId(UUID secureId) {
        this.secureId = secureId;
    }

}
