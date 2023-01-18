package com.vilgodskaia.movieplatformpetproject.config.aspect;

import com.vilgodskaia.movieplatformpetproject.util.DataType;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@Log4j2
public class MovieAop {

    private final String apiPointCut = "execution(com.vilgodskaia.movieplatformpetproject.api.movie.*.*(..))";

    @Pointcut(apiPointCut)
    public void logController() {
    }

    @Before("logController()")
    public void logRequest(JoinPoint joinPoint) {
        log.info(joinPoint.getSignature().getName());
        log.info(createJoinPointForLogs(joinPoint, DataType.REQUEST));
    }

    @AfterReturning("logController()")
    public void logResponse(JoinPoint joinPoint) {
        log.info(joinPoint.getSignature().getName());
        log.info(createJoinPointForLogs(joinPoint, DataType.RESPONSE));
    }

    private String createJoinPointForLogs(JoinPoint joinPoint, DataType dataType) {
        Object[] args = joinPoint.getArgs();
        if (args.length == 0) {
            return joinPoint.getSignature().getName().concat(" method does not have parameters.");
        }
        StringBuilder values = new StringBuilder();
        values.append(dataType.equals(DataType.REQUEST)
                ? "\\r\\n========== The request values =========="
                : "\\r\\n========== The response values ==========");
        Arrays.stream(args).forEach(arg -> {
            values.append("\r\n");
            values.append(arg.toString());
        });
        values.append("\r\n==================================================");
        return values.toString();
    }
}
