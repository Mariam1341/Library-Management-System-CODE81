package com.code81.library.aop;

import com.code81.library.service.UserActivityLogService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
@Aspect
@Component
@RequiredArgsConstructor
public class UserActivityLoggingAspect {

    private final UserActivityLogService logService;
    @Pointcut("@annotation(com.code81.library.aop.LogActivity)")
    public void logActivityMethods() {}
    @Pointcut("execution(* com.code81.library.service.*.create*(..)) || " +
            "execution(* com.code81.library.service.*.update*(..)) || " +
            "execution(* com.code81.library.service.*.delete*(..))")
    public void defaultCrudOperations() {}

    @AfterReturning("defaultCrudOperations() || logActivityMethods()")
    public void logUserAction(JoinPoint joinPoint) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth != null ? auth.getName() : "SYSTEM";

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        LogActivity annotation = method.getAnnotation(LogActivity.class);

        String action;
        if (annotation != null && !annotation.action().isEmpty()) {
             action = annotation.action();
        } else {
            String methodName = signature.getName();
            String formatted = methodName
                    .replaceAll("([a-z])([A-Z]+)", "$1_$2")
                    .toUpperCase();
            action = formatted;
        }

        String details = String.format(
                "Action: %s | Class: %s | Method: %s | Time: %s",
                action,
                signature.getDeclaringType().getSimpleName(),
                signature.getName(),
                LocalDateTime.now()
        );

        logService.log(username, action, details);
    }

}
