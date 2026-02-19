package com.hjq.http.exception;

public final class NetworkException extends HttpException {
    public NetworkException(String str) {
        super(str);
    }

    public NetworkException(String str, Throwable th) {
        super(str, th);
    }
}
