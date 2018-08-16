package com.wp.myboot.baseEntity;

public class NetResult<DataType> {

    private int code;//错误码 0:请求成功
    private String msg;//消息
    private DataType data;//数据体

    public NetResult() {
    }

    public NetResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public NetResult(int code, String msg, DataType data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataType getData() {
        return data;
    }

    public void setData(DataType data) {
        this.data = data;

    }
}