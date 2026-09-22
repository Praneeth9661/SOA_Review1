package com.klu.usage.controller;

import com.klu.usage.entity.Usage;
import com.klu.usage.service.UsageService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usage")
public class UsageController {

    private final UsageService usageService;

    public UsageController(UsageService usageService) {
        this.usageService = usageService;
    }

    // Add usage
    @PostMapping
    public ResponseEntity<Usage> addUsage(
            @RequestBody Usage usage) {

        return ResponseEntity.ok(
                usageService.addUsage(usage)
        );
    }

    // Get all usage
    @GetMapping
    public ResponseEntity<List<Usage>> getAllUsage() {

        return ResponseEntity.ok(
                usageService.getAllUsage()
        );
    }

    // Get usage by ID
    @GetMapping("/{usageId}")
    public ResponseEntity<Usage> getUsageById(
            @PathVariable Long usageId) {

        return usageService
                .getUsageById(usageId)
                .map(ResponseEntity::ok)
                .orElse(
                        ResponseEntity.notFound().build()
                );
    }

    // Get usage for a particular meter
    @GetMapping("/meter/{meterId}")
    public ResponseEntity<List<Usage>> getUsageByMeterId(
            @PathVariable Long meterId) {

        return ResponseEntity.ok(
                usageService.getUsageByMeterId(meterId)
        );
    }

    // Update usage
    @PutMapping("/{usageId}")
    public ResponseEntity<Usage> updateUsage(
            @PathVariable Long usageId,
            @RequestBody Usage usage) {

        try {

            return ResponseEntity.ok(
                    usageService.updateUsage(
                            usageId,
                            usage
                    )
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    // Delete usage
    @DeleteMapping("/{usageId}")
    public ResponseEntity<String> deleteUsage(
            @PathVariable Long usageId) {

        try {

            usageService.deleteUsage(usageId);

            return ResponseEntity.ok(
                    "Usage record deleted successfully"
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .notFound()
                    .build();
        }
    }
}