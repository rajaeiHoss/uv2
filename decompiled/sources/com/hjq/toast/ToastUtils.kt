package com.hjq.toast

import android.app.Application
import android.content.res.Resources
import com.hjq.toast.config.IToastInterceptor
import com.hjq.toast.config.IToastStrategy
import com.hjq.toast.config.IToastStyle
import com.hjq.toast.style.BlackToastStyle
import com.hjq.toast.style.LocationToastStyle
import com.hjq.toast.style.ViewToastStyle

class ToastUtils private constructor() {
    companion object {
        private var sApplication: Application? = null
        private var sDebugMode: Boolean? = null
        private var sToastInterceptor: IToastInterceptor? = null
        private var sToastStrategy: IToastStrategy? = null
        private var sToastStyle: IToastStyle<*>? = null

        @JvmStatic
        fun init(application: Application) {
            init(application, sToastStyle)
        }

        @JvmStatic
        fun init(application: Application, style: IToastStyle<*>?) {
            sApplication = application
            if (sToastStrategy == null) {
                setStrategy(ToastStrategy())
            }
            val toastStyle = style ?: BlackToastStyle()
            setStyle(toastStyle)
        }

        @JvmStatic
        fun isInit(): Boolean = sApplication != null && sToastStrategy != null && sToastStyle != null

        @JvmStatic
        fun show(obj: Any?) {
            show(obj?.toString() ?: "null")
        }

        @JvmStatic
        fun debugShow(obj: Any?) {
            if (isDebugMode()) {
                show(obj)
            }
        }

        @JvmStatic
        fun show(textResId: Int) {
            try {
                show(sApplication!!.resources.getText(textResId))
            } catch (_: Resources.NotFoundException) {
                show(textResId.toString())
            }
        }

        @JvmStatic
        fun debugShow(textResId: Int) {
            if (isDebugMode()) {
                show(textResId)
            }
        }

        @JvmStatic
        fun show(text: CharSequence?) {
            if (!text.isNullOrEmpty()) {
                val toastInterceptor = sToastInterceptor
                if (toastInterceptor == null || !toastInterceptor.intercept(text)) {
                    sToastStrategy!!.showToast(text)
                }
            }
        }

        @JvmStatic
        fun debugShow(text: CharSequence?) {
            if (isDebugMode()) {
                show(text)
            }
        }

        @JvmStatic
        fun cancel() {
            sToastStrategy!!.cancelToast()
        }

        @JvmStatic
        fun setGravity(gravity: Int) {
            setGravity(gravity, 0, 0)
        }

        @JvmStatic
        fun setGravity(gravity: Int, xOffset: Int, yOffset: Int) {
            setGravity(gravity, xOffset, yOffset, 0.0f, 0.0f)
        }

        @JvmStatic
        fun setGravity(gravity: Int, xOffset: Int, yOffset: Int, horizontalMargin: Float, verticalMargin: Float) {
            sToastStrategy!!.bindStyle(
                LocationToastStyle(sToastStyle, gravity, xOffset, yOffset, horizontalMargin, verticalMargin)
            )
        }

        @JvmStatic
        fun setView(layoutId: Int) {
            if (layoutId > 0) {
                setStyle(ViewToastStyle(layoutId, sToastStyle))
            }
        }

        @JvmStatic
        fun setStyle(style: IToastStyle<*>) {
            sToastStyle = style
            sToastStrategy!!.bindStyle(style)
        }

        @JvmStatic
        fun getStyle(): IToastStyle<*>? = sToastStyle

        @JvmStatic
        fun setStrategy(strategy: IToastStrategy) {
            sToastStrategy = strategy
            strategy.registerStrategy(sApplication!!)
        }

        @JvmStatic
        fun getStrategy(): IToastStrategy? = sToastStrategy

        @JvmStatic
        fun setInterceptor(interceptor: IToastInterceptor?) {
            sToastInterceptor = interceptor
        }

        @JvmStatic
        fun getInterceptor(): IToastInterceptor? = sToastInterceptor

        @JvmStatic
        fun setDebugMode(debugMode: Boolean) {
            sDebugMode = debugMode
        }

        private fun isDebugMode(): Boolean {
            if (sDebugMode == null) {
                sDebugMode = (sApplication!!.applicationInfo.flags and 2) != 0
            }
            return sDebugMode == true
        }
    }
}
