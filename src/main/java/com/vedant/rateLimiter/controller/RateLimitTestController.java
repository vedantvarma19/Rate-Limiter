package com.vedant.rateLimiter.controller;

import com.vedant.rateLimiter.dto.RateLimitIdentifier;
import com.vedant.rateLimiter.dto.RateLimitResult;
import com.vedant.rateLimiter.service.RateLimiterEngine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitTestController {

    private final RateLimiterEngine engine;

    public RateLimitTestController(RateLimiterEngine engine) {
        this.engine = engine;
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
}