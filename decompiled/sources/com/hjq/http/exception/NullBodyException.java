package com.hjq.http.exception;

public final class NullBodyException extends HttpException {
    public NullBodyException(String str) {
        super(str);
    }

    public NullBodyException(String str, Throwable th) {
        super(str, th);
    }
}
