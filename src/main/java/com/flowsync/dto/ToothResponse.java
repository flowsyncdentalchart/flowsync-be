package com.flowsync.dto;

import com.flowsync.models.enums.ToothState;

public class ToothResponse {

    private Long id;
    private String name;
    private ToothState state;
    private Long dentalChartId;

    public ToothResponse() {
    }

    public ToothResponse(Long id, String name, ToothState state, Long dentalChartId) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.dentalChartId = dentalChartId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ToothState getState() {
        return state;
    }

    public Long getDentalChartId() {
        return dentalChartId;
    }
}
