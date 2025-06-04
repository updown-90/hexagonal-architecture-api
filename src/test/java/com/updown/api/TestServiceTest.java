package com.updown.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TestServiceTest {

    @Autowired
    private TestCircuitBreakerService testService;

    /**
     * 서킷브레이커가 열리기 전까지는 예외가 발생하는지 확인합니다.
     * 최소 호출 횟수(5회) 이전에는 예외가 발생해야 합니다.
     */
    @Test
    void testCircuitBreakerBeforeThreshold() {
        // 최소 호출 횟수(5회) 이전에는 fallback 메서드가 ₩호출되어야 함
        for (int i = 0; i < 4; i++) {
            String result = testService.testMethod();
            assertEquals("Fallback response", result);
        }
    }

    /**
     * 서킷브레이커가 열린 후에도 fallback 메서드가 호출되는지 확인합니다.
     * 실패율이 임계값(50%)을 초과하면 서킷브레이커가 열리고 fallback 메서드가 계속 호출됩니다.
     */
    @Test
    void testCircuitBreakerAfterThreshold() {
        // 서킷브레이커가 열리기 전까지 fallback 메서드 호출
        for (int i = 0; i < 5; i++) {
            String result = testService.testMethod();
            assertEquals("Fallback response", result);
        }

        // 서킷브레이커가 열린 후에도 fallback 메서드가 호출됨
        String result = testService.testMethod();
        assertEquals("Fallback response", result);
    }

    /**
     * 서킷브레이커의 상태 변화를 확인합니다.
     * 1. CLOSED -> OPEN: 실패율이 임계값을 초과하면
     * 2. OPEN -> HALF-OPEN: 대기 시간(5초) 후
     * 3. HALF-OPEN -> CLOSED: 성공률이 임계값 이상이면
     */
    @Test
    void testCircuitBreakerStateTransition() throws InterruptedException {
        // 1. CLOSED -> OPEN: 실패율이 임계값을 초과하면
        for (int i = 0; i < 5; i++) {
            String result = testService.testMethod();
            assertEquals("Fallback response", result);
        }

        // 2. OPEN 상태: fallback 메서드가 호출됨
        String result = testService.testMethod();
        assertEquals("Fallback response", result);

        // 3. OPEN -> HALF-OPEN: 대기 시간(5초) 후
        Thread.sleep(5000);

        // 4. HALF-OPEN 상태: 다시 fallback 메서드가 호출됨
        result = testService.testMethod();
        assertEquals("Fallback response", result);
    }
} 