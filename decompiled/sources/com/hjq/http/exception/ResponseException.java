package com.hjq.http.exception;

import okhttp3.Response;

public final class ResponseException extends HttpException {
    private final Response mResponse;

    public ResponseException(String str, Response response) {
        super(str);
        this.mResponse = response;
    }

    public ResponseException(String str, Throwable th, Response response) {
        super(str, th);
        this.mResponse = response;
    }

    public Response getResponse() {
        return this.mResponse;
    }
}
