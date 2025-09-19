package com.code81.library.aop;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) @Documented
public @interface LogActivity {
    String action() default "";
}