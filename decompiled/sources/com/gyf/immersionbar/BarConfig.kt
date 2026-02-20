package com.gyf.immersionbar

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.provider.Settings
import android.util.DisplayMetrics
import android.util.TypedValue

internal class BarConfig(activity: Activity) {
    private val mActionBarHeight: Int
    private val mHasNavigationBar: Boolean
    private val mInPortrait: Boolean
    private val mNavigationBarHeight: Int
    private val mNavigationBarWidth: Int
    private val mSmallestWidthDp: Float
    private val mStatusBarHeight: Int

    init {
        mInPortrait = activity.resources.configuration.orientation == 1
        mSmallestWidthDp = getSmallestWidthDp(activity)
        mStatusBarHeight = getInternalDimensionSize(activity, "status_bar_height")
        mActionBarHeight = getActionBarHeight(activity)
        val navigationBarHeight = getNavigationBarHeight(activity)
        mNavigationBarHeight = navigationBarHeight
        mNavigationBarWidth = getNavigationBarWidth(activity)
        mHasNavigationBar = navigationBarHeight > 0
    }

    private fun getActionBarHeight(activity: Activity): Int {
        var actionBarHeight = 0
        if (Build.VERSION.SDK_INT < 14) {
            return 0
        }
        val actionBar = activity.window.findViewById<android.view.View>(R.id.action_bar_container)
        if (actionBar != null) {
            actionBarHeight = actionBar.measuredHeight
        }
        if (actionBarHeight != 0) {
            return actionBarHeight
        }
        val typedValue = TypedValue()
        activity.theme.resolveAttribute(16843499, typedValue, true)
        return TypedValue.complexToDimensionPixelSize(
            typedValue.data,
            activity.resources.displayMetrics
        )
    }

    private fun getNavigationBarHeight(context: Context): Int {
        if (Build.VERSION.SDK_INT < 14 || !hasNavBar(context as Activity)) {
            return 0
        }
        return getInternalDimensionSize(
            context,
            if (mInPortrait) "navigation_bar_height" else "navigation_bar_height_landscape"
        )
    }

    private fun getNavigationBarWidth(context: Context): Int {
        if (Build.VERSION.SDK_INT < 14 || !hasNavBar(context as Activity)) {
            return 0
        }
        return getInternalDimensionSize(context, "navigation_bar_width")
    }

    private fun hasNavBar(activity: Activity): Boolean {
        if (Build.VERSION.SDK_INT >= 17) {
            if (Settings.Global.getInt(activity.contentResolver, "force_fsg_nav_bar", 0) != 0) {
                return false
            }
            if (OSUtils.isEMUI()) {
                if (OSUtils.isEMUI3_x() || Build.VERSION.SDK_INT < 21) {
                    if (Settings.System.getInt(activity.contentResolver, "navigationbar_is_min", 0) != 0) {
                        return false
                    }
                } else if (Settings.Global.getInt(activity.contentResolver, "navigationbar_is_min", 0) != 0) {
                    return false
                }
            }
        }
        val display = activity.windowManager.defaultDisplay
        val realMetrics = DisplayMetrics()
        if (Build.VERSION.SDK_INT >= 17) {
            display.getRealMetrics(realMetrics)
        }
        val realHeight = realMetrics.heightPixels
        val realWidth = realMetrics.widthPixels
        val appMetrics = DisplayMetrics()
        display.getMetrics(appMetrics)
        val appHeight = appMetrics.heightPixels
        return (realWidth - appMetrics.widthPixels > 0) || (realHeight - appHeight > 0)
    }

    private fun getInternalDimensionSize(context: Context, key: String): Int {
        return try {
            val resourceId = Resources.getSystem().getIdentifier(key, "dimen", "android")
            if (resourceId > 0) {
                val targetValue = context.resources.getDimensionPixelSize(resourceId)
                val systemValue = Resources.getSystem().getDimensionPixelSize(resourceId)
                if (systemValue >= targetValue) {
                    systemValue
                } else {
                    val dpValue = (targetValue.toFloat() * Resources.getSystem().displayMetrics.density) /
                        context.resources.displayMetrics.density
                    if (dpValue >= 0.0f) (dpValue + 0.5f).toInt() else (dpValue - 0.5f).toInt()
                }
            } else {
                0
            }
        } catch (_: Resources.NotFoundException) {
            0
        }
    }

    private fun getSmallestWidthDp(activity: Activity): Float {
        val metrics = DisplayMetrics()
        if (Build.VERSION.SDK_INT >= 16) {
            activity.windowManager.defaultDisplay.getRealMetrics(metrics)
        } else {
            activity.windowManager.defaultDisplay.getMetrics(metrics)
        }
        return minOf(metrics.widthPixels.toFloat() / metrics.density, metrics.heightPixels.toFloat() / metrics.density)
    }

    fun isNavigationAtBottom(): Boolean {
        return mSmallestWidthDp >= 600.0f || mInPortrait
    }

    fun getStatusBarHeight(): Int {
        return mStatusBarHeight
    }

    fun getActionBarHeight(): Int {
        return mActionBarHeight
    }

    fun hasNavigationBar(): Boolean {
        return mHasNavigationBar
    }

    fun getNavigationBarHeight(): Int {
        return mNavigationBarHeight
    }

    fun getNavigationBarWidth(): Int {
        return mNavigationBarWidth
    }
}
