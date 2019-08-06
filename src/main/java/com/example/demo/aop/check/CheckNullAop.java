package com.example.demo.aop.check;


import com.example.demo.exception.ExceptionEnum;
import com.example.demo.util.result.ResultUtil;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
@Log4j2
public class CheckNullAop {
    /**
     * 拦截方法上已经参数上的注解
     */
    @Pointcut("@annotation(CheckNull) || execution(* *(@CheckNull (*),..))")
    public void check() {
    }

    /**
     * 方法参数校验
     *
     * @param annotations    方法上的注解数组
     * @param args           方法的参数值列表
     * @param parameterNames 方法的参数名列表
     */
    private String checkMethod(Annotation[] annotations, Object[] args, String[] parameterNames) {

        //校验方法上的注解
        //考虑到有多个相同的注解
        for (Annotation annotation : annotations) {
            if (annotation instanceof CheckNull) {
                //先解析方法上的参数
                String result = resolveMethod((CheckNull) annotation, args, Arrays.asList(parameterNames));
                if (null != result)
                    return result;
            }
        }
        return null;
    }

    /**
     * 解析方法上的注解
     *
     * @param checkNull  注解
     * @param args       方法的参数值列表
     * @param parameters 方法的参数名列表
     */
    private String resolveMethod(CheckNull checkNull, Object[] args, List<String> parameters) {

        assert args.length == parameters.size();

        String[] list = checkNull.value().split(",");

        //直接遍历value
        for (String s : list) {
            int index = parameters.indexOf(s);
            if (-1 != index && null == args[index]) {
                return s;
            }
        }

        return null;
    }

    /**
     * 解析参数上的注解
     *
     * @param args       方法的参数值列表
     * @param parameters 参数的注解列表，为二维数组
     */
    private String resolveParam(Object[] args, Annotation[][] parameters) {

        for (int i = 0; i < parameters.length; ++i) {
            for (Annotation annotation : parameters[i]) {
                if (annotation instanceof CheckNull) {
                    CheckNull c = (CheckNull) annotation;
                    if (c.type() != Object.class) {
                        for (String s : c.value().split(",")) {
                            try {
                                //拿到不能访问的属性
                                //无法拿到父类属性
                                Field field = args[i].getClass().getDeclaredField(s);
                                if (!field.isAccessible()) field.setAccessible(true);
                                if (null == field.get(args[i])) {
                                    return s;
                                }
                            } catch (NoSuchFieldException | IllegalAccessException e) {
                                log.warn(e.getMessage());
                            }
                        }
                    }
                    break;
                }
            }
        }

        return null;
    }

    @Around(value = "check()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        //得到真实的方法对象
        Method realMethod = joinPoint.getTarget().getClass().getDeclaredMethod(joinPoint.getSignature().getName(),
                method.getParameterTypes());
        //拿到方法的注解
        Annotation[] annotations = realMethod.getDeclaredAnnotations();
        //得到参数列表
        Object[] args = joinPoint.getArgs();
        //得到参数名列表
        String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        //先校验方法上的注解
        String r1 = checkMethod(annotations, args, parameterNames);
        if (null == r1) {
            //再校验参数上的注解
            r1 = resolveParam(args, realMethod.getParameterAnnotations());
            if (null == r1) {
                return joinPoint.proceed();
            }
        }
        //适用于返回值为json
        return ResultUtil.error(ExceptionEnum.PARAMETER_NULL.getCode(), String.format(ExceptionEnum.PARAMETER_NULL.getMsg(), r1));
    }
}
