package com.google.android.gms.tasks

interface OnCompleteListener<TResult> {
    fun onComplete(task: Task<TResult>)
}
