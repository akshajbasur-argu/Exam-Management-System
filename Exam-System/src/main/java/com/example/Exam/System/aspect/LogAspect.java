package com.example.Exam.System.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;

@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(* com.example.Exam.System.service.impl..*(..))")
    public void aspectForService() {
    }

    @Pointcut("execution(* com.example.Exam.System.controller..*(..))")
    public void aspectForController() {
    }

    private static final ThreadLocal<Boolean> isLogging = ThreadLocal.withInitial(() -> false);

    @Around("aspectForService()")
    public Object logService(ProceedingJoinPoint joinPoint) throws Throwable {
        return logAround(joinPoint, "Service");
    }

    @Around("aspectForService()")
    public Object logController(ProceedingJoinPoint joinPoint) throws Throwable {
        return logAround(joinPoint, "Controller");
    }

    public Object logAround(ProceedingJoinPoint joinPoint, String name) throws Throwable {

        LocalTime startTime = LocalTime.now();
        isLogging.set(true);
        System.out.println("Before execution of " + name);


        Object result = joinPoint.proceed();

        LocalTime endTime = LocalTime.now();
        System.out.println("After execution of " + name);

        Duration duration = Duration.between(startTime, endTime);
        System.out.println("Total Execution time for " + name + " layer is :" + (float)duration.getNano()/1000000000 + " Seconds");
        isLogging.set(false);
        return result;
    }
}
