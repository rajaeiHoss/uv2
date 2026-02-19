package com.hjq.base.action

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent

interface ActivityAction {
    fun getContext(): Context

    fun getActivity(): Activity? {
        var currentContext: Context? = getContext()
        while (currentContext != null) {
            if (currentContext is Activity) {
                return currentContext
            }
            currentContext =
                if (currentContext is ContextWrapper) {
                    currentContext.baseContext
                } else {
                    null
                }
        }
        return null
    }

    fun startActivity(activityClass: Class<out Activity>) {
        startActivity(Intent(getContext(), activityClass))
    }

    fun startActivity(intent: Intent) {
        if (getContext() !is Activity) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        getContext().startActivity(intent)
    }

    object CC {
        @JvmStatic
        fun `$default$getActivity`(activityAction: ActivityAction): Activity? {
            var currentContext: Context? = activityAction.getContext()
            while (currentContext != null) {
                if (currentContext is Activity) {
                    return currentContext
                }
                currentContext =
                    if (currentContext is ContextWrapper) {
                        currentContext.baseContext
                    } else {
                        null
                    }
            }
            return null
        }

        @JvmStatic
        fun `$default$startActivity`(
            activityAction: ActivityAction,
            activityClass: Class<out Activity>
        ) {
            activityAction.startActivity(Intent(activityAction.getContext(), activityClass))
        }

        @JvmStatic
        fun `$default$startActivity`(activityAction: ActivityAction, intent: Intent) {
            if (activityAction.getContext() !is Activity) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            activityAction.getContext().startActivity(intent)
        }
    }
}
