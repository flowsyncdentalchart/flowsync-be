package com.flowsync.controllers;

import com.flowsync.dto.DiagnosisResponse;
import com.flowsync.services.DiagnosisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/diagnoses")
public class DiagnosisController {

    private final DiagnosisService diagnosisService;

    public DiagnosisController(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @GetMapping("/{id}")
    public DiagnosisResponse getDiagnosisById(@PathVariable Long id) {
        return diagnosisService.getDiagnosisById(id);
    }
}
