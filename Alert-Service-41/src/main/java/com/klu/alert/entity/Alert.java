package com.klu.alert.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "alerts")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alertId;

    @Column(nullable = false)
    private Long meterId;

    @Column(nullable = false)
    private Double thresholdValue;

    @Column(nullable = false)
    private String alertStatus;

    public Alert() {
    }

    public Alert(Long alertId,
                 Long meterId,
                 Double thresholdValue,
                 String alertStatus) {

        this.alertId = alertId;
        this.meterId = meterId;
        this.thresholdValue = thresholdValue;
        this.alertStatus = alertStatus;
    }

    public Long getAlertId() {
        return alertId;
    }

    public void setAlertId(Long alertId) {
        this.alertId = alertId;
    }

    public Long getMeterId() {
        return meterId;
    }

    public void setMeterId(Long meterId) {
        this.meterId = meterId;
    }

    public Double getThresholdValue() {
        return thresholdValue;
    }

    public void setThresholdValue(Double thresholdValue) {
        this.thresholdValue = thresholdValue;
    }

    public String getAlertStatus() {
        return alertStatus;
    }

    public void setAlertStatus(String alertStatus) {
        this.alertStatus = alertStatus;
    }
}