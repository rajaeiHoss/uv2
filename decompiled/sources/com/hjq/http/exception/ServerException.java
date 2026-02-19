package com.hjq.http.exception;

public final class ServerException extends HttpException {
    public ServerException(String str) {
        super(str);
    }

    public ServerException(String str, Throwable th) {
        super(str, th);
    }
}
