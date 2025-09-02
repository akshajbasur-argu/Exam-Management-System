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

    @Pointcut("execution(* com.example.Exam.System.service.impl.ProfessorServiceImpl.*(..))")
    public void aspectTry() {
    }
    private static final ThreadLocal<Boolean> isLogging = ThreadLocal.withInitial(()->false);

    @Around("aspectTry()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        LocalTime startTime = LocalTime.now();
        System.out.println();
        Object result = null;
        if(isLogging.get()){
            System.out.println("inside log if");
            return joinPoint.proceed();
        }
        isLogging.set(true);
        try {
            return joinPoint.proceed();
        }
        finally {
            LocalTime endTime = LocalTime.now();
            Duration duration = Duration.between(startTime,endTime);
            System.out.println("Total Execution time = "+ duration);
            isLogging.set(false);
        }

        }
}
