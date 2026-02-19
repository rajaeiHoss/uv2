package com.hjq.toast

import android.app.Activity
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.media2.subtitle.Cea708CCParser
import com.hjq.base.action.AnimAction
import com.hjq.toast.config.IToast

class ToastImpl(activity: Activity, private val mToast: IToast) {
    private val mPackageName: String = activity.packageName
    private var mShow: Boolean = false
    private val mWindowLifecycle: WindowLifecycle = WindowLifecycle(activity)

    private val mCancelRunnable = Runnable {
        try {
            val activityRef = mWindowLifecycle.getActivity()
            if (activityRef != null) {
                val windowManager = activityRef.getSystemService("window") as? WindowManager
                if (windowManager != null) {
                    windowManager.removeViewImmediate(mToast.getView())
                    mWindowLifecycle.unregister()
                    setShow(false)
                    return@Runnable
                }
            }
            mWindowLifecycle.unregister()
            setShow(false)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        } catch (throwable: Throwable) {
            mWindowLifecycle.unregister()
            setShow(false)
            throw throwable
        }
    }

    private val mShowRunnable = Runnable {
        val activityRef = mWindowLifecycle.getActivity()
        if (activityRef != null && !activityRef.isFinishing) {
            if (Build.VERSION.SDK_INT < 17 || !activityRef.isDestroyed) {
                val layoutParams = WindowManager.LayoutParams()
                layoutParams.height = -2
                layoutParams.width = -2
                layoutParams.format = -3
                layoutParams.windowAnimations = AnimAction.ANIM_TOAST
                layoutParams.flags = Cea708CCParser.Const.CODE_C1_DF0
                layoutParams.packageName = mPackageName
                layoutParams.gravity = mToast.getGravity()
                layoutParams.x = mToast.getXOffset()
                layoutParams.y = mToast.getYOffset()
                layoutParams.verticalMargin = mToast.getVerticalMargin()
                layoutParams.horizontalMargin = mToast.getHorizontalMargin()
                val windowManager = activityRef.getSystemService("window") as? WindowManager
                if (windowManager != null) {
                    try {
                        windowManager.addView(mToast.getView(), layoutParams)
                        HANDLER.postDelayed(
                            { cancel() },
                            if (mToast.getDuration() == 1) LONG_DURATION_TIMEOUT.toLong() else SHORT_DURATION_TIMEOUT.toLong()
                        )
                        mWindowLifecycle.register(this)
                        setShow(true)
                    } catch (e: WindowManager.BadTokenException) {
                        e.printStackTrace()
                    } catch (e: IllegalStateException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    fun isShow(): Boolean = mShow

    fun setShow(show: Boolean) {
        mShow = show
    }

    fun show() {
        if (!isShow()) {
            HANDLER.removeCallbacks(mShowRunnable)
            HANDLER.post(mShowRunnable)
        }
    }

    fun cancel() {
        if (isShow()) {
            HANDLER.removeCallbacks(mCancelRunnable)
            HANDLER.post(mCancelRunnable)
        }
    }

    companion object {
        private const val LONG_DURATION_TIMEOUT: Int = 3500
        private const val SHORT_DURATION_TIMEOUT: Int = 2000
        private val HANDLER: Handler = Handler(Looper.getMainLooper())
    }
}
