package com.sofisticat.management.logging;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ApplicationLoggerAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(com.sofisticat.management.controllers..*)")
    public void definePackagePointcuts() {
        //empty method to name locations of points
    }

    @Pointcut("within(com.sofisticat.management.services..*)")
    public void servicePointcuts() {
        // service methods pointcut
    }

    @Before("definePackagePointcuts()")
    public void logBeforeController(JoinPoint joinPoint) {
        log.debug("\n\n\n");
        log.debug("******* Before Method Execution: ******** ");
        log.debug("{}.{} () with arguments[s] = {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
    }

    @After("definePackagePointcuts()")
    public void logAfterController() {
        log.debug(" -----  ");
    }


}
