package com.klu.meter.controller;

import com.klu.meter.entity.Meter;
import com.klu.meter.service.MeterService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meters")
public class MeterController {

    private final MeterService meterService;

    public MeterController(MeterService meterService) {
        this.meterService = meterService;
    }

    // Add Meter
    @PostMapping
    public ResponseEntity<Meter> addMeter(
            @RequestBody Meter meter) {

        return ResponseEntity.ok(
                meterService.addMeter(meter)
        );
    }

    // Get All Meters
    @GetMapping
    public ResponseEntity<List<Meter>> getAllMeters() {

        return ResponseEntity.ok(
                meterService.getAllMeters()
        );
    }

    // Get Meter By ID
    @GetMapping("/{meterId}")
    public ResponseEntity<Meter> getMeterById(
            @PathVariable Long meterId) {

        return meterService.getMeterById(meterId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update Meter
    @PutMapping("/{meterId}")
    public ResponseEntity<Meter> updateMeter(
            @PathVariable Long meterId,
            @RequestBody Meter meter) {

        try {

            return ResponseEntity.ok(
                    meterService.updateMeter(
                            meterId,
                            meter
                    )
            );

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();
        }
    }

    // Delete Meter
    @DeleteMapping("/{meterId}")
    public ResponseEntity<String> deleteMeter(
            @PathVariable Long meterId) {

        try {

            meterService.deleteMeter(meterId);

            return ResponseEntity.ok(
                    "Meter deleted successfully"
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .notFound()
                    .build();
        }
    }
}