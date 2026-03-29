package com.flowsync.controllers;

import com.flowsync.dto.ToothRequest;
import com.flowsync.dto.ToothResponse;
import com.flowsync.services.ToothService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tooth")
public class ToothController {

    private final ToothService toothService;

    public ToothController(ToothService toothService) {
        this.toothService = toothService;
    }

    @PostMapping
    public ResponseEntity createTooth(@RequestBody ToothRequest toothRequest) {

        ToothResponse toothResponse = toothService.createTooth(toothRequest);

        return ResponseEntity.ok(toothResponse.getName() + " created with id " + toothResponse.getId());
    }

    @GetMapping("/{id}")
    public ToothResponse getToothById(@PathVariable Long id) {
        return toothService.getToothById(id);
    }

    @GetMapping("/dentalChart/{id}")
    public List<ToothResponse> getToothByDentalChartId(@PathVariable Long id) {
        return toothService.getToothByDentalChartId(id);
    }

    @PutMapping("/{id}")
    public ToothResponse updateTooth(@PathVariable Long id, @RequestBody ToothRequest toothRequest) {
        return toothService.updateTooth(id, toothRequest);
    }
}
