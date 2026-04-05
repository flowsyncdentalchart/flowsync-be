package com.flowsync.dto;

public class DiagnosisResponse {

    private Long id;

    private String name;

    public DiagnosisResponse() {
    }

    public DiagnosisResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
