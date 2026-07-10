package com.streamax.client.aop

@java.lang.annotation.Target(java.lang.annotation.ElementType.METHOD)
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
annotation class SingleClick(val value: Long = 1000L)
