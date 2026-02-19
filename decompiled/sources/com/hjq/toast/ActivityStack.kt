package com.hjq.toast

import android.app.Activity
import android.app.Application
import android.os.Bundle

class ActivityStack private constructor() : Application.ActivityLifecycleCallbacks {
    private var mForegroundActivity: Activity? = null

    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    fun getForegroundActivity(): Activity? = mForegroundActivity

    override fun onActivityResumed(activity: Activity) {
        mForegroundActivity = activity
    }

    override fun onActivityPaused(activity: Activity) {
        if (mForegroundActivity === activity) {
            mForegroundActivity = null
        }
    }

    companion object {
        @JvmStatic
        fun register(application: Application): ActivityStack {
            val activityStack = ActivityStack()
            application.registerActivityLifecycleCallbacks(activityStack)
            return activityStack
        }
    }
}
