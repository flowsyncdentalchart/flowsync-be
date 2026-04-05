package com.flowsync.dto;

import com.flowsync.models.enums.ToothName;
import com.flowsync.models.enums.ToothState;

public class ToothRequest {
    private ToothName name;
    private ToothState state;
    private Long dentalChartId;

    public ToothRequest() {
    }

    public ToothRequest(ToothName name, ToothState state, Long dentalChartId) {
        this.name = name;
        this.state = state;
        this.dentalChartId = dentalChartId;
    }

    public ToothName getName() {
        return name;
    }

    public ToothState getState() {
        return state;
    }

    public Long getDentalChartId() {
        return dentalChartId;
    }
}
