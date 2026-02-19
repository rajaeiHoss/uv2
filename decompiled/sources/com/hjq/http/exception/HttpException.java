package com.hjq.http.exception;

public class HttpException extends Exception {
    private final String mMessage;

    public HttpException(String str) {
        super(str);
        this.mMessage = str;
    }

    public HttpException(String str, Throwable th) {
        super(str, th);
        this.mMessage = str;
    }

    public String getMessage() {
        return this.mMessage;
    }
}
