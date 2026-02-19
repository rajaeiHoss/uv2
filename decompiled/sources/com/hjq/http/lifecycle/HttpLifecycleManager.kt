package com.hjq.http.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.hjq.http.EasyHttp

class HttpLifecycleManager : LifecycleEventObserver {
    companion object {
        @JvmStatic
        fun bind(lifecycleOwner: LifecycleOwner) {
            lifecycleOwner.lifecycle.addObserver(HttpLifecycleManager())
        }

        @JvmStatic
        fun isLifecycleActive(lifecycleOwner: LifecycleOwner?): Boolean {
            return lifecycleOwner != null && lifecycleOwner.lifecycle.currentState != Lifecycle.State.DESTROYED
        }
    }

    override fun onStateChanged(lifecycleOwner: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            lifecycleOwner.lifecycle.removeObserver(this)
            EasyHttp.cancel(lifecycleOwner)
        }
    }
}
