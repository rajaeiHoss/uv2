package com.hjq.http.config

import com.hjq.http.model.BodyType

interface IRequestType {
    fun getType(): BodyType
}
