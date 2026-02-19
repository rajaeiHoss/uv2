package com.hjq.toast

import android.app.Activity
import android.app.Application
import android.os.Build
import android.os.Bundle

class WindowLifecycle(
    private var mActivity: Activity?
) : Application.ActivityLifecycleCallbacks {
    private var mToastImpl: ToastImpl? = null

    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    fun getActivity(): Activity? = mActivity

    override fun onActivityPaused(activity: Activity) {
        if (mActivity === activity) {
            mToastImpl?.cancel()
        }
    }

    override fun onActivityDestroyed(activity: Activity) {
        if (mActivity === activity) {
            mToastImpl?.cancel()
            unregister()
            mActivity = null
        }
    }

    fun register(toastImpl: Any) {
        mToastImpl = toastImpl as? ToastImpl
        mActivity?.let { activity ->
            if (Build.VERSION.SDK_INT >= 29) {
                activity.registerActivityLifecycleCallbacks(this)
            } else {
                activity.application.registerActivityLifecycleCallbacks(this)
            }
        }
    }

    fun unregister() {
        mToastImpl = null
        mActivity?.let { activity ->
            if (Build.VERSION.SDK_INT >= 29) {
                activity.unregisterActivityLifecycleCallbacks(this)
            } else {
                activity.application.unregisterActivityLifecycleCallbacks(this)
            }
        }
    }
}
