package com.vedant.rateLimiter.dto;


public record RateLimitResult(
        boolean allowed,
        long remaining,
        long resetAfterSeconds
) {
}