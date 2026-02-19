package com.hjq.http.config;

import com.hjq.http.annotation.HttpIgnore;

public final class RequestApi implements IRequestApi {
    @HttpIgnore
    private final String mApi;

    public RequestApi(String str) {
        this.mApi = str;
    }

    public String getApi() {
        return this.mApi;
    }

    public String toString() {
        return this.mApi;
    }
}
