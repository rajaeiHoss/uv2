package com.hjq.http.model;

public enum HttpMethod {
    GET("GET"),
    POST("POST"),
    HEAD("HEAD"),
    DELETE("DELETE"),
    PUT("PUT"),
    PATCH("PATCH"),
    OPTIONS("OPTIONS"),
    TRACE("TRACE");
    
    private final String mMethod;

    private HttpMethod(String str) {
        this.mMethod = str;
    }

    public String toString() {
        return this.mMethod;
    }
}
