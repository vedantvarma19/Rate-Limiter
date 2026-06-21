package com.vedant.rateLimiter.controller;

import com.vedant.rateLimiter.dto.RateLimitIdentifier;
import com.vedant.rateLimiter.dto.RateLimitResult;
import com.vedant.rateLimiter.service.RateLimiterEngine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.vedant.rateLimiter.service.ApiKeyRateLimitService;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
public class RateLimitTestController {

    private final RateLimiterEngine engine;
    private final ApiKeyRateLimitService apiKeyService;

   public RateLimitTestController(
        RateLimiterEngine engine,
        ApiKeyRateLimitService apiKeyService) {

    this.engine = engine;
    this.apiKeyService = apiKeyService;
}

    @GetMapping("/api/test")
    public RateLimitResult test() {

        return engine.evaluate(
                "sliding_window",
                new RateLimitIdentifier(
                        "IP:127.0.0.1",
                        5,
                        60
                )
        );
    }

   @GetMapping("/api/token")
public RateLimitResult tokenTest(
        @RequestHeader("X-API-KEY")
        String apiKey) {

    long limit =
            apiKeyService.getCapacity(apiKey);

    return engine.evaluate(
            "token_bucket",
            new RateLimitIdentifier(
                    apiKey,
                    limit,
                    60
            )
    );
}
}