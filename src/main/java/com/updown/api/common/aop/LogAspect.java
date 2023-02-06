package com.updown.api.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//  @Aspect	AOP의 단위가 되는 횡단관심사를 의미
//  @Pointcut 여러 JoinPoint 중 실제 어드바이스를 적용할 곳을 선별하기 위한 표현식 -> 어떤 부분에 어떤 클래스들에 or 어떤 메소드에 적용할지
//  JoinPoint 횡단관심사가 실행될 지점 -> 말 그대로 실제 실행될(된) 지점
//  Advice	횡단관심사를 처리하는 부분, 특정 JoinPoint에서 실행되는 코드로서 before, after returing , after throwing, after , around advice가 있다
//      @Before : 메서드 실행 전에 실행하는 Advice
//      @After Returning : 메서드 정상 실행 후 실행하는 Advice
//      @After Throwing : 메서드 실행시 예외 발생시 실행하는 Advice
//      @After : 메서드 정상 실행 또는 예외 발생 상관없이 실행하는 Advice
//      @Around :  위 네가지 Advice를 모두 포함 , 모든 시점에서 실행할 수 있는 Advice
// Target Aspect 가 적용된 객체를 말한다
@Slf4j
@Aspect
@Component
public class LogAspect {

    // com.updown.api.account.presentation 패키지 하위 클래스들 전부 적용하겠다고 지점 설정
    @Pointcut("execution(* com.updown.api.account.presentation.*.*(..))")
    private void logging() {}

    // Logger Annotation이 지정되어 있는 곳에서 실행
    @Pointcut("@annotation(com.updown.api.common.aop.Logger)")
    private void annotationLogging() {}

    @Before("logging() || annotationLogging()")
    public void before() {
        log.info("Log Start!");
    }

    @After("logging() || annotationLogging()")
    public void after() {
        log.info("Log End!");
    }

}
