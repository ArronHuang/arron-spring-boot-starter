package com.arronhuang.boot.entity;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author ArronHuang
 * @date 2021/01/05
 */
@Data
public class R<T> {

    private Integer code;

    private String msg;

    private T data;

    public static <T> R ok(T data) {
        R r = byHttpStatus(HttpStatus.OK);
        r.setData(data);
        return r;
    }

    public static R byHttpStatus(HttpStatus httpStatus) {
        R r = new R();
        r.setCode(httpStatus.value());
        r.setMsg(httpStatus.getReasonPhrase());
        return r;
    }

    public static R byHttpStatus(HttpStatus httpStatus, String msg) {
        R r = byHttpStatus(httpStatus);
        r.setMsg(msg);
        return r;
    }

    public boolean isOk() {
        return this.code == HttpStatus.OK.value();
    }

}
