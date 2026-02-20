package com.gyf.immersionbar

import android.view.View
import androidx.core.view.ViewCompat

class BarParams : Cloneable {
    @JvmField
    var autoNavigationBarDarkModeAlpha: Float = 0.0f

    @JvmField
    var autoNavigationBarDarkModeEnable: Boolean = false

    @JvmField
    var autoStatusBarDarkModeAlpha: Float = 0.0f

    @JvmField
    var autoStatusBarDarkModeEnable: Boolean = false

    @JvmField
    var barEnable: Boolean = true

    @JvmField
    var barHide: BarHide = BarHide.FLAG_SHOW_BAR

    @JvmField
    var contentAlpha: Float = 0.0f

    @JvmField
    var contentColor: Int = 0

    @JvmField
    var contentColorTransform: Int = ViewCompat.MEASURED_STATE_MASK

    @JvmField
    var defaultNavigationBarColor: Int = ViewCompat.MEASURED_STATE_MASK

    @JvmField
    var fits: Boolean = false

    @JvmField
    var fitsLayoutOverlapEnable: Boolean = true

    @JvmField
    var flymeOSStatusBarFontColor: Int = 0

    @JvmField
    var flymeOSStatusBarFontTempColor: Int = 0

    @JvmField
    var fullScreen: Boolean = false

    @JvmField
    var hideNavigationBar: Boolean = false

    @JvmField
    var isSupportActionBar: Boolean = false

    @JvmField
    var keyboardEnable: Boolean = false

    @JvmField
    var keyboardMode: Int = 18

    @JvmField
    var navigationBarAlpha: Float = 0.0f

    @JvmField
    var navigationBarColor: Int = ViewCompat.MEASURED_STATE_MASK

    @JvmField
    var navigationBarColorTransform: Int = ViewCompat.MEASURED_STATE_MASK

    @JvmField
    var navigationBarDarkIcon: Boolean = false

    @JvmField
    var navigationBarEnable: Boolean = true

    @JvmField
    var navigationBarTempAlpha: Float = 0.0f

    @JvmField
    var navigationBarWithEMUI3Enable: Boolean = true

    @JvmField
    var navigationBarWithKitkatEnable: Boolean = true

    @JvmField
    var onBarListener: OnBarListener? = null

    @JvmField
    var onKeyboardListener: OnKeyboardListener? = null

    @JvmField
    var onNavigationBarListener: OnNavigationBarListener? = null

    @JvmField
    var statusBarAlpha: Float = 0.0f

    @JvmField
    var statusBarColor: Int = 0

    @JvmField
    var statusBarColorEnabled: Boolean = true

    @JvmField
    var statusBarColorTransform: Int = ViewCompat.MEASURED_STATE_MASK

    @JvmField
    var statusBarDarkFont: Boolean = false

    @JvmField
    var statusBarTempAlpha: Float = 0.0f

    @JvmField
    var statusBarView: View? = null

    @JvmField
    var titleBarView: View? = null

    @JvmField
    var viewAlpha: Float = 0.0f

    @JvmField
    var viewMap: MutableMap<View, MutableMap<Int, Int>> = HashMap()

    public override fun clone(): BarParams {
        return try {
            super.clone() as BarParams
        } catch (_: CloneNotSupportedException) {
            this
        }
    }
}
