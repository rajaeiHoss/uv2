package com.hjq.http.config

import com.hjq.http.model.HttpHeaders
import com.hjq.http.model.HttpParams

interface IRequestInterceptor {
    fun interceptArguments(iRequestApi: IRequestApi, httpParams: HttpParams, httpHeaders: HttpHeaders)
}
