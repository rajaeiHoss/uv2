package org.ksoap2.transport;

import java.io.IOException;

public class HttpResponseException extends IOException {
    private int statusCode;

    public HttpResponseException(int i) {
        this.statusCode = i;
    }

    public HttpResponseException(String str, int i) {
        super(str);
        this.statusCode = i;
    }

    public HttpResponseException(String str, Throwable th, int i) {
        super(str, th);
        this.statusCode = i;
    }

    public HttpResponseException(Throwable th, int i) {
        super(th);
        this.statusCode = i;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}
