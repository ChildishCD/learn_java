package com.javasm.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class ServerResponse<T> implements Serializable {

    private static final long serialVersionUID = -3142991941101495015L;
    private String message;
    private Integer code;

    private T data;//dao方法的返回值类型


    private ServerResponse() {
    }

    private ServerResponse(String message, Integer code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    private ServerResponse(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public static <T> ServerResponse<T> success(T data) {
        return new ServerResponse<>(CodeEnum.SUCCESS.getMsg(), CodeEnum.SUCCESS.getCode(), data);
    }

    public static <T> ServerResponse<T> success() {
        return new ServerResponse<>(CodeEnum.SUCCESS.getMsg(), CodeEnum.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> success(String msg) {
        return new ServerResponse<>(msg, CodeEnum.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> success(String msg, T data) {
        return new ServerResponse<>(msg, CodeEnum.SUCCESS.getCode(), data);
    }

    public static <T> ServerResponse<T> success(String msg, int code, T data) {

        return new ServerResponse<>(msg, code, data);
    }

    //失败

    public static <T> ServerResponse<T> error() {
        return new ServerResponse<>(CodeEnum.ERROR.getMsg(), CodeEnum.ERROR.getCode());
    }

    public static <T> ServerResponse<T> error(String msg) {
        return new ServerResponse<>(msg, CodeEnum.ERROR.getCode());
    }

    public static <T> ServerResponse<T> error(String msg, int code) {
        return new ServerResponse<>(msg, code);
    }


}
