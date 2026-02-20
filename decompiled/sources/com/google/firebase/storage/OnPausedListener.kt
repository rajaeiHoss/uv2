package com.google.firebase.storage

interface OnPausedListener<TProgress> {
    fun onPaused(tprogress: TProgress)
}
