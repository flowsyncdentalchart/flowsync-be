package com.flowsync.services;

import com.flowsync.dto.DiagnosisResponse;
import com.flowsync.exceptions.ResourceNotFoundException;
import com.flowsync.models.Diagnosis;
import com.flowsync.repositories.DiagnosisRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;

    public DiagnosisService(DiagnosisRepository diagnosisRepository) {
        this.diagnosisRepository = diagnosisRepository;
    }

    public DiagnosisResponse getDiagnosisById(Long id) {
        Diagnosis diagnosis = diagnosisRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diagnosis not found"));

        return new DiagnosisResponse(diagnosis.getId(), diagnosis.getName());

    }

    public List<DiagnosisResponse> getAllDiagnoses() {
        List<DiagnosisResponse> diagnosisResponses = new ArrayList<>();
        List<Diagnosis> diagnoses = diagnosisRepository.findAll();
        for (Diagnosis diagnosis : diagnoses) {
            diagnosisResponses.add(new DiagnosisResponse(diagnosis.getId(), diagnosis.getName()));
        }
        return diagnosisResponses;
    }
}
