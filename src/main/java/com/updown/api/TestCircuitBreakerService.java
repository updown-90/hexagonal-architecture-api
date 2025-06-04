package com.updown.api;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class TestCircuitBreakerService {
    
    /**
     * 서킷브레이커 테스트를 위한 메서드
     * 항상 예외를 발생시킵니다.
     */
    @CircuitBreaker(name = "testCircuitBreaker", fallbackMethod = "fallbackMethod")
    public String testMethod() {
        System.out.println("!!! testMethod 실행됨"); // 또는 log.info
        throw new RuntimeException("Test exception");
    }

    /**
     * 서킷브레이커가 열렸을 때 호출되는 fallback 메서드
     */
    public String fallbackMethod(Exception e) {
        System.out.println("@@@ fallbackMethod 호출됨: " + e.getMessage());
        return "Fallback response";
    }
} 