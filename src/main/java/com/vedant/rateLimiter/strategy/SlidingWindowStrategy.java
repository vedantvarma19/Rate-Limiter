package com.vedant.rateLimiter.strategy;

import com.vedant.rateLimiter.dto.RateLimitIdentifier;
import com.vedant.rateLimiter.dto.RateLimitResult;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@Component
public class SlidingWindowStrategy implements RateLimiterStrategy {

    private static final String ALGORITHM_NAME = "sliding_window";

    private final StringRedisTemplate redisTemplate;

    private DefaultRedisScript<List> slidingWindowScript;

    public SlidingWindowStrategy(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void loadScript() throws IOException {

        ClassPathResource resource =
                new ClassPathResource("scripts/sliding_window.lua");

        String scriptText =
                new String(
                        resource.getInputStream().readAllBytes(),
                        StandardCharsets.UTF_8
                );

        slidingWindowScript = new DefaultRedisScript<>();
        slidingWindowScript.setScriptText(scriptText);
        slidingWindowScript.setResultType(List.class);
    }

    @Override
    public RateLimitResult evaluate(RateLimitIdentifier identifier) {

        long now = System.currentTimeMillis();
        long windowMs = identifier.windowSeconds() * 1000;

        try {

            List<?> result = redisTemplate.execute(
                    slidingWindowScript,
                    Collections.singletonList(identifier.key()),
                    String.valueOf(now),
                    String.valueOf(windowMs),
                    String.valueOf(identifier.capacity())
            );

            if (result == null || result.size() < 2) {
                return new RateLimitResult(
                        false,
                        0,
                        identifier.windowSeconds()
                );
            }

            boolean allowed =
                    ((Number) result.get(0)).longValue() == 1L;

            long remaining =
                    ((Number) result.get(1)).longValue();

            return new RateLimitResult(
                    allowed,
                    remaining,
                    identifier.windowSeconds()
            );

        } catch (Exception ex) {

            return new RateLimitResult(
                    false,
                    0,
                    identifier.windowSeconds()
            );
        }
    }

    @Override
    public String getAlgorithmName() {
        return ALGORITHM_NAME;
    }
}