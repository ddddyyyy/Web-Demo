package com.example.demo.exception;

/**
 * 自定义的异常枚举
 *
 * @author MDY
 */
public enum ExceptionEnum {
    UNKNOWN_ERROR(-1, "未知错误"),
    USER_IS_EXIST(1, "用户已存在"),
    PASSWORD_ERROR_OVER(2, "密码错误超过三次,请60秒以后重试"),
    ACCOUNT_IS_LOCKED(3, "账户被封，请联系管理员"),
    ERROR_PASSWORD_OR_USER(4, "账号或密码错误"),
    IS_NOT_LOGIN(5, "用户未登陆"),
    LOGIN_IS_INVALID(6, "登陆失效，请重新登陆"),
    UNAUTHORIZED_EXCEPTION(7, "无访问权限"),
    USER_IS_NOT_EXIST(8, "用户不存在"),
    QRCODE_EXPRIE(9, "注册二维码过期了"),
    PASSWORD_ERROR(10, "密码错误"),
    FILE_ERROR(11, "文件过大"),
    DEPARTMENT_NEED_USER(12, "部门需要设置管理员"),
    VIDEO_GROUP_EXIST(13, "视频集已存在"),
    FILE_NOT_EXIST(14, "文件不存在"),
    FILE_NOT_READY(15, "文件正在转码"),
    FILE_NOT_READY_OR_NOT_EXIST(15, "文件正在转码或者不存在"),
    FILE_IS_NULL(16, "文件为空"),
    NULL_PARAMS_ERROR(20, "参数为空"),
    NEED_PAY(31, "需要付费"),
    CLONE_FAILURE(40, "对象克隆失败"),
    ERROR_METHOD(50, "请求方法错误"),
    PARAMETER_ERROR(60, "传入参数格式错误"),
    PARAMETER_NULL(61, "请求参数：%s为空");
    /**
     * 异常代号
     */
    private Integer code;
    /**
     * 异常信息
     */
    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
