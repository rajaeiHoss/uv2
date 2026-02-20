package com.google.gson.internal

interface ObjectConstructor<T> {
    fun construct(): T
}
