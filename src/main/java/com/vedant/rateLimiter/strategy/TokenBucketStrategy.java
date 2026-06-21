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
public class TokenBucketStrategy implements RateLimiterStrategy {

    private static final String ALGORITHM_NAME = "token_bucket";

    private final StringRedisTemplate redisTemplate;

    private DefaultRedisScript<List> tokenBucketScript;

    public TokenBucketStrategy(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void loadScript() throws IOException {

        ClassPathResource resource =
                new ClassPathResource("scripts/token_bucket.lua");

        String scriptText =
                new String(
                        resource.getInputStream().readAllBytes(),
                        StandardCharsets.UTF_8
                );

        tokenBucketScript = new DefaultRedisScript<>();
        tokenBucketScript.setScriptText(scriptText);
        tokenBucketScript.setResultType(List.class);
    }

    @Override
public RateLimitResult evaluate(
        RateLimitIdentifier identifier) {

    long capacity =
            identifier.capacity();

    long refillRate = 1;

    long now =
            System.currentTimeMillis();

    List<?> result =
            redisTemplate.execute(
                    tokenBucketScript,
                    Collections.singletonList(
                            identifier.key()
                    ),
                    String.valueOf(capacity),
                    String.valueOf(refillRate),
                    String.valueOf(now)
            );

    if (result == null || result.size() < 2) {

        return new RateLimitResult(
                false,
                0,
                0
        );
    }

    boolean allowed =
            ((Number) result.get(0))
                    .longValue() == 1;

    long remaining =
            ((Number) result.get(1))
                    .longValue();

    return new RateLimitResult(
            allowed,
            remaining,
            0
    );
}

    @Override
    public String getAlgorithmName() {
        return "token_bucket";
    }
}