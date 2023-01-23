package com.vilgodskaia.movieplatformpetproject.config.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@Log4j2
public class MovieAop {
    private final String exceptionPointCut = "execution(* com.vilgodskaia.movieplatformpetproject.*.*.*(..))";
    private final String restControllerPointCut = "within(@org.springframework.web.bind.annotation.RestController *)";

    @Before(restControllerPointCut)
    public void logRequest(JoinPoint joinPoint) {
        log.info("METHOD: " + joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        if (args.length == 0) {
            log.info("Method does not have parameters.");
        } else {
            StringBuilder values = new StringBuilder();
            values.append("========== The request values ==========");
            Arrays.stream(args).forEach(arg -> {
                values.append("\r\n");
                values.append(arg.toString());
            });
            values.append("\r\n==================================================");
            log.info(values.toString());
        }
    }

    @AfterReturning(value = restControllerPointCut, returning = "returnValue")
    public void logResponse(JoinPoint joinPoint, Object returnValue) {
        log.info("METHOD: " + joinPoint.getSignature().getName());
        log.info("========== The response values ==========");
        log.info(returnValue.toString());
    }

    @AfterThrowing(pointcut = exceptionPointCut, throwing = "exception")
    public void logExceptions(JoinPoint joinPoint, Throwable exception) {
        log.info("====================We have an exception here====================");
        log.info("METHOD: " + joinPoint.getSignature().getName());
        log.info(exception.getMessage());
        log.info("=================================================================");
    }
}
