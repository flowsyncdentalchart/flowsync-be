package com.flowsync.services;

import com.flowsync.dto.RestorationResponse;
import com.flowsync.exceptions.ResourceNotFoundException;
import com.flowsync.models.Restoration;
import com.flowsync.repositories.RestorationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestorationService {

    private final RestorationRepository restorationRepository;

    public RestorationService(RestorationRepository restorationRepository) {
        this.restorationRepository = restorationRepository;
    }

    public RestorationResponse getRestorationById(Long id) {
        Restoration restoration = restorationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restoration with id " + id + " not found"));
        return new RestorationResponse(restoration.getId(), restoration.getName(), restoration.getMaterial());
    }

    public List<RestorationResponse> getAllRestorations() {
        List<RestorationResponse> restorationResponses = new ArrayList<>();
        List<Restoration> restorations = restorationRepository.findAll();
        for (Restoration restoration : restorations) {
            restorationResponses.add(
                    new RestorationResponse(restoration.getId(), restoration.getName(), restoration.getMaterial()));
        }
        return restorationResponses;
    }
}
