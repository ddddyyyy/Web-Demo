package com.example.demo.aop;


import com.alibaba.fastjson.JSON;
import com.example.demo.util.result.Result;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 日志打印的切口类
 * 监听控制层
 *
 * @author MDY
 */
@Aspect
@Component
@Log4j2
public class HttpAop {

    @Pointcut("execution(public * com.example.demo.controller.*.*(..))")
    public void log() {
    }

    /**
     * 控制器执行之前打印日志
     *
     * @param joinPoint 切入点的执行点
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        log.info("-----------------------request log start-----------------------");
        //url
        log.info("url={}", request.getRequestURL());
        //method
        log.info("method={}", request.getMethod());
        //ip
        log.info("id={}", request.getRemoteAddr());
        //class_method
        log.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "," + joinPoint.getSignature().getName());
        // 获取入参的时候，args还包含了一些其他的内容，比如ServletRequest等，
        // 而这些入参并不能进行序列化，
        // 所以会报java.lang.IllegalStateException: UT010018: Async not started的错；
        Object[] args = joinPoint.getArgs();
        //过滤得到不是下述参数类型的参数
        log.info("args={}", JSON.toJSONString(Arrays.stream(args).filter(i -> !(i instanceof ServletRequest || i instanceof ServletResponse || i instanceof MultipartFile || i instanceof MultipartFile[])).collect(Collectors.toList())));
        log.info("-----------------------request log end-----------------------");
    }

    /**
     * 控制器执行后打印日志
     *
     * @param object 执行完后的返回值
     */
    @AfterReturning(pointcut = "log()", returning = "object")
    public void doAfterReturning(JoinPoint joinPoint, Object object) {
        log.info("++++++++++++++++response log start++++++++++++++++");
        if (object != null && !(object instanceof BufferedImage)) {
            if (object instanceof Result) {
                //未知异常，则输出上下文信息
                if (((Result) object).getStatus() == -1) {
                    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                    assert attributes != null;
                    HttpServletRequest request = attributes.getRequest();
                    log.error("-----------------------request log start-----------------------");
                    //url
                    log.error("url={}", request.getRequestURL());
                    //method
                    log.error("method={}", request.getMethod());
                    //ip
                    log.error("id={}", request.getRemoteAddr());
                    //class_method
                    log.error("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "," + joinPoint.getSignature().getName());
                    // 获取入参的时候，args还包含了一些其他的内容，比如ServletRequest等，
                    // 而这些入参并不能进行序列化，
                    // 所以会报java.lang.IllegalStateException: UT010018: Async not started的错；
                    Object[] args = joinPoint.getArgs();
                    //过滤得到不是下述参数类型的参数
                    log.error("args={}", JSON.toJSONString(Arrays.stream(args).filter(i -> !(i instanceof ServletRequest || i instanceof ServletResponse || i instanceof MultipartFile || i instanceof MultipartFile[])).collect(Collectors.toList())));
                    log.error("-----------------------request log end-----------------------");
                }
            }
            log.info("response={}", JSON.toJSONString(object));
        } else {
            log.info("response is none");
        }
        log.info("++++++++++++++++response log end++++++++++++++++");
    }
}
