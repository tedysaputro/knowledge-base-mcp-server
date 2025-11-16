package com.subrutin.knowledge.audit;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@RequestScoped
public class AuditableListener {

  
    @PrePersist
    void preCreate(Auditable auditable) {
        //temporary
        auditable.setCreatedBy("SYSTEM");
        auditable.setUpdatedBy("SYSTEM");
    }

    @PreUpdate
    void preUpdate(Auditable auditable) {
        //temporary
        auditable.setUpdatedBy("SYSTEM");
    }  
}
