package com.example.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

  @Pointcut("execution (* com.example.aop.service..*(..))")
  public void pointcut() {

  }

  @Pointcut("execution (* com.example.aop.service..*(..))")
  public void pointcutWithReturned() {

  }

  // Run: 2nd
  @Order(1)
  @Before("pointcut()")
  public void before(JoinPoint point) {
    System.out.println("ASPECT: Before " + point.getSignature().getName() + "\tARGS: " + point.getArgs().toString());
  }

  // Run: 3rd
  @Order(2)
  @Before("pointcut()")
  public void beforeSecond(JoinPoint point) {
    System.out.println("ASPECT: Before " + point.getSignature().getName() + "\tThe second before()");
  }

  // Run: 4th
  @After("pointcut()")
  public void after(JoinPoint point) {
    System.out
        .println("ASPECT: After " + point.getSignature().getName() + "\tSIGN: " + point.getSignature().toLongString());
  }

  // Run: 5th
  @AfterReturning(value = "pointcutWithReturned()", returning = "returned")
  public void afterReturning(JoinPoint point, Object returned) {
    System.out.println("ASPECT: Return " + point.getSignature().getName() + "\tRETURN: " + returned);
  }

  // Run: 1st
  @Around("pointcut()")
  public Object around(ProceedingJoinPoint point) throws Throwable {
    System.out.println("ASPECT: Around " + point.getSignature().getName() + "\tARGS: " + point.getArgs().toString());
    Object result = point.proceed();
    System.out.println("ASPECT: Around " + point.getSignature().getName() + "\tRETURN: " + result);
    return result;
  }

  @AfterThrowing("pointcut()")
  public void afterThrowing(JoinPoint point) {
    System.out.println("ASPECT: Throw " + point.getSignature().getName());
  }

}
