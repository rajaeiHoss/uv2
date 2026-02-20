package com.hjq.http.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Build
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

class ActivityLifecycle(
    activity: Activity
) : LifecycleOwner, LifecycleEventObserver, Application.ActivityLifecycleCallbacks {
    private var mActivity: Activity? = activity
    private val mLifecycle: LifecycleRegistry = LifecycleRegistry(this)

    init {
        if (activity is LifecycleOwner) {
            activity.lifecycle.addObserver(this)
        } else if (Build.VERSION.SDK_INT >= 29) {
            activity.registerActivityLifecycleCallbacks(this)
        } else {
            activity.application.registerActivityLifecycleCallbacks(this)
        }
    }

    override val lifecycle: Lifecycle
        get() = mLifecycle

    override fun onStateChanged(lifecycleOwner: LifecycleOwner, event: Lifecycle.Event) {
        mLifecycle.handleLifecycleEvent(event)
        if (event == Lifecycle.Event.ON_DESTROY) {
            lifecycleOwner.lifecycle.removeObserver(this)
            mActivity = null
        }
    }

    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
        if (mActivity === activity) {
            mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        }
    }

    override fun onActivityStarted(activity: Activity) {
        if (mActivity === activity) {
            mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_START)
        }
    }

    override fun onActivityResumed(activity: Activity) {
        if (mActivity === activity) {
            mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        }
    }

    override fun onActivityPaused(activity: Activity) {
        if (mActivity === activity) {
            mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        }
    }

    override fun onActivityStopped(activity: Activity) {
        if (mActivity === activity) {
            mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
        val target = mActivity
        if (target === activity) {
            mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            if (Build.VERSION.SDK_INT >= 29) {
                target?.unregisterActivityLifecycleCallbacks(this)
            } else {
                target?.application?.unregisterActivityLifecycleCallbacks(this)
            }
            mActivity = null
        }
    }
}
