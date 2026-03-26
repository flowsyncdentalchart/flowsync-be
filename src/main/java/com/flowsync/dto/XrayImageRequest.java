package com.flowsync.dto;

public class XrayImageRequest {


    private Long dentalChartId;

    private Long userId;

    private String filePath;

    public XrayImageRequest() {
    }

    public XrayImageRequest(Long dentalChart, Long userId, String filePath) {
        this.dentalChartId = dentalChart;
        this.userId = userId;
        this.filePath = filePath;
    }

    public Long getDentalChart() {
        return dentalChartId;
    }

    public void setDentalChart(Long dentalChart) {
        this.dentalChartId = dentalChart;
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
}
