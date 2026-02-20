package com.hjq.http.lifecycle

import android.app.Service
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

abstract class LifecycleService : Service(), LifecycleOwner {
    private val mLifecycle: LifecycleRegistry = LifecycleRegistry(this)

    override val lifecycle: Lifecycle
        get() = mLifecycle

    override fun onCreate() {
        super.onCreate()
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }
}
