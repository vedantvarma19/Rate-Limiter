package com.vedant.rateLimiter.service;
import com.vedant.rateLimiter.dto.RateLimitIdentifier;
import com.vedant.rateLimiter.dto.RateLimitResult;
import com.vedant.rateLimiter.exception.UnsupportedAlgorithmException;
import com.vedant.rateLimiter.strategy.RateLimiterStrategy;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class RateLimiterEngine {

    private final Map<String, RateLimiterStrategy> strategies;

    public RateLimiterEngine(List<RateLimiterStrategy> strategyList) {

        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(
                        RateLimiterStrategy::getAlgorithmName,
                        Function.identity()
                ));
    }

    public RateLimitResult evaluate(
            String algorithmName,
            RateLimitIdentifier identifier
    ) {

        RateLimiterStrategy strategy =
                strategies.get(algorithmName);

        if (strategy == null) {
            throw new UnsupportedAlgorithmException(
                    algorithmName
            );
        }

        return strategy.evaluate(identifier);
    }
}