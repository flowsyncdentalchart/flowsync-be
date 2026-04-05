package com.flowsync.controllers;

import com.flowsync.dto.RestorationResponse;
import com.flowsync.services.RestorationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/restorations")
public class RestorationController {

    private final RestorationService restorationService;

    public RestorationController(RestorationService restorationService) {
        this.restorationService = restorationService;
    }

    @GetMapping("/{id}")
    public RestorationResponse getRestorationById(@PathVariable Long id) {
        return restorationService.getRestorationById(id);
    }

    @GetMapping
    public List<RestorationResponse> getAllRestorations() {
        return restorationService.getAllRestorations();
    }

}
