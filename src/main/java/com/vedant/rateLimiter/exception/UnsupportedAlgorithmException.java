package com.vedant.rateLimiter.exception;


public class UnsupportedAlgorithmException extends RuntimeException {

    public UnsupportedAlgorithmException(String algorithm) {
        super("Unsupported rate limiting algorithm: " + algorithm);
    }
}