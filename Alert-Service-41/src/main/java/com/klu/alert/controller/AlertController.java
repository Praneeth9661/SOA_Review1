package com.klu.alert.controller;

import com.klu.alert.entity.Alert;
import com.klu.alert.service.AlertService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    // Create Alert
    @PostMapping
    public ResponseEntity<Alert> createAlert(
            @RequestBody Alert alert) {

        return ResponseEntity.ok(
                alertService.createAlert(alert)
        );
    }

    // Get all alerts
    @GetMapping
    public ResponseEntity<List<Alert>> getAllAlerts() {

        return ResponseEntity.ok(
                alertService.getAllAlerts()
        );
    }

    // Get alert by ID
    @GetMapping("/{alertId}")
    public ResponseEntity<Alert> getAlertById(
            @PathVariable Long alertId) {

        return alertService
                .getAlertById(alertId)
                .map(ResponseEntity::ok)
                .orElse(
                        ResponseEntity.notFound().build()
                );
    }

    // Get alerts by meter ID
    @GetMapping("/meter/{meterId}")
    public ResponseEntity<List<Alert>> getAlertsByMeterId(
            @PathVariable Long meterId) {

        return ResponseEntity.ok(
                alertService.getAlertsByMeterId(meterId)
        );
    }

    // Update alert
    @PutMapping("/{alertId}")
    public ResponseEntity<Alert> updateAlert(
            @PathVariable Long alertId,
            @RequestBody Alert alert) {

        try {

            return ResponseEntity.ok(
                    alertService.updateAlert(
                            alertId,
                            alert
                    )
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    // Delete alert
    @DeleteMapping("/{alertId}")
    public ResponseEntity<String> deleteAlert(
            @PathVariable Long alertId) {

        try {

            alertService.deleteAlert(alertId);

            return ResponseEntity.ok(
                    "Alert deleted successfully"
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    // Check consumption against threshold
    @PostMapping("/check")
    public ResponseEntity<Map<String, Object>> checkThreshold(
            @RequestParam Long meterId,
            @RequestParam Double consumption) {

        boolean triggered =
                alertService.checkThreshold(
                        meterId,
                        consumption
                );

        return ResponseEntity.ok(
                Map.of(
                        "meterId", meterId,
                        "consumption", consumption,
                        "alertTriggered", triggered
                )
        );
    }
}