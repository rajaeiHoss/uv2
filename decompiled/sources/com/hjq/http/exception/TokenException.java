package com.hjq.http.exception;

public final class TokenException extends HttpException {
    public TokenException(String str) {
        super(str);
    }

    public TokenException(String str, Throwable th) {
        super(str, th);
    }
}
