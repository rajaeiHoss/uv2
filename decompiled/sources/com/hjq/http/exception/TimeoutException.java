package com.hjq.http.exception;

public final class TimeoutException extends HttpException {
    public TimeoutException(String str) {
        super(str);
    }

    public TimeoutException(String str, Throwable th) {
        super(str, th);
    }
}
