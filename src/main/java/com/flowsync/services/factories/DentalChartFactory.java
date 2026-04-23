package com.flowsync.services.factories;

import com.flowsync.dto.ToothRequest;
import com.flowsync.dto.ToothResponse;
import com.flowsync.models.DentalChart;
import com.flowsync.models.enums.ToothName;
import com.flowsync.models.enums.ToothState;
import com.flowsync.services.ToothService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DentalChartFactory {

    public final ToothService toothService;

    public DentalChartFactory(ToothService toothService) {
        this.toothService = toothService;
    }

    public List<ToothResponse> createTeethForDentalChart(DentalChart dentalChart){
        ToothName[] teethName = ToothName.values();
        List<ToothResponse> teethList = new ArrayList<>();
        ToothState toothState = ToothState.PRESENT;
        int i = 0;

        for (ToothName name : teethName){
            ToothRequest toothRequest = new ToothRequest(name, toothState, dentalChart.getId());
            ToothResponse tooth = toothService.createTooth(toothRequest);
            teethList.add(tooth);
        }

        return teethList;
    }
}
