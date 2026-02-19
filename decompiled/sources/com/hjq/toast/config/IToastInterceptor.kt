package com.hjq.toast.config

interface IToastInterceptor {
    fun intercept(text: CharSequence): Boolean
}
