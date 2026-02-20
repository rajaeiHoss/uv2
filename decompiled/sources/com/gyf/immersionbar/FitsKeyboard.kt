package com.gyf.immersionbar

import android.graphics.Rect
import android.os.Build
import android.view.View
import android.view.ViewTreeObserver
import android.view.Window

internal class FitsKeyboard : ViewTreeObserver.OnGlobalLayoutListener {
    private var mChildView: View? = null
    private var mContentView: View? = null
    private var mDecorView: View? = null
    private var mImmersionBar: ImmersionBar? = null
    private var mIsAddListener: Boolean = false
    private var mPaddingBottom: Int = 0
    private var mPaddingLeft: Int = 0
    private var mPaddingRight: Int = 0
    private var mPaddingTop: Int = 0
    private var mTempKeyboardHeight: Int = 0
    private var mWindow: Window? = null

    constructor(immersionBar: ImmersionBar) {
        throw UnsupportedOperationException(
            "Method not decompiled: com.gyf.immersionbar.FitsKeyboard.<init>(com.gyf.immersionbar.ImmersionBar):void"
        )
    }

    fun enable(mode: Int) {
        if (Build.VERSION.SDK_INT >= 19) {
            mWindow?.setSoftInputMode(mode)
            if (!mIsAddListener) {
                mDecorView?.viewTreeObserver?.addOnGlobalLayoutListener(this)
                mIsAddListener = true
            }
        }
    }

    fun disable() {
        if (Build.VERSION.SDK_INT >= 19 && mIsAddListener) {
            if (mChildView != null) {
                mContentView?.setPadding(mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom)
            } else {
                val immersionBar = mImmersionBar
                if (immersionBar != null) {
                    mContentView?.setPadding(
                        immersionBar.paddingLeft,
                        immersionBar.paddingTop,
                        immersionBar.paddingRight,
                        immersionBar.paddingBottom
                    )
                }
            }
        }
    }

    fun cancel() {
        if (Build.VERSION.SDK_INT >= 19 && mIsAddListener) {
            mDecorView?.viewTreeObserver?.removeOnGlobalLayoutListener(this)
            mIsAddListener = false
        }
    }

    override fun onGlobalLayout() {
        val immersionBar = mImmersionBar
        val barParams = immersionBar?.barParams
        if (immersionBar != null && barParams != null && barParams.keyboardEnable) {
            val barConfig = immersionBar.barConfig
            val navigationBarHeight = if (barConfig.isNavigationAtBottom()) {
                barConfig.getNavigationBarHeight()
            } else {
                barConfig.getNavigationBarWidth()
            }

            val rect = Rect()
            val decorView = mDecorView ?: return
            val contentView = mContentView ?: return
            decorView.getWindowVisibleDisplayFrame(rect)
            var keyboardHeight = contentView.height - rect.bottom

            if (keyboardHeight != mTempKeyboardHeight) {
                mTempKeyboardHeight = keyboardHeight
                var isPopup = true
                var keyboardHeightForListener = 0

                val contentRoot = mWindow?.decorView?.findViewById<View>(16908290)
                if (contentRoot != null && ImmersionBar.checkFitsSystemWindows(contentRoot)) {
                    keyboardHeight -= navigationBarHeight
                    if (keyboardHeight <= navigationBarHeight) {
                        isPopup = false
                    }
                } else if (mChildView != null) {
                    if (barParams.isSupportActionBar) {
                        keyboardHeight += immersionBar.actionBarHeight + barConfig.getStatusBarHeight()
                    }
                    if (barParams.fits) {
                        keyboardHeight += barConfig.getStatusBarHeight()
                    }
                    val contentBottomPadding = if (keyboardHeight > navigationBarHeight) {
                        mPaddingBottom + keyboardHeight
                    } else {
                        isPopup = false
                        0
                    }
                    contentView.setPadding(mPaddingLeft, mPaddingTop, mPaddingRight, contentBottomPadding)
                } else {
                    var contentBottomPadding = immersionBar.paddingBottom
                    keyboardHeight -= navigationBarHeight
                    if (keyboardHeight > navigationBarHeight) {
                        contentBottomPadding = keyboardHeight + navigationBarHeight
                    } else {
                        isPopup = false
                    }
                    contentView.setPadding(
                        immersionBar.paddingLeft,
                        immersionBar.paddingTop,
                        immersionBar.paddingRight,
                        contentBottomPadding
                    )
                }

                if (keyboardHeight >= 0) {
                    keyboardHeightForListener = keyboardHeight
                }
                barParams.onKeyboardListener?.onKeyboardChange(isPopup, keyboardHeightForListener)

                if (!isPopup && barParams.barHide != BarHide.FLAG_SHOW_BAR) {
                    immersionBar.setBar()
                }
            }
        }
    }
}
