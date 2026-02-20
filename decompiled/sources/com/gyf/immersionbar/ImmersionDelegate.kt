package com.gyf.immersionbar

import android.app.Activity
import android.app.Dialog
import android.content.res.Configuration
import android.os.Build
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

internal class ImmersionDelegate : Runnable {
    private var mBarProperties: BarProperties? = null
    private var mImmersionBar: ImmersionBar? = null
    private var mNotchHeight: Int = 0
    private var mOnBarListener: OnBarListener? = null

    constructor(obj: Any) {
        when (obj) {
            is Activity -> {
                if (mImmersionBar == null) {
                    mImmersionBar = ImmersionBar(obj)
                }
            }

            is Fragment -> {
                if (mImmersionBar != null) {
                    return
                }
                mImmersionBar = if (obj is DialogFragment) {
                    ImmersionBar(obj)
                } else {
                    ImmersionBar(obj)
                }
            }

            is android.app.Fragment -> {
                if (mImmersionBar == null) {
                    mImmersionBar = if (obj is android.app.DialogFragment) {
                        ImmersionBar(obj)
                    } else {
                        ImmersionBar(obj)
                    }
                }
            }
        }
    }

    constructor(activity: Activity, dialog: Dialog) {
        if (mImmersionBar == null) {
            mImmersionBar = ImmersionBar(activity, dialog)
        }
    }

    fun get(): ImmersionBar {
        return mImmersionBar!!
    }

    fun onActivityCreated(configuration: Configuration) {
        barChanged(configuration)
    }

    fun onResume() {
        mImmersionBar?.onResume()
    }

    fun onDestroy() {
        mBarProperties = null
        mImmersionBar?.onDestroy()
        mImmersionBar = null
    }

    fun onConfigurationChanged(configuration: Configuration) {
        val immersionBar = mImmersionBar
        if (immersionBar != null) {
            immersionBar.onConfigurationChanged(configuration)
            barChanged(configuration)
        }
    }

    private fun barChanged(configuration: Configuration) {
        val immersionBar = mImmersionBar
        if (immersionBar != null && immersionBar.initialized() && Build.VERSION.SDK_INT >= 19) {
            val onBarListener = immersionBar.barParams.onBarListener
            mOnBarListener = onBarListener
            if (onBarListener != null) {
                val activity = immersionBar.activity
                if (mBarProperties == null) {
                    mBarProperties = BarProperties()
                }
                mBarProperties!!.setPortrait(configuration.orientation == 1)
                val rotation = activity.windowManager.defaultDisplay.rotation
                if (rotation == 1) {
                    mBarProperties!!.setLandscapeLeft(true)
                    mBarProperties!!.setLandscapeRight(false)
                } else if (rotation == 3) {
                    mBarProperties!!.setLandscapeLeft(false)
                    mBarProperties!!.setLandscapeRight(true)
                } else {
                    mBarProperties!!.setLandscapeLeft(false)
                    mBarProperties!!.setLandscapeRight(false)
                }
                activity.window.decorView.post(this)
            }
        }
    }

    override fun run() {
        val immersionBar = mImmersionBar
        if (immersionBar != null && immersionBar.activity != null) {
            val activity = immersionBar.activity
            val barConfig = BarConfig(activity)
            mBarProperties!!.setStatusBarHeight(barConfig.getStatusBarHeight())
            mBarProperties!!.setNavigationBar(barConfig.hasNavigationBar())
            mBarProperties!!.setNavigationBarHeight(barConfig.getNavigationBarHeight())
            mBarProperties!!.setNavigationBarWidth(barConfig.getNavigationBarWidth())
            mBarProperties!!.setActionBarHeight(barConfig.getActionBarHeight())
            val hasNotchScreen = NotchUtils.hasNotchScreen(activity)
            mBarProperties!!.setNotchScreen(hasNotchScreen)
            if (hasNotchScreen && mNotchHeight == 0) {
                val notchHeight = NotchUtils.getNotchHeight(activity)
                mNotchHeight = notchHeight
                mBarProperties!!.setNotchHeight(notchHeight)
            }
            mOnBarListener!!.onBarChange(mBarProperties!!)
        }
    }
}
