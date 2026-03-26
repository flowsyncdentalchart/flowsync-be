package com.flowsync.repositories;

import com.flowsync.models.Tooth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToothRepository extends JpaRepository<Tooth, Long> {

}
