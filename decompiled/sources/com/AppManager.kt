package com

import android.app.Activity
import java.util.Stack

class AppManager private constructor() {

    fun addActivity(activity: Activity) {
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack!!.add(activity)
    }

    fun finishAllActivity() {
        val stack = activityStack ?: return
        for (index in stack.indices) {
            val activity = stack[index]
            activity?.finish()
        }
        stack.clear()
    }

    companion object {
        private var activityStack: Stack<Activity?>? = null
        private var instance: AppManager? = null

        @JvmStatic
        fun getAppManager(): AppManager {
            if (instance == null) {
                instance = AppManager()
            }
            return instance!!
        }
    }
}
