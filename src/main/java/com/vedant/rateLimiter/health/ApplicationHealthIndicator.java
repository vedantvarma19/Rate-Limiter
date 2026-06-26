package com.vedant.rateLimiter.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ApplicationHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {

        return Health.up()
                .withDetail(
                        "message",
                        "Rate Limiter Application is running successfully"
                )
                .withDetail(
                        "version",
                        "1.0"
                )
                .withDetail(
                        "algorithm",
                        "Sliding Window + Token Bucket"
                )
                .build();
    }
}