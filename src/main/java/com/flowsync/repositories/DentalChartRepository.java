package com.flowsync.repositories;

import com.flowsync.models.DentalChart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DentalChartRepository extends JpaRepository<DentalChart, Long> {
}
