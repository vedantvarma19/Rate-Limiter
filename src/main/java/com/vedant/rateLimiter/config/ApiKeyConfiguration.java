package com.vedant.rateLimiter.config;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ApiKeyConfiguration {

    private final Map<String, Long> userLimits = Map.of(
            "free-user", 10L,
            "premium-user", 100L,
            "enterprise-user", 1000L
    );

    public long getLimit(String apiKey) {
        return userLimits.getOrDefault(apiKey, 5L);
    }
}