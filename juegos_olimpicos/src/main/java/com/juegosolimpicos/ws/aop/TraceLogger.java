package com.juegosolimpicos.ws.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Aspect
@Component
@Configuration
public class TraceLogger {

    @Pointcut("@within(com.juegosolimpicos.ws.aop.ILogeable)")
    public void loggerRestController() {
    }

    @Pointcut("within(com.juegosolimpicos.services..*)")
    public void loggerRestControllerPackage() {
    }

    @Around("loggerRestControllerPackage() && loggerRestController()")
    public Object  loggerRestController( final ProceedingJoinPoint joinPoint) throws Throwable{
         final var logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
         final var started = Instant.now();
         boolean isFinishedOK = Boolean.TRUE;
         try{
             return joinPoint.proceed();
             }catch (final Throwable e){
             isFinishedOK = Boolean.FALSE;
             throw e;
         } finally{
             logger.info("class: {},method: {},Process finished successfully: {},Started at: {},Finished at: {}",
                     joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName(),
                     isFinishedOK,started,Instant.now());
         }
    }



}