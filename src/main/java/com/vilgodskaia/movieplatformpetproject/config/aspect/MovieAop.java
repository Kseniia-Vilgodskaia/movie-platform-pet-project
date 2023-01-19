package com.vilgodskaia.movieplatformpetproject.config.aspect;

import com.vilgodskaia.movieplatformpetproject.util.DataType;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@Log4j2
public class MovieAop {

    private final String apiPointCutMovieController = "execution(* com.vilgodskaia.movieplatformpetproject.api.movie.MovieRestController.**(..))";
    private final String apiPointCutStreamingController = "execution(* com.vilgodskaia.movieplatformpetproject.api.streamingplatform.StreamingPlatformRestController.**(..))";
    private final String apiPointCutMovieOnStreamingController = "execution(* com.vilgodskaia.movieplatformpetproject.api.movieonstreamingplatform.MovieOnStreamingPlatformRestController.**(..))";

    private final String exceptionPointCut = "execution(* com.vilgodskaia.movieplatformpetproject.*.*.*(..))";

    @Pointcut(apiPointCutMovieController)
    public void logMovieController() {
    }

    @Pointcut(apiPointCutStreamingController)
    public void logStreamingController() {
    }

    @Pointcut(apiPointCutMovieOnStreamingController)
    public void logMovieOnStreamingController() {
    }

    @Pointcut("logMovieController() || logStreamingController() || logMovieOnStreamingController()")
    public void logAnyController() {
    }

    @Before("logAnyController()")
    public void logRequest(JoinPoint joinPoint) {
        log.info("METHOD: " + joinPoint.getSignature().getName());
        log.info(createJoinPointForLogs(joinPoint, DataType.REQUEST));
    }

    @AfterReturning("logAnyController()")
    public void logResponse(JoinPoint joinPoint) {
        log.info("METHOD: " + joinPoint.getSignature().getName());
        log.info(createJoinPointForLogs(joinPoint, DataType.RESPONSE));
    }

    @AfterThrowing(pointcut = exceptionPointCut, throwing = "exception")
    public void logExceptions(JoinPoint joinPoint, Throwable exception) {
        log.info("====================We have an exception here====================");
        log.info("METHOD: " + joinPoint.getSignature().getName());
        log.info(exception.getMessage());
        log.info("=================================================================");
    }

    private String createJoinPointForLogs(JoinPoint joinPoint, DataType dataType) {
        Object[] args = joinPoint.getArgs();
        if (args.length == 0) {
            return joinPoint.getSignature().getName().concat(" method does not have parameters.");
        }
        StringBuilder values = new StringBuilder();
        values.append(dataType.equals(DataType.REQUEST)
                ? "========== The request values =========="
                : "========== The response values ==========");
        Arrays.stream(args).forEach(arg -> {
            values.append("\r\n");
            values.append(arg.toString());
        });
        values.append("\r\n==================================================");
        return values.toString();
    }
}
