package com.flowsync.services;

import com.flowsync.dto.ToothRequest;
import com.flowsync.dto.ToothResponse;
import com.flowsync.exceptions.ResourceNotFoundException;
import com.flowsync.models.DentalChart;
import com.flowsync.models.Tooth;
import com.flowsync.repositories.DentalChartRepository;
import com.flowsync.repositories.ToothRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToothService {
    private final ToothRepository toothRepository;
    private final DentalChartRepository dentalChartRepository;

    public ToothService(ToothRepository toothRepository, DentalChartRepository dentalChartRepository) {
        this.toothRepository = toothRepository;
        this.dentalChartRepository = dentalChartRepository;
    }

    public ToothResponse createTooth(ToothRequest toothRequest) {
        DentalChart dentalChart = dentalChartRepository.findById(toothRequest.getDentalChartId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Dental chart with id " + toothRequest.getDentalChartId() + " not found"));

        Tooth tooth = new Tooth(toothRequest.getName(), toothRequest.getState(), dentalChart);
        toothRepository.save(tooth);

        return new ToothResponse(tooth.getId(), tooth.getName(), tooth.getState(), tooth.getDentalChart().getId());
    }

    public ToothResponse getToothById(Long id) {

        Tooth tooth = toothRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tooth with id " + id + " was not found"));

        return new ToothResponse(tooth.getId(), tooth.getName(), tooth.getState(), tooth.getDentalChart().getId());
    }

    public List<ToothResponse> getToothByDentalChartId(Long dentalChartId) {

        List<Tooth> teeth = toothRepository.findAllByDentalChartId(dentalChartId);

        List<ToothResponse> teethResponse = new ArrayList<>();

        for (Tooth tooth : teeth) {
            teethResponse.add(new ToothResponse(tooth.getId(), tooth.getName(), tooth.getState(),
                    tooth.getDentalChart().getId()));
        }

        return teethResponse;

    }

    public ToothResponse updateTooth(Long id, ToothRequest toothRequest) {
        Tooth updatedTooth = toothRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tooth with id " + id + " not found"));

        if (toothRequest.getState() != null) {
            updatedTooth.setState(toothRequest.getState());
        }

        return new ToothResponse(updatedTooth.getId(), updatedTooth.getName(), updatedTooth.getState(),
                updatedTooth.getDentalChart().getId());
    }

}
