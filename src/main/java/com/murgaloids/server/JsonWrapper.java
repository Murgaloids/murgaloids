package com.murgaloids.server;

public class JsonWrapper<T> {
    public final T data;

    public JsonWrapper(T data) {
        this.data = data;
    }
}
