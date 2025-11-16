package com.subrutin.knowledge.domain;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DocumentMetadata implements Serializable {

    @JsonProperty("project_name")
    private String projectName;

    @JsonProperty("department")
    private String department;

    @JsonProperty("owner")
    private String owner;

    @JsonProperty("related_documents")
    private List<String> relatedDocuments;

    // Getters and Setters

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<String> getRelatedDocuments() {
        return relatedDocuments;
    }

    public void setRelatedDocuments(List<String> relatedDocuments) {
        this.relatedDocuments = relatedDocuments;
    }
}
