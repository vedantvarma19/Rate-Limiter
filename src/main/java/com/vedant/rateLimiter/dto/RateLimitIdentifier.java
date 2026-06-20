package com.vedant.rateLimiter.dto;

public record RateLimitIdentifier(
        String key,
        long capacity,
        long windowSeconds
) {
}
