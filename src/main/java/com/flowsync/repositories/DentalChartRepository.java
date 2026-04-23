package com.flowsync.repositories;

import com.flowsync.models.DentalChart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DentalChartRepository extends JpaRepository<DentalChart, Long> {
    List<DentalChart> findAllByPatientId(Long patientId);
}
