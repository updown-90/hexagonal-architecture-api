package com.updown.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * UpDown API 애플리케이션 메인 클래스
 * 헥사고날 아키텍처 기반의 Spring Boot 애플리케이션
 */
@SpringBootApplication
public class HexagonalArchitectureApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HexagonalArchitectureApiApplication.class, args);
    }
}
