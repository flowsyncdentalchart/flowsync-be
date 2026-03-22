package com.flowsync.dto;

public class XrayImageRequestDTO {


    private String dentalChart;

    private String takenBy;

    private String filePath;

    public XrayImageRequestDTO() {
    }

    public XrayImageRequestDTO(String dentalChart, String takenBy, String filePath) {
        this.dentalChart = dentalChart;
        this.takenBy = takenBy;
        this.filePath = filePath;
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
}
