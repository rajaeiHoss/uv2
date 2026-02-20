package com.google.firebase.storage

interface OnProgressListener<TProgress> {
    fun onProgress(tprogress: TProgress)
}
