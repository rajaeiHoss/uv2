package com.gyf.immersionbar

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.TypedValue
import android.view.DisplayCutout
import android.view.View
import android.view.WindowInsets

class NotchUtils private constructor() {
    companion object {
        private const val NOTCH_HUA_WEI: String = "com.huawei.android.util.HwNotchSizeUtil"
        private const val NOTCH_OPPO: String = "com.oppo.feature.screen.heteromorphism"
        private const val NOTCH_VIVO: String = "android.util.FtFeature"

        @JvmStatic
        fun hasNotchScreen(activity: Activity?): Boolean {
            return activity != null && (
                hasNotchAtXiaoMi(activity) ||
                    hasNotchAtHuaWei(activity) ||
                    hasNotchAtOPPO(activity) ||
                    hasNotchAtVIVO(activity) ||
                    hasNotchAtAndroidP(activity)
                )
        }

        @JvmStatic
        fun hasNotchScreen(view: View?): Boolean {
            return view != null && (
                hasNotchAtXiaoMi(view.context) ||
                    hasNotchAtHuaWei(view.context) ||
                    hasNotchAtOPPO(view.context) ||
                    hasNotchAtVIVO(view.context) ||
                    hasNotchAtAndroidP(view)
                )
        }

        private fun hasNotchAtAndroidP(view: View): Boolean {
            return getDisplayCutout(view) != null
        }

        private fun hasNotchAtAndroidP(activity: Activity): Boolean {
            return getDisplayCutout(activity) != null
        }

        private fun getDisplayCutout(activity: Activity?): DisplayCutout? {
            if (Build.VERSION.SDK_INT < 28 || activity == null) {
                return null
            }
            val window = activity.window ?: return null
            val rootWindowInsets = window.decorView.rootWindowInsets ?: return null
            return rootWindowInsets.displayCutout
        }

        private fun getDisplayCutout(view: View?): DisplayCutout? {
            if (Build.VERSION.SDK_INT < 28 || view == null) {
                return null
            }
            val rootWindowInsets: WindowInsets = view.rootWindowInsets ?: return null
            return rootWindowInsets.displayCutout
        }

        private fun hasNotchAtXiaoMi(context: Context): Boolean {
            throw UnsupportedOperationException(
                "Method not decompiled: com.gyf.immersionbar.NotchUtils.hasNotchAtXiaoMi(android.content.Context):boolean"
            )
        }

        private fun hasNotchAtHuaWei(context: Context): Boolean {
            return try {
                val clazz = context.classLoader.loadClass(NOTCH_HUA_WEI)
                clazz.getMethod("hasNotchInScreen").invoke(clazz) as Boolean
            } catch (_: Exception) {
                false
            }
        }

        private fun hasNotchAtVIVO(context: Context): Boolean {
            return try {
                val clazz = context.classLoader.loadClass(NOTCH_VIVO)
                clazz.getMethod("isFeatureSupport", Int::class.javaPrimitiveType)
                    .invoke(clazz, 32) as Boolean
            } catch (_: Exception) {
                false
            }
        }

        private fun hasNotchAtOPPO(context: Context): Boolean {
            return try {
                context.packageManager.hasSystemFeature(NOTCH_OPPO)
            } catch (_: Exception) {
                false
            }
        }

        @JvmStatic
        fun getNotchHeight(activity: Activity): Int {
            val statusBarHeight = ImmersionBar.getStatusBarHeight(activity)
            val displayCutout = getDisplayCutout(activity)
            if (Build.VERSION.SDK_INT < 28 || displayCutout == null) {
                var notchHeight = if (hasNotchAtXiaoMi(activity)) getXiaoMiNotchHeight(activity) else 0
                if (hasNotchAtHuaWei(activity)) {
                    notchHeight = getHuaWeiNotchSize(activity)[1]
                }
                if (hasNotchAtVIVO(activity) && dp2px(activity, 32).also { notchHeight = it } < statusBarHeight) {
                    notchHeight = statusBarHeight
                }
                if (!hasNotchAtOPPO(activity)) {
                    return notchHeight
                }
                return if (80 < statusBarHeight) statusBarHeight else 80
            }
            return if (activity.resources.configuration.orientation == 1) {
                displayCutout.safeInsetTop
            } else {
                if (displayCutout.safeInsetLeft == 0) displayCutout.safeInsetRight else displayCutout.safeInsetLeft
            }
        }

        private fun getXiaoMiNotchHeight(context: Context): Int {
            val id = context.resources.getIdentifier("notch_height", "dimen", "android")
            return if (id > 0) context.resources.getDimensionPixelSize(id) else 0
        }

        private fun getHuaWeiNotchSize(context: Context): IntArray {
            val defaultSize = intArrayOf(0, 0)
            return try {
                val clazz = context.classLoader.loadClass(NOTCH_HUA_WEI)
                clazz.getMethod("getNotchSize").invoke(clazz) as IntArray
            } catch (_: Exception) {
                defaultSize
            }
        }

        private fun dp2px(context: Context, dp: Int): Int {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp.toFloat(),
                context.resources.displayMetrics
            ).toInt()
        }
    }
}
