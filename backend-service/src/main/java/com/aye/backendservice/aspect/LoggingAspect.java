package com.aye.backendservice.aspect;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("@annotation(com.aye.backendservice.aspect.annotation.EnableLogging)")
    public Object logControllerCall(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getName();
        String className = signature.getDeclaringType().getSimpleName();
        //String[] paramNames = signature.getParameterNames();
        //Object[] paramValues = joinPoint.getArgs();
        // HTTP request metadata
        String requestId = "N/A";
        ServletRequestAttributes attrs =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attrs != null) {
            HttpServletRequest request = attrs.getRequest();
            requestId = request.getRequestId();
        }
        //log.info("▶ method={} | class={} | requestId={} ", methodName, className, requestId);

        long start = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();

            long elapsed = System.currentTimeMillis() - start;

            log.info("✔ method={} | class={} | requestId={} | status=SUCCESS | executionTime={}ms", methodName, className, requestId, elapsed);

            return result;

        } catch (Exception ex) {

            long elapsed = System.currentTimeMillis() - start;

            log.warn("✘ method={} | class={} | requestId={} | status=BAD_REQUEST | reason={} | executionTime={}ms", methodName, className, requestId, ex.getMessage(), elapsed);

            throw ex;
        }
    }

    private String buildArgString(String[] paramNames, Object[] paramValues) {

        if (paramNames == null || paramValues == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < paramNames.length; i++) {

            if (i > 0) {
                sb.append(", ");
            }

            sb.append(paramNames[i])
                    .append("=")
                    .append(paramValues[i]);
        }

        return sb.toString();
    }
}