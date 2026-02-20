package com.google.android.gms.tasks

interface SuccessContinuation<TResult, TContinuationResult> {
    @Throws(Exception::class)
    fun then(tresult: TResult): Task<TContinuationResult>
}
