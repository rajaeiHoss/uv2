package com.hjq.base.action

import android.os.Handler
import android.os.Looper
import android.os.SystemClock

interface HandlerAction {
    fun getHandler(): Handler {
        return HANDLER
    }

    fun post(runnable: Runnable): Boolean {
        return postDelayed(runnable, 0)
    }

    fun postDelayed(runnable: Runnable, delayMillis: Long): Boolean {
        val safeDelayMillis = if (delayMillis < 0) 0 else delayMillis
        return postAtTime(runnable, SystemClock.uptimeMillis() + safeDelayMillis)
    }

    fun postAtTime(runnable: Runnable, uptimeMillis: Long): Boolean {
        return HANDLER.postAtTime(runnable, this, uptimeMillis)
    }

    fun removeCallbacks(runnable: Runnable) {
        HANDLER.removeCallbacks(runnable)
    }

    fun removeCallbacks() {
        HANDLER.removeCallbacksAndMessages(this)
    }

    companion object {
        @JvmField
        val HANDLER: Handler = Handler(Looper.getMainLooper())
    }

    object CC {
        @JvmStatic
        fun `$default$getHandler`(handlerAction: HandlerAction): Handler {
            return HANDLER
        }

        @JvmStatic
        fun `$default$post`(handlerAction: HandlerAction, runnable: Runnable): Boolean {
            return handlerAction.postDelayed(runnable, 0)
        }

        @JvmStatic
        fun `$default$postDelayed`(
            handlerAction: HandlerAction,
            runnable: Runnable,
            delayMillis: Long
        ): Boolean {
            val safeDelayMillis = if (delayMillis < 0) 0 else delayMillis
            return handlerAction.postAtTime(runnable, SystemClock.uptimeMillis() + safeDelayMillis)
        }

        @JvmStatic
        fun `$default$postAtTime`(
            handlerAction: HandlerAction,
            runnable: Runnable,
            uptimeMillis: Long
        ): Boolean {
            return HANDLER.postAtTime(runnable, handlerAction, uptimeMillis)
        }

        @JvmStatic
        fun `$default$removeCallbacks`(handlerAction: HandlerAction, runnable: Runnable) {
            HANDLER.removeCallbacks(runnable)
        }

        @JvmStatic
        fun `$default$removeCallbacks`(handlerAction: HandlerAction) {
            HANDLER.removeCallbacksAndMessages(handlerAction)
        }
    }
}
