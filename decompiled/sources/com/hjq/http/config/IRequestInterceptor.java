package com.hjq.http.config;

import com.hjq.http.model.HttpHeaders;
import com.hjq.http.model.HttpParams;

public interface IRequestInterceptor {
    void interceptArguments(IRequestApi iRequestApi, HttpParams httpParams, HttpHeaders httpHeaders);
}
