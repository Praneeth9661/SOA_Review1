package com.klu.alert.service;

import com.klu.alert.entity.Alert;
import com.klu.alert.repository.AlertRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlertService {

    private final AlertRepository alertRepository;

    public AlertService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    // Create alert
    public Alert createAlert(Alert alert) {

        if (alert.getAlertStatus() == null ||
            alert.getAlertStatus().isBlank()) {

            alert.setAlertStatus("ACTIVE");
        }

        return alertRepository.save(alert);
    }

    // Get all alerts
    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    // Get alert by ID
    public Optional<Alert> getAlertById(Long alertId) {
        return alertRepository.findById(alertId);
    }

    // Get alerts for a meter
    public List<Alert> getAlertsByMeterId(Long meterId) {
        return alertRepository.findByMeterId(meterId);
    }

    // Update alert
    public Alert updateAlert(Long alertId,
                             Alert alert) {

        Alert existingAlert =
                alertRepository.findById(alertId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Alert not found"
                                )
                        );

        existingAlert.setMeterId(
                alert.getMeterId()
        );

        existingAlert.setThresholdValue(
                alert.getThresholdValue()
        );

        existingAlert.setAlertStatus(
                alert.getAlertStatus()
        );

        return alertRepository.save(existingAlert);
    }

    // Delete alert
    public void deleteAlert(Long alertId) {

        if (!alertRepository.existsById(alertId)) {

            throw new RuntimeException(
                    "Alert not found"
            );
        }

        alertRepository.deleteById(alertId);
    }

    // Check consumption against threshold
    public boolean checkThreshold(
            Long meterId,
            Double consumption) {

        List<Alert> alerts =
                alertRepository.findByMeterId(meterId);

        for (Alert alert : alerts) {

            if ("ACTIVE".equalsIgnoreCase(
                    alert.getAlertStatus())) {

                if (consumption >=
                    alert.getThresholdValue()) {

                    alert.setAlertStatus("TRIGGERED");

                    alertRepository.save(alert);

                    return true;
                }
            }
        }

        return false;
    }
}