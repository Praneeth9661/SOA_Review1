package com.klu.meter.repository;

import com.klu.meter.entity.Meter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeterRepository extends JpaRepository<Meter, Long> {

}