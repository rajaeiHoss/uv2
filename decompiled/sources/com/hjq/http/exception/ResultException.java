package com.hjq.http.exception;

public final class ResultException extends HttpException {
    private final Object mData;

    public ResultException(String str, Object obj) {
        super(str);
        this.mData = obj;
    }

    public ResultException(String str, Throwable th, Object obj) {
        super(str, th);
        this.mData = obj;
    }

    public Object getData() {
        return this.mData;
    }
}
