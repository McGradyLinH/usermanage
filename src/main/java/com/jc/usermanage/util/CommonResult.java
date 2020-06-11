package com.jc.usermanage.util;

/**
 * 统一的返回对象
 * @author lx
 * @version 1.0
 * @date 2020/6/11 23:08
 */
public class CommonResult<T> {
    /**
     * 状态码 like 4XX 5XX 200
     */
    private Integer code;
    /**
     * 描述
     */
    private String message;
    /**
     * 返回的数据
     */
    private T data;

    public CommonResult() {
    }

    public CommonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
