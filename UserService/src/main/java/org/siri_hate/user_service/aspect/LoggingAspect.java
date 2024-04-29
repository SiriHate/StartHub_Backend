package org.siri_hate.user_service.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Log4j2
public class LoggingAspect {

    @Pointcut("execution(* org.siri_hate.user_service.controller..*(..))")
    private void controllerMethods() {}

    @Pointcut("execution(* org.siri_hate.user_service.service..*(..))")
    private void serviceMethods() {}

    @Pointcut("execution(* org.siri_hate.user_service.repository..*(..))")
    private void repositoryMethods() {}


    @Around("controllerMethods()")
    public Object aroundAllControllerMethodsAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String methodName = methodSignature.getName();
        log.info("Controller method: {} - was called", methodName);

        Object targetMethodResult;
        try {
            targetMethodResult = proceedingJoinPoint.proceed();
            log.info("Controller method: {} - has completed", methodName);
        } catch (Throwable throwable) {
            log.error("Exception occurred in controller method {}: {}", methodName, throwable.getMessage());
            throw throwable;
        }

        return targetMethodResult;
    }

    @Around("serviceMethods()")
    public Object aroundAllServiceMethodsAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String methodName = methodSignature.getName();
        log.info("Service method: {} - was called", methodName);

        Object targetMethodResult;
        try {
            targetMethodResult = proceedingJoinPoint.proceed();
            log.info("Service method: {} - has completed", methodName);
        } catch (Throwable throwable) {
            log.error("Exception occurred in service method {}: {}", methodName, throwable.getMessage());
            throw throwable;
        }

        return targetMethodResult;
    }

    @Around("repositoryMethods()")
    public Object aroundAllRepositoryMethodsAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String methodName = methodSignature.getName();
        log.info("Repository method: {} - was called", methodName);

        Object targetMethodResult;
        try {
            targetMethodResult = proceedingJoinPoint.proceed();
            log.info("Repository method: {} - has completed", methodName);
        } catch (Throwable throwable) {
            log.error("Exception occurred in repository method {}: {}", methodName, throwable.getMessage());
            throw throwable;
        }

        return targetMethodResult;
    }

}
