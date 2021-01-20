package com.arronhuang.boot.entity;

/**
 * @author ArronHuang
 * @date 2021/01/20
 */
public abstract class BaseRequest<T> {

    public abstract T buildEntity();

}
