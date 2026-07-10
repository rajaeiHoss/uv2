package com.google.android.gms.common.api

interface ResultCallback<R : Result> {
    fun onResult(result: R)
}
