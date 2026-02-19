package com.hjq.http.model

enum class HttpMethod(private val mMethod: String) {
    GET("GET"),
    POST("POST"),
    HEAD("HEAD"),
    DELETE("DELETE"),
    PUT("PUT"),
    PATCH("PATCH"),
    OPTIONS("OPTIONS"),
    TRACE("TRACE");

    override fun toString(): String {
        return mMethod
    }
}
