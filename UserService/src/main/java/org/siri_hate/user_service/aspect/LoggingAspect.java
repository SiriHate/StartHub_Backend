package org.siri_hate.user_service.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

  @Pointcut("execution(* org.siri_hate.user_service.controller..*(..))")
  private void controllerMethods() {}

  @Pointcut("execution(* org.siri_hate.user_service.service..*(..))")
  private void serviceMethods() {}

  @Pointcut("execution(* org.siri_hate.user_service.repository..*(..))")
  private void repositoryMethods() {}

  @Around("controllerMethods()")
  public Object aroundAllControllerMethodsAdvice(ProceedingJoinPoint proceedingJoinPoint)
      throws Throwable {
    MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
    String methodName = methodSignature.getName();
    LOGGER.info("Controller method: {} - was called", methodName);
    Object targetMethodResult;
    try {
      targetMethodResult = proceedingJoinPoint.proceed();
      LOGGER.info("Controller method: {} - has completed", methodName);
    } catch (Throwable throwable) {
      LOGGER.error(
          "Exception occurred in controller method {}: {}", methodName, throwable.getMessage());
      throw throwable;
    }
    return targetMethodResult;
  }

  @Around("serviceMethods()")
  public Object aroundAllServiceMethodsAdvice(ProceedingJoinPoint proceedingJoinPoint)
      throws Throwable {
    MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
    String methodName = methodSignature.getName();
    LOGGER.info("Service method: {} - was called", methodName);
    Object targetMethodResult;
    try {
      targetMethodResult = proceedingJoinPoint.proceed();
      LOGGER.info("Service method: {} - has completed", methodName);
    } catch (Throwable throwable) {
      LOGGER.error(
          "Exception occurred in service method {}: {}", methodName, throwable.getMessage());
      throw throwable;
    }
    return targetMethodResult;
  }

  @Around("repositoryMethods()")
  public Object aroundAllRepositoryMethodsAdvice(ProceedingJoinPoint proceedingJoinPoint)
      throws Throwable {
    MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
    String methodName = methodSignature.getName();
    LOGGER.info("Repository method: {} - was called", methodName);
    Object targetMethodResult;
    try {
      targetMethodResult = proceedingJoinPoint.proceed();
      LOGGER.info("Repository method: {} - has completed", methodName);
    } catch (Throwable throwable) {
      LOGGER.error(
          "Exception occurred in repository method {}: {}", methodName, throwable.getMessage());
      throw throwable;
    }
    return targetMethodResult;
  }
}
