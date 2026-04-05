package com.flowsync.dto;

public class DentalChartRequest {

    private Long patientId;

    private Long userId;

    public DentalChartRequest(Long patientId, Long userId) {
        this.patientId = patientId;
        this.userId = userId;
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
