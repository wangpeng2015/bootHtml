package com.boot.commons.utils;

import java.io.Serializable;

/**
 *
 * @param <T>
 */
public class ResponseEntity<T> implements Serializable {
    private static final long serialVersionUID = -750644833749014618L;
    private T data;
    private String code;
    private String msg;
    private Boolean isSuccess;

    public void setCode(String code) {
        this.code = code;
    }

    public static long getSerialVersionUID() {
        return -750644833749014618L;
    }

    public Boolean getIsSuccess() {
        return this.isSuccess;
    }

    public void setIsSuccess(Boolean success) {
        this.isSuccess = success;
    }

    public String getCode() {
        return this.code;
    }

    public ResponseEntity() {
        this.code = "0";
        this.msg = "请求成功";
        this.setIsSuccess(true);
    }

    public ResponseEntity(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static <T> ResponseEntity<T> ok(T data) {
        ResponseEntity resp = new ResponseEntity();
        resp.setCode("0");
        resp.setData(data);
        resp.setIsSuccess(true);
        return resp;
    }

    public static <T> ResponseEntity<T> fail(String code, String error) {
        ResponseEntity resp = new ResponseEntity();
        resp.setCode(code);
        resp.setMsg(error);
        resp.setIsSuccess(false);
        return resp;
    }
}