package com.example.demo.aop.check;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 检验参数是否为空
 * 在方法上的注解只是校验value里面的参数是否为空
 * 在参数上的注解需要指定参数类型，才能对参数的成员进行校验
 */
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckNull {
    Class<?> type() default Object.class;

    String value() default "";
}
