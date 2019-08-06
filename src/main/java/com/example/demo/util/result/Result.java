package com.example.demo.util.result;

/**
 * 返回前端的数据格式
 *
 * @param <T>
 * @author MDY
 */
public class Result<T> {

    /**
     * 状态码，0表示成功
     */
    private Integer status;
    /**
     * 错误信息
     */
    private String msg;
    /**
     * 返回的数据
     */
    private T data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
