package com.flowsync.dto;

public class DentalChartDTO {

    private Long id;

    private Long patientId;

    private Long userId;

    public DentalChartDTO(Long id, Long patientId, Long userId) {
        this.id = id;
        this.patientId = patientId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
