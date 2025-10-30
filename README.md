# Hexagonal Architecture API

헥사고날 아키텍처 기반의 Spring Boot API 프로젝트입니다.

## 🏗️ 아키텍처

이 프로젝트는 **헥사고날 아키텍처(Hexagonal Architecture)** 패턴을 적용하여 구현되었습니다.

### 핵심 원칙

- **의존성 역전**: 도메인이 인프라에 의존하지 않음
- **포트-어댑터 패턴**: 인터페이스와 구현체 분리
- **도메인 중심 설계**: 비즈니스 로직이 도메인 레이어에 집중
- **테스트 용이성**: 각 계층을 독립적으로 테스트 가능

### 패키지 구조

```
src/main/java/com/updown/api/
├── account/                          # 계정 바운디드 컨텍스트
│   ├── adapter/                      # 어댑터 레이어
│   │   ├── in/web/                   # 인바운드 어댑터 (웹 컨트롤러)
│   │   └── out/persistence/          # 아웃바운드 어댑터 (데이터베이스)
│   ├── application/                  # 애플리케이션 레이어
│   │   ├── port/
│   │   │   ├── in/                   # 인바운드 포트 (유스케이스)
│   │   │   └── out/                  # 아웃바운드 포트 (리포지토리)
│   │   └── service/                  # 유스케이스 구현체
│   └── domain/                       # 도메인 레이어
│       ├── Account.java              # 도메인 엔티티
│       └── vo/                       # 값 객체
├── department/                       # 부서 바운디드 컨텍스트
│   ├── adapter/
│   ├── application/
│   └── domain/
├── common/                           # 공통 모듈
│   └── exception/                    # 예외 처리
└── BuckPalApplication.java           # 메인 애플리케이션
```

## 🚀 기술 스택

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **H2 Database** (테스트용)
- **Lombok**
- **JUnit 5**
- **Mockito**

## 📋 주요 기능

### Account (계정)
- 계정 생성, 조회, 수정, 삭제
- 로그인 ID 중복 검사
- 계정 상태 관리 (정상, 정지, 삭제)
- 부서와의 연관관계

### Department (부서)
- 부서 생성, 조회, 수정, 삭제
- 부서명 중복 검사
- 계정과의 연관관계

## 🛠️ 실행 방법

### 1. 프로젝트 클론
```bash
git clone <repository-url>
cd hexagonal-architecture-api
```

### 2. 애플리케이션 실행
```bash
./gradlew bootRun
```

### 3. 테스트 실행
```bash
# 모든 테스트 실행
./gradlew test

# 특정 테스트 실행
./gradlew test --tests "AccountTest"

# 테스트 리포트 확인
open build/reports/tests/test/index.html
```

## 📚 API 문서

### Account API

#### 계정 생성
```http
POST /accounts
Content-Type: application/json

{
  "loginId": "testuser",
  "password": "password123",
  "accountName": "테스트사용자",
  "departmentId": 1
}
```

#### 계정 조회
```http
GET /accounts/{accountId}
```

#### 계정 수정
```http
PUT /accounts
Content-Type: application/json

{
  "accountId": 1,
  "accountName": "수정된이름"
}
```

#### 계정 삭제
```http
DELETE /accounts/{accountId}
```

### Department API

#### 부서 생성
```http
POST /departments
Content-Type: application/json

{
  "name": "개발팀"
}
```

#### 부서 조회
```http
GET /departments/{departmentId}
```

#### 부서 수정
```http
PUT /departments
Content-Type: application/json

{
  "departmentId": 1,
  "name": "수정된부서"
}
```

#### 부서 삭제
```http
DELETE /departments/{departmentId}
```

## 🧪 테스트

### 테스트 구조
- **도메인 테스트**: 순수 비즈니스 로직 테스트
- **애플리케이션 테스트**: 유스케이스 로직 테스트 (Mockito 사용)
- **어댑터 테스트**: 외부 시스템 연동 테스트

### 테스트 커버리지
- **총 60개 테스트** 모두 통과
- **Account**: 36개 테스트
- **Department**: 24개 테스트

### 테스트 실행 예시
```bash
# 도메인 테스트만 실행
./gradlew test --tests "*domain*"

# 애플리케이션 서비스 테스트만 실행
./gradlew test --tests "*service*"

# 어댑터 테스트만 실행
./gradlew test --tests "*adapter*"
```

## 🏛️ 아키텍처 패턴

### 1. 포트-어댑터 패턴
- **Port**: 인터페이스 (도메인과 외부 세계 간의 계약)
- **Adapter**: 포트의 구현체 (실제 외부 시스템과의 연동)

### 2. 계층별 책임
- **Domain**: 순수 비즈니스 로직, 외부 의존성 없음
- **Application**: 유스케이스 오케스트레이션
- **Infrastructure**: 외부 시스템 연동 (DB, 외부 API)
- **Presentation**: 사용자 인터페이스 (REST API)

### 3. 의존성 방향
```
Presentation → Application → Domain
     ↓              ↓
Infrastructure → Application
```

## 📖 참고 자료
- [wikibook/clean-architecture](https://github.com/wikibook/clean-architecture)
- [만들면서 배우는 클린 아키텍처](https://wikibook.co.kr/clean-architecture/)
- [Hexagonal Architecture with Java and Spring](https://reflectoring.io/spring-hexagonal/)

