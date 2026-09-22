package com.klu.usage.service;

import com.klu.usage.entity.Usage;
import com.klu.usage.repository.UsageRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsageService {

    private final UsageRepository usageRepository;
    private final RestTemplate restTemplate;

    public UsageService(UsageRepository usageRepository,
                        RestTemplate restTemplate) {

        this.usageRepository = usageRepository;
        this.restTemplate = restTemplate;
    }

    // Add usage record
    public Usage addUsage(Usage usage) {

        if (usage.getTimestamp() == null) {
            usage.setTimestamp(LocalDateTime.now());
        }

        // Save usage record
        Usage savedUsage = usageRepository.save(usage);

        // Send usage information to Alert Service
        String alertUrl =
                "http://ALERT-SERVICE-41/api/alerts/check"
                + "?meterId=" + usage.getMeterId()
                + "&consumption=" + usage.getConsumptionUnits();

        try {

            restTemplate.postForObject(
                    alertUrl,
                    null,
                    Boolean.class
            );

        } catch (Exception e) {

            System.out.println(
                    "Alert Service communication failed: "
                    + e.getMessage()
            );
        }

        return savedUsage;
    }

    // Get all usage records
    public List<Usage> getAllUsage() {

        return usageRepository.findAll();
    }

    // Get usage by ID
    public Optional<Usage> getUsageById(Long usageId) {

        return usageRepository.findById(usageId);
    }

    // Get usage by meter ID
    public List<Usage> getUsageByMeterId(Long meterId) {

        return usageRepository.findByMeterId(meterId);
    }

    // Update usage
    public Usage updateUsage(Long usageId, Usage usage) {

        Usage existingUsage =
                usageRepository.findById(usageId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Usage record not found"
                                )
                        );

        existingUsage.setMeterId(
                usage.getMeterId()
        );

        existingUsage.setConsumptionUnits(
                usage.getConsumptionUnits()
        );

        if (usage.getTimestamp() != null) {

            existingUsage.setTimestamp(
                    usage.getTimestamp()
            );
        }

        return usageRepository.save(existingUsage);
    }

    // Delete usage
    public void deleteUsage(Long usageId) {

        if (!usageRepository.existsById(usageId)) {

            throw new RuntimeException(
                    "Usage record not found"
            );
        }

        usageRepository.deleteById(usageId);
    }
}