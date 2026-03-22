package com.flowsync.dto;

public class DentalChartDTO {

    private Long patientId;

    private Long userId;

    public DentalChartDTO(Long patientId, Long userId) {
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
