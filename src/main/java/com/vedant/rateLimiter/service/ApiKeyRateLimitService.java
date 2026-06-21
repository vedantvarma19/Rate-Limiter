package com.vedant.rateLimiter.service;

import com.vedant.rateLimiter.config.ApiKeyConfiguration;
import org.springframework.stereotype.Service;

@Service
public class ApiKeyRateLimitService {

    private final ApiKeyConfiguration configuration;

    public ApiKeyRateLimitService(
            ApiKeyConfiguration configuration) {

        this.configuration = configuration;
    }

    public long getCapacity(String apiKey) {
        return configuration.getLimit(apiKey);
    }
}