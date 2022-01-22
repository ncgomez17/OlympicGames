package com.juegosolimpicos.audit;

import org.aspectj.lang.annotation.Pointcut;

public abstract class AuditPoincut {

    @Pointcut("within(com.juegosolimpicos.services.impl.*)")
    public void servicesMethods(){
    }

    @Pointcut("servicesMethods() && execution(* *..save(..))")
    public void pointcutCreateOrActualizeService() {
    }

    @Pointcut("servicesMethods() && execution(* delete*(..))")
    public void pointcutDeleteService() {
    }
}
