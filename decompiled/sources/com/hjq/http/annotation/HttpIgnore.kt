package com.hjq.http.annotation

@MustBeDocumented
@Target(allowedTargets = [AnnotationTarget.FIELD])
@Retention(AnnotationRetention.RUNTIME)
annotation class HttpIgnore
