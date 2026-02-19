package com.hjq.http.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

class ApplicationLifecycle : LifecycleOwner {
    private val mLifecycle: LifecycleRegistry = LifecycleRegistry(this)

    override fun getLifecycle(): Lifecycle {
        return mLifecycle
    }
}
