package com.flowsync.services;

import com.flowsync.dto.DiagnosisResponse;
import com.flowsync.dto.RestorationResponse;
import com.flowsync.dto.ToothRequest;
import com.flowsync.dto.ToothResponse;
import com.flowsync.exceptions.ResourceNotFoundException;
import com.flowsync.models.DentalChart;
import com.flowsync.models.Diagnosis;
import com.flowsync.models.Restoration;
import com.flowsync.models.Tooth;
import com.flowsync.repositories.DentalChartRepository;
import com.flowsync.repositories.DiagnosisRepository;
import com.flowsync.repositories.RestorationRepository;
import com.flowsync.repositories.ToothRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToothService {
    private final ToothRepository toothRepository;
    private final DentalChartRepository dentalChartRepository;
    private final DiagnosisRepository diagnosisRepository;
    private final RestorationRepository restorationRepository;

    public ToothService(ToothRepository toothRepository, DentalChartRepository dentalChartRepository,
            DiagnosisRepository diagnosisRepository, RestorationRepository restorationRepository) {
        this.toothRepository = toothRepository;
        this.dentalChartRepository = dentalChartRepository;
        this.diagnosisRepository = diagnosisRepository;
        this.restorationRepository = restorationRepository;
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

    @Transactional
    public void addDiagnosisToTooth(Long toothId, Long diagnosisId) {
        Tooth tooth = toothRepository.findById(toothId)
                .orElseThrow(() -> new ResourceNotFoundException("Tooth not found"));
        Diagnosis diagnosis = diagnosisRepository.findById(diagnosisId)
                .orElseThrow(() -> new ResourceNotFoundException("Diagnosis not found"));

        tooth.addDiagnosis(diagnosis);
        // no need to call save() — @Transactional handles dirty checking
    }

    @Transactional(readOnly = true)
    public List<DiagnosisResponse> getDiagnosesByToothId(Long toothId) {
        Tooth tooth = toothRepository.findById(toothId)
                .orElseThrow(() -> new ResourceNotFoundException("Tooth not found"));
        return tooth.getDiagnoses().stream().map(d -> new DiagnosisResponse(d.getId(), d.getName()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void removeDiagnosisFromTooth(Long toothId, Long diagnosisId) {
        Tooth tooth = toothRepository.findById(toothId)
                .orElseThrow(() -> new ResourceNotFoundException("Tooth not found"));
        Diagnosis diagnosis = diagnosisRepository.findById(diagnosisId)
                .orElseThrow(() -> new ResourceNotFoundException("Diagnosis not found"));

        tooth.removeDiagnosis(diagnosis);
    }

    @Transactional
    public void addRestorationToTooth(Long toothId, Long restorationId) {
        Tooth tooth = toothRepository.findById(toothId)
                .orElseThrow(() -> new ResourceNotFoundException("Tooth not found"));
        Restoration restoration = restorationRepository.findById(restorationId)
                .orElseThrow(() -> new ResourceNotFoundException("Restoration not found"));

        tooth.addRestoration(restoration);
    }

    @Transactional(readOnly = true)
    public List<RestorationResponse> getRestorationsByToothId(Long toothId) {
        Tooth tooth = toothRepository.findById(toothId)
                .orElseThrow(() -> new ResourceNotFoundException("Tooth not found"));
        return tooth.getRestorations().stream()
                .map(r -> new RestorationResponse(r.getId(), r.getName(), r.getMaterial()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void removeRestorationFromTooth(Long toothId, Long restorationId) {
        Tooth tooth = toothRepository.findById(toothId)
                .orElseThrow(() -> new ResourceNotFoundException("Tooth not found"));
        Restoration restoration = restorationRepository.findById(restorationId)
                .orElseThrow(() -> new ResourceNotFoundException("Restoration not found"));

        tooth.removeRestoration(restoration);
    }

}
