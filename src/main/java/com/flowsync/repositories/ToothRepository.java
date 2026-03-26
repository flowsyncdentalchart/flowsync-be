package com.flowsync.repositories;

import com.flowsync.models.Tooth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToothRepository extends JpaRepository<Tooth, Long> {

    List<Tooth> findAllByDentalChartId(Long id);

}
