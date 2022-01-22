package com.juegosolimpicos.audit;

import com.juegosolimpicos.services.IAuditService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Configuration
public class AuditAdvice extends AuditPoincut {

    private static Logger log = LoggerFactory.getLogger(AuditAdvice.class);

    @Autowired
    private IAuditService auditService;

    @AfterReturning(pointcut = "pointcutCreateOrActualizeService() && args(dto)",returning = "returnValue")
    public void createEntityAudit(Object dto,Object returnValue) throws Throwable {
        final String METHOD_NAME = "createEntityAudit(Object dto,Object returnValue)";
        log.info("STARTED: {}",METHOD_NAME);
        auditService.save(returnValue,AuditEnum.CREATE_ACTION);
        log.trace("FINISHED: {}",METHOD_NAME);
    }

    @Around("pointcutDeleteService() && args(id)")
    public void deleteEntityAudit(ProceedingJoinPoint pjp, Integer id) throws Throwable{
    String entity = pjp.getTarget().getClass().getSimpleName().split("(?=[A-Z])")[0];
    pjp.proceed();
    auditService.delete(id,entity,AuditEnum.DELETE_ACTION);
    }

}
