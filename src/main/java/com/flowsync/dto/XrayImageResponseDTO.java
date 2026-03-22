package com.flowsync.dto;

import java.time.LocalDateTime;

public class XrayImageResponseDTO {


    private Long id;

    private String dentalChart;

    private String takenBy;

    private String filePath;

    private LocalDateTime createdAt;

    public XrayImageResponseDTO() {
    }

    public XrayImageResponseDTO(Long id, String dentalChart, String takenBy, String filePath, LocalDateTime createdAt) {
        this.id = id;
        this.dentalChart = dentalChart;
        this.takenBy = takenBy;
        this.filePath = filePath;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDentalChart() {
        return dentalChart;
    }

    public void setDentalChart(String dentalChart) {
        this.dentalChart = dentalChart;
    }

    public String getTakenBy() {
        return takenBy;
    }

    public void setTakenBy(String takenBy) {
        this.takenBy = takenBy;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
