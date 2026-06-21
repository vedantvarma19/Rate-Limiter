package com.vedant.rateLimiter.interceptor;

import com.vedant.rateLimiter.service.ApiKeyRateLimitService;
import com.vedant.rateLimiter.service.RateLimiterEngine;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.vedant.rateLimiter.dto.RateLimitIdentifier;
import com.vedant.rateLimiter.dto.RateLimitResult;
import com.vedant.rateLimiter.exception.RateLimitExceededException;

@Component
public class RateLimitInterceptor
        implements HandlerInterceptor {

    private final RateLimiterEngine engine;

    private final ApiKeyRateLimitService apiKeyService;

    public RateLimitInterceptor(
            RateLimiterEngine engine,
            ApiKeyRateLimitService apiKeyService) {

        this.engine = engine;
        this.apiKeyService = apiKeyService;
    }

    @Override
public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler) {

  String apiKey =
        request.getHeader("X-API-KEY");

if (apiKey == null || apiKey.isBlank()) {
    apiKey = "free-user";
}
long limit =
        apiKeyService.getCapacity(apiKey);
        RateLimitIdentifier identifier =
        new RateLimitIdentifier(
                apiKey,
                limit,
                60
        );

        RateLimitResult result =
        engine.evaluate(
                "token_bucket",
                identifier
        );

        if (!result.allowed()) {

    throw new RateLimitExceededException(
            "Rate limit exceeded"
    );
}

return true;

}
}