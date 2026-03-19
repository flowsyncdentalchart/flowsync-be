package com.flowsync.controllers;

import com.flowsync.services.DentalChartService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dentalChart")
public class DentalChartController {

    private final DentalChartService dentalChartService;

    public DentalChartController(DentalChartService dentalChartService) {
        this.dentalChartService = dentalChartService;
    }

    @PostMapping

}
