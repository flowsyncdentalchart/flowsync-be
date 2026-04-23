package com.flowsync.services.factories;

import com.flowsync.dto.ToothRequest;
import com.flowsync.models.enums.ToothName;
import com.flowsync.models.enums.ToothState;
import com.flowsync.services.ToothService;
import org.springframework.stereotype.Component;

@Component
public class DentalChartFactory {

    public final ToothService toothService;

    public DentalChartFactory(ToothService toothService) {
        this.toothService = toothService;
    }

    public void createTeethForDentalChart(Long id){
        ToothName[] teethName = ToothName.values();
        ToothState toothState = ToothState.PRESENT;

        for (ToothName name : teethName){
            ToothRequest toothRequest = new ToothRequest(name, toothState, id);
            toothService.createTooth(toothRequest);
        }

    }
}
