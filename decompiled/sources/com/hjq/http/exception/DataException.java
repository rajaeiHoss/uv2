package com.hjq.http.exception;

public final class DataException extends HttpException {
    public DataException(String str) {
        super(str);
    }

    public DataException(String str, Throwable th) {
        super(str, th);
    }
}
