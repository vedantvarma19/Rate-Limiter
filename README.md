<<<<<<< HEAD
# Rate-Limiter
Production-ready Rate Limiter service built with Spring Boot, featuring Strategy Pattern architecture, Redis caching, configurable limits, low-latency request processing, and support for multiple rate-limiting algorithms.

A production-grade, extensible Rate Limiter built with Spring Boot and Strategy Pattern architecture to support multiple throttling algorithms with minimal code changes.

This description will look particularly strong if you're planning to implement:

Fixed Window Counter
Sliding Window Log
Sliding Window Counter
Token Bucket
Leaky Bucket
Redis-backed distributed rate limiting
Custom annotations (@RateLimit)
Spring AOP integration
=======
# Rate Limiter Application

A Spring Boot application for implementing rate limiting functionality.

## Prerequisites

- Java 17 or higher
- Maven 3.6.0 or higher

## Build

To build the project, run:

```bash
mvn clean install
```

## Run

To run the application, execute:

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## Project Structure

```
src/
├── main/
│   ├── java/com/example/rateLimiter/
│   │   └── RateLimiterApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/example/rateLimiter/
        └── RateLimiterApplicationTests.java
```

## Testing

To run tests, execute:

```bash
mvn test
```

## Dependencies

- Spring Boot Starter Web
- Spring Boot Starter Test

## License

This project is licensed under the MIT License.
>>>>>>> c1ba574 (Milestone 1: Implement strategy pattern architecture for rate limiting)
