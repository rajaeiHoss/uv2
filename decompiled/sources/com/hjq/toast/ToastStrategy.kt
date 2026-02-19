package com.hjq.toast

import android.app.Activity
import android.app.Application
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.hjq.toast.config.IToast
import com.hjq.toast.config.IToastStrategy
import com.hjq.toast.config.IToastStyle
import java.lang.ref.WeakReference

open class ToastStrategy : Handler(Looper.getMainLooper()), IToastStrategy {
    private var mActivityStack: ActivityStack? = null
    private var mApplication: Application? = null
    private var mToastReference: WeakReference<IToast>? = null
    private var mToastStyle: IToastStyle<*>? = null

    override fun registerStrategy(application: Application) {
        mApplication = application
        mActivityStack = ActivityStack.register(application)
    }

    override fun bindStyle(style: IToastStyle<*>) {
        mToastStyle = style
    }

    override fun createToast(application: Application): IToast {
        val foregroundActivity: Activity? = mActivityStack!!.getForegroundActivity()
        val toast: IToast = if (foregroundActivity != null) {
            ActivityToast(foregroundActivity)
        } else if (Build.VERSION.SDK_INT == 25) {
            SafeToast(application)
        } else {
            SystemToast(application)
        }
        if (toast is ActivityToast || Build.VERSION.SDK_INT < 30 || application.applicationInfo.targetSdkVersion < 30) {
            val style = mToastStyle!!
            toast.setView(style.createView(application))
            toast.setGravity(style.getGravity(), style.getXOffset(), style.getYOffset())
            toast.setMargin(style.getHorizontalMargin(), style.getVerticalMargin())
        }
        return toast
    }

    override fun showToast(text: CharSequence) {
        removeMessages(TYPE_SHOW)
        val message = Message.obtain().apply {
            what = TYPE_SHOW
            obj = text
        }
        sendMessageDelayed(message, DELAY_TIMEOUT.toLong())
    }

    override fun cancelToast() {
        removeMessages(TYPE_CANCEL)
        sendEmptyMessage(TYPE_CANCEL)
    }

    override fun handleMessage(message: Message) {
        val toast: IToast? = mToastReference?.get()
        when (message.what) {
            TYPE_SHOW -> {
                val text = message.obj
                if (text is CharSequence) {
                    toast?.cancel()
                    val createToast = createToast(mApplication!!)
                    mToastReference = WeakReference(createToast)
                    createToast.setDuration(getToastDuration(text))
                    createToast.setText(text)
                    createToast.show()
                }
            }

            TYPE_CANCEL -> {
                toast?.cancel()
            }
        }
    }

    protected open fun getToastDuration(text: CharSequence): Int = if (text.length > 20) 1 else 0

    private companion object {
        private const val DELAY_TIMEOUT: Int = 200
        private const val TYPE_SHOW: Int = 1
        private const val TYPE_CANCEL: Int = 2
    }
}
