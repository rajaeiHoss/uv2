package com.hjq.http.exception;

public final class UnknownException extends HttpException {
    public UnknownException(String str) {
        super(str);
    }

    public UnknownException(String str, Throwable th) {
        super(str, th);
    }
}
