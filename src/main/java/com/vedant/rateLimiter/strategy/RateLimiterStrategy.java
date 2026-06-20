package com.vedant.rateLimiter.strategy;

import com.vedant.rateLimiter.dto.RateLimitIdentifier;
import com.vedant.rateLimiter.dto.RateLimitResult;

public interface RateLimiterStrategy {

    RateLimitResult evaluate(RateLimitIdentifier identifier);

    String getAlgorithmName();
}