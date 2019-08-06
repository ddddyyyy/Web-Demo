package com.example.demo.util.result;


import com.example.demo.exception.ExceptionEnum;
import com.example.demo.exception.MyException;

/**
 * 对传回前端的数据处理的工具类
 * @author MDY
 */
public class ResultUtil {
	
	/**
	 * 泛型方法
	 * @param object 传入的数据
	 * @return 前端规定的数据
	 */
	public static <T> Result<T> success(T object) {
		Result<T> result = new Result<>();
		result.setData(object);
		result.setMsg("success");
		result.setStatus(0);
		return result;
	}
	
	/**
	 * @param code 异常代码
	 * @param msg 异常信息
	 */
	public static <T> Result<T> error(Integer code, String msg) {
		Result<T> result = new Result<>();
		result.setStatus(code);
		result.setMsg(msg);
		result.setData(null);
		return result;
	}

	/**
	 * @param myException 自定义的异常类
	 */
	public static <T> Result<T> error(MyException myException) {
		Result<T> result = new Result<>();
		result.setStatus(myException.getCode());
		result.setMsg(myException.getMessage());
		result.setData(null);
		return result;
	}

	/**
	 * 返回异常信息，在已知的范围内
	 * @param exceptionEnum 自定义的异常枚举常量
	 */
	public static <T> Result<T> error(ExceptionEnum exceptionEnum) {
		Result<T> result = new Result<>();
		result.setStatus(exceptionEnum.getCode());
		result.setMsg(exceptionEnum.getMsg());
		result.setData(null);
		return result;
	}
	
}
