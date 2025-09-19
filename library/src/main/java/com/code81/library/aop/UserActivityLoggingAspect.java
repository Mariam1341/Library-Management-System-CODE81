package com.code81.library.aop;

import com.code81.library.service.UserActivityLogService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class UserActivityLoggingAspect {

    private final UserActivityLogService logService;

    // ده بيلقط أي ميثود متعلمة بـ @LogActivity
    @Pointcut("@annotation(logActivity)")
    public void logActivityMethods(LogActivity logActivity) {}

    // وده بيلقط create/update/delete/borrow/return
    @Pointcut("execution(* com.code81.library.service.*.create*(..)) || " +
            "execution(* com.code81.library.service.*.update*(..)) || " +
            "execution(* com.code81.library.service.*.delete*(..)) || " )
    public void defaultCrudOperations() {}

    @AfterReturning("defaultCrudOperations() || logActivityMethods(logActivity)")
    public void logUserAction(JoinPoint joinPoint, LogActivity logActivity) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth != null ? auth.getName() : "SYSTEM";

        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        String action = logActivity != null && !logActivity.action().isEmpty()
                ? logActivity.action()
                : methodName.toUpperCase();

        String details = String.format(
                "Action: %s | Class: %s | Method: %s | Time: %s",
                action, className, methodName, LocalDateTime.now()
        );

        logService.log(username, action, details);
    }
}