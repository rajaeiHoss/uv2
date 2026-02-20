package com.google.android.gms.tasks

interface Continuation<TResult, TContinuationResult> {
    @Throws(Exception::class)
    fun then(task: Task<TResult>): TContinuationResult
}
