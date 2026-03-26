package com.flowsync.controllers;

import com.flowsync.dto.PatientDTO;
import com.flowsync.dto.PatientResponseDTO;
import com.flowsync.models.Patient;
import com.flowsync.services.PatientService;
import com.flowsync.services.mappers.PatientMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService patientService;
    private final PatientMapper patientMapper;

    public PatientController(PatientService patientService, PatientMapper patientMapper) {
        this.patientService = patientService;
        this.patientMapper = patientMapper;
    }

    @PostMapping
    public ResponseEntity createPatient(@RequestBody Patient patient) {

        patientMapper.validateFields(patient);

        PatientDTO patientDTO = patientMapper.createPatientDTOFromPatient(patient);

        Patient newPatient = patientService.createPatient(patientDTO);

        return ResponseEntity.ok("Patient created with id " + newPatient.getId());
    }

    @GetMapping("/{id}")
    public PatientResponseDTO findPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    @PutMapping("/{id}")
    public PatientResponseDTO updatePatient(@PathVariable Long id, @RequestBody PatientDTO patientDTO) {
        return patientService.updatePatient(id, patientDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePatient(@PathVariable Long id) {
        return patientService.deletePatient(id);
    }

}
