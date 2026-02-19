package com.streamax.client.http.model;

public class HttpData<T> {
    private int code;
    private T data;
    private String msg;

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.msg;
    }

    public T getData() {
        return this.data;
    }

    public boolean isRequestSucceed() {
        return this.code == 200;
    }

    public boolean isTokenFailure() {
        return this.code == 1001;
    }
}
