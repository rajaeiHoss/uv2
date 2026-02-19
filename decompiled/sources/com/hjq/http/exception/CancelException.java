package com.hjq.http.exception;

public final class CancelException extends HttpException {
    public CancelException(String str) {
        super(str);
    }

    public CancelException(String str, Throwable th) {
        super(str, th);
    }
}
