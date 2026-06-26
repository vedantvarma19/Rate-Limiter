# 🚀 Distributed Rate Limiter API

<div align="center">

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot)
![Redis](https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Lua](https://img.shields.io/badge/Lua-000080?style=for-the-badge&logo=lua)

Production-inspired distributed Rate Limiter built using Spring Boot, Redis, Lua Scripts, and the Strategy Design Pattern. The application supports multiple rate-limiting algorithms while maintaining clean architecture, scalability, and high performance.

</div>

---

# 📖 Overview

Modern applications expose APIs that are vulnerable to abuse, excessive traffic, and denial-of-service attacks. A rate limiter protects backend services by restricting how many requests a client can make within a specified time window.

This project demonstrates how production systems implement distributed rate limiting using Redis for shared state, Lua scripts for atomic operations, and the Strategy Design Pattern for supporting multiple algorithms without changing the core application.

The architecture is modular, extensible, and designed with clean software engineering principles.

---

# ✨ Features

## 🚦 Multiple Rate Limiting Algorithms

- Sliding Window Rate Limiter
- Token Bucket Rate Limiter
- Easily extensible for additional algorithms

---

## ⚡ Distributed Rate Limiting

- Redis as centralized storage
- Consistent limits across multiple application instances
- Suitable for horizontally scalable deployments

---

## 🧠 Atomic Operations with Lua

- Redis Lua Scripts
- Atomic execution
- Race-condition prevention
- High-performance request processing

---

## 🎯 Strategy Pattern

Each rate-limiting algorithm is implemented independently using the Strategy Pattern.

Benefits include:

- Open/Closed Principle
- Easy algorithm switching
- Better maintainability
- Cleaner architecture

---

## 🔑 Flexible Identification

Supports limiting requests based on:

- Client IP
- API Key
- Custom Identifiers

---

## ⚙ Configurable Limits

Different request capacities can be configured for different users or API keys.

Example:

| API Key | Capacity |
|----------|----------|
| Free User | 10 Requests |
| Premium User | 100 Requests |
| Enterprise | 1000 Requests |

---

## 📦 REST API

Example endpoints:

```
GET /api/test
GET /api/token
```

Returns

- Request Allowed
- Remaining Requests
- Retry Time
- Current Limit Status

---

# 🛠 Tech Stack

## Backend

- Java 17
- Spring Boot 3
- Spring Web

## Storage

- Redis

## Infrastructure

- Docker
- Docker Desktop

## Scripting

- Lua

## Testing

- Postman

---

# 🏗 Architecture

```
              Client
                 │
                 ▼
         Spring Boot REST API
                 │
         RateLimiterEngine
                 │
       Strategy Pattern
        ┌────────┴────────┐
        │                 │
        ▼                 ▼
 Sliding Window      Token Bucket
        │                 │
        └────────┬────────┘
                 ▼
          Redis + Lua Script
                 │
                 ▼
          Atomic Operations
```

---

# 📂 Project Structure

```
src
 ├── config
 ├── controller
 ├── dto
 ├── exception
 ├── interceptor
 ├── service
 ├── strategy
 │      ├── SlidingWindowStrategy
 │      ├── TokenBucketStrategy
 │      └── RateLimiterStrategy
 ├── lua
 │      ├── sliding_window.lua
 │      └── token_bucket.lua
 └── application.properties
```

---

# 🔥 Algorithms Implemented

## 1. Sliding Window

Tracks timestamps of incoming requests inside Redis Sorted Sets.

Advantages

- Smooth request distribution
- Accurate limiting
- Production-friendly

---

## 2. Token Bucket

Maintains a bucket that refills tokens over time.

Advantages

- Allows short traffic bursts
- Prevents continuous abuse
- Commonly used by cloud providers

---

# ⚡ Why Redis?

Redis was selected because it offers:

- In-memory performance
- Millisecond latency
- Atomic Lua execution
- Distributed synchronization
- Excellent scalability

---

# 🔐 Key Design Principles

- Strategy Design Pattern
- SOLID Principles
- Open Closed Principle
- Dependency Injection
- Clean Architecture
- Separation of Concerns

---

# 🚀 Installation

## Clone Repository

```bash
git clone https://github.com/YOUR_USERNAME/distributed-rate-limiter.git
```

---

## Start Redis

```bash
docker run -d \
-p 6379:6379 \
--name redis-rate-limiter \
redis
```

---

## Install Dependencies

```bash
mvn clean install
```

---

## Run Application

```bash
mvn spring-boot:run
```

---

Server

```
http://localhost:8080
```

---

# 🧪 Testing

Use Postman or cURL.

Example

```http
GET /api/test
```

Repeated requests will eventually return a rate-limited response once the configured threshold is exceeded.

---

# 📊 Future Improvements

- Fixed Window Algorithm
- Leaky Bucket Algorithm
- Redis Cluster Support
- JWT User-Based Rate Limiting
- Dynamic Configuration
- Prometheus Metrics
- Grafana Dashboard
- Kubernetes Deployment
- Spring Cloud Gateway Integration
- Distributed Microservice Support

---

# 📚 What I Learned

Through this project, I gained hands-on experience with:

- Distributed Systems
- API Rate Limiting
- Redis Data Structures
- Lua Scripting
- Strategy Design Pattern
- Spring Boot Architecture
- Docker
- Backend Performance Optimization
- REST API Development
- Clean Code Practices
- SOLID Principles

---

# 👨‍💻 Author

**Vedant Varma**

B.Tech – Information Science Engineering

Passionate about Backend Development, Distributed Systems, and System Design.

GitHub:
https://github.com/vedantvarma19

LinkedIn:
https://www.linkedin.com/in/vedant-varma

---

# ⭐ Support

If you found this project useful,

⭐ Star the repository

🍴 Fork it

💡 Share your feedback
