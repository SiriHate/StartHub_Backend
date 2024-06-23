package org.siri_hate.notification_service.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

/**
 * This class is responsible for logging the execution of methods in the controller, service, and repository layers.
 * It uses Aspect Oriented Programming (AOP) to achieve this.
 */
@Component
@Aspect
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * Pointcut that matches all methods in the controller layer.
     */
    @Pointcut("execution(* org.siri_hate.notification_service.controller..*(..))")
    private void controllerMethods() {}

    /**
     * Pointcut that matches all methods in the service layer.
     */
    @Pointcut("execution(* org.siri_hate.notification_service.service..*(..))")
    private void serviceMethods() {}

    /**
     * Pointcut that matches all methods in the repository layer.
     */
    @Pointcut("execution(* org.siri_hate.notification_service.repository..*(..))")
    private void repositoryMethods() {}

    /**
     * Advice that logs the execution of all methods in the controller layer.
     * It logs when a method is called and when it has completed.
     * If an exception occurs during the execution of the method, it logs the exception message.
     *
     * @param proceedingJoinPoint the join point at which the advice is applied
     * @return the result of the method execution
     * @throws Throwable if an exception occurs during the method execution
     */
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

    /**
     * Advice that logs the execution of all methods in the service layer.
     * It logs when a method is called and when it has completed.
     * If an exception occurs during the execution of the method, it logs the exception message.
     *
     * @param proceedingJoinPoint the join point at which the advice is applied
     * @return the result of the method execution
     * @throws Throwable if an exception occurs during the method execution
     */
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

    /**
     * Advice that logs the execution of all methods in the repository layer.
     * It logs when a method is called and when it has completed.
     * If an exception occurs during the execution of the method, it logs the exception message.
     *
     * @param proceedingJoinPoint the join point at which the advice is applied
     * @return the result of the method execution
     * @throws Throwable if an exception occurs during the method execution
     */
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