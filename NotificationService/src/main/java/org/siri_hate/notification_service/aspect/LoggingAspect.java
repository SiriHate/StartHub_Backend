package org.siri_hate.notification_service.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

@Component
@Aspect
@Log4j2
public class LoggingAspect {

    // TODO *Исправить баг с Could not generate CGLIB subclass of class*

//    @Pointcut("execution(* org.siri_hate.notification_service.service.MailService.*(..))")
//    private void emailSender() {}
//
//    @Around("emailSender()")
//    public Object aroundAllControllerMethodsAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//
//        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
//        String methodName = methodSignature.getName();
//        log.info("Email sender method: {} - was called", methodName);
//
//        Object targetMethodResult;
//
//        try {
//            targetMethodResult = proceedingJoinPoint.proceed();
//            log.info("Email sender: {} - has completed successfully", methodName);
//        } catch (Throwable throwable) {
//            log.error("Exception occurred in email sender method {}: {}", methodName, throwable.getMessage());
//            throw throwable;
//        }
//
//        return targetMethodResult;
//    }

}
