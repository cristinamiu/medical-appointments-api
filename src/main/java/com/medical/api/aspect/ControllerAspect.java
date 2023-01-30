package com.medical.api.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAspect {
    @Autowired
    private ApplicationContext applicationContext;
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Before("execution(* com.medical.api.service.*.*.add*(..))")
    public void beforeAdvice(JoinPoint joinPoint)
    {
        logger.info("Attempting to add a new record for: " + joinPoint.getSignature().getName());
    }

    @Before("execution(* com.medical.api.service.*.*.get*(..))")
    public void beforeAdviceGet(JoinPoint joinPoint)
    {
        logger.info("Attempting to get record(s) for: " + joinPoint.getSignature().getName());
    }

    @Before("execution(* com.medical.api.service.*.*.delete*(..))")
    public void beforeAdviceDelete(JoinPoint joinPoint)
    {
        logger.info("Attempting to delete record for: " + joinPoint.getSignature().getName());
    }
}
