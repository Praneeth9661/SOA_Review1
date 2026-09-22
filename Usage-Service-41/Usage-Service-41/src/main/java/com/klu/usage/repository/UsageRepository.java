package com.klu.usage.repository;

import com.klu.usage.entity.Usage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsageRepository extends JpaRepository<Usage, Long> {

    List<Usage> findByMeterId(Long meterId);
}