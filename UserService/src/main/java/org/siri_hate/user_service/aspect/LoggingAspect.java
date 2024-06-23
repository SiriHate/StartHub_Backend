package org.siri_hate.user_service.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Aspect class for logging.
 * This class uses AspectJ annotations to define advice methods that are executed before and after the execution of methods in the controller, service, and repository layers.
 * It uses the @Aspect and @Component annotations to be detected by Spring AOP and to be managed as a Spring bean.
 */
@Component
@Aspect
public class LoggingAspect {

    // Logger instance for the LoggingAspect class
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * Pointcut that matches all methods in the controller layer.
     */
    @Pointcut("execution(* org.siri_hate.user_service.controller..*(..))")
    private void controllerMethods() {}

    /**
     * Pointcut that matches all methods in the controller layer.
     */
    @Pointcut("execution(* org.siri_hate.user_service.service..*(..))")
    private void serviceMethods() {}

    /**
     * Pointcut that matches all methods in the repository layer.
     */
    @Pointcut("execution(* org.siri_hate.user_service.repository..*(..))")
    private void repositoryMethods() {}

    /**
     * Advice method that logs information before and after the execution of all methods in the controller layer.
     *
     * @param proceedingJoinPoint the ProceedingJoinPoint object that allows the advice method to proceed with the target method's execution
     * @return the result of the target method's execution
     * @throws Throwable if an exception occurs during the target method's execution
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
     * Advice that logs the execution of all methods in the controller layer.
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
