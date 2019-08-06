package com.example.demo.exception;

/**
 * 自定义的异常类，便于异常的集中处理
 * @author MDY
 */
public class MyException extends RuntimeException {
	private static final long serialVersionUID = 1918670231295368652L;

	/**
	 * 异常代号
	 */
	private Integer code;

	/**
	 * 自定义异常
	 * @param message 异常信息
	 * @param code 异常代号
	 */
	public MyException(String message, Integer code) {
		super(message);
		this.code = code;
	}

	/**
	 * 利用枚举处理异常
	 * @param exceptionEnum 出现的已定义异常
	 */
	public MyException(ExceptionEnum exceptionEnum) {
		super(exceptionEnum.getMsg());
		this.code = exceptionEnum.getCode();
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}
