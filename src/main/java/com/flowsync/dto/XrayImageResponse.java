package com.flowsync.dto;

import java.time.LocalDateTime;

public class XrayImageResponse {

    private Long id;

    private Long dentalChartId;

    private Long userId;

    private String filePath;

    private LocalDateTime createdAt;

    public XrayImageResponse() {
    }

    public XrayImageResponse(Long id, Long dentalChartId, Long userId, String filePath, LocalDateTime createdAt) {
        this.id = id;
        this.dentalChartId = dentalChartId;
        this.userId = userId;
        this.filePath = filePath;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDentalChartId() {
        return dentalChartId;
    }

    public void setDentalChartId(Long dentalChartId) {
        this.dentalChartId = dentalChartId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
