package com.example.demo.exception;


import com.example.demo.util.result.Result;
import com.example.demo.util.result.ResultUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * 自定义的异常全局处理类，可以捕捉RuntimeException类型的异常
 *
 * @author MDY
 */
@Log4j2
@RestControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * @param e 抛出的异常
     * @return 根据请求返回json数据
     */
    @ExceptionHandler(value = RuntimeException.class)
//    @ResponseStatus(HttpStatus.CONFLICT)
    public Object exceptionGet(Exception e) {
        //自定义的异常类
        MyException myException;
        //返回前端的数据格式
        Result<?> result;
        //判断异常是否属于已定义
        if (e instanceof MyException) {
            myException = (MyException) e;
            result = ResultUtil.error(myException.getCode(), myException.getMessage());
        }
        //其它在拦截器以及其它无法直接抛出异常的地方抛出的异常只能自己判断，类似于shiro的权限不足异常
        else if (e instanceof HttpRequestMethodNotSupportedException) {
            //不属于以上所有异常，按未定义异常处理
            result = ResultUtil.error(ExceptionEnum.ERROR_METHOD);
        } else {
            result = formatException(e);
        }
        return result;
    }


    private String getTrace(Exception e) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(stream));
        return stream.toString();
    }

    private Result formatException(Exception e) {
        Result<?> result;
        String error = getTrace(e);
        result = ResultUtil.error(-1, e.toString());
        log.error(error);
        return result;
    }

}
