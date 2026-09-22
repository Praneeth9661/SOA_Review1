package com.klu.meter.service;

import com.klu.meter.entity.Meter;
import com.klu.meter.repository.MeterRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeterService {

    private final MeterRepository meterRepository;

    public MeterService(MeterRepository meterRepository) {
        this.meterRepository = meterRepository;
    }

    // Add new meter
    public Meter addMeter(Meter meter) {
        return meterRepository.save(meter);
    }

    // Get all meters
    public List<Meter> getAllMeters() {
        return meterRepository.findAll();
    }

    // Get meter by ID
    public Optional<Meter> getMeterById(Long meterId) {
        return meterRepository.findById(meterId);
    }

    // Update meter
    public Meter updateMeter(Long meterId, Meter meter) {

        Meter existingMeter =
                meterRepository.findById(meterId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Meter not found"));

        existingMeter.setUserId(meter.getUserId());
        existingMeter.setLocation(meter.getLocation());
        existingMeter.setStatus(meter.getStatus());

        return meterRepository.save(existingMeter);
    }

    // Delete meter
    public void deleteMeter(Long meterId) {

        if (!meterRepository.existsById(meterId)) {
            throw new RuntimeException("Meter not found");
        }

        meterRepository.deleteById(meterId);
    }
}