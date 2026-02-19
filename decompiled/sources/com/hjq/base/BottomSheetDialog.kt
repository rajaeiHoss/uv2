package com.hjq.base

import android.content.Context
import android.content.res.TypedArray
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior

class BottomSheetDialog : BaseDialog, View.OnTouchListener, View.OnClickListener {
    private val mBottomSheetBehavior: BottomSheetBehavior<FrameLayout>
    private var mCancelable: Boolean = true
    private var mCanceledOnTouchOutside: Boolean = true
    private var mCanceledOnTouchOutsideSet: Boolean = false

    constructor(context: Context) : this(context, R.style.BaseDialogTheme)

    constructor(context: Context, themeId: Int) : super(context, themeId) {
        val bottomSheetBehavior = BottomSheetBehavior<FrameLayout>(context, null as AttributeSet?)
        mBottomSheetBehavior = bottomSheetBehavior
        bottomSheetBehavior.addBottomSheetCallback(MyBottomSheetCallback())
        bottomSheetBehavior.isHideable = mCancelable
        supportRequestWindowFeature(1)
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window: Window? = window
        if (window != null) {
            window.clearFlags(67108864)
            window.addFlags(Int.MIN_VALUE)
            val decorView = window.decorView
            decorView.systemUiVisibility = decorView.systemUiVisibility or 2 or 4096
            window.setLayout(-1, -1)
        }
    }

    override fun setContentView(layoutResId: Int) {
        super.setContentView(wrapContentView(layoutInflater.inflate(layoutResId, null, false)))
    }

    override fun setContentView(view: View) {
        super.setContentView(wrapContentView(view))
    }

    override fun setContentView(view: View, layoutParams: ViewGroup.LayoutParams?) {
        view.layoutParams = layoutParams
        super.setContentView(wrapContentView(view))
    }

    override fun setCancelable(cancelable: Boolean) {
        super.setCancelable(cancelable)
        if (mCancelable != cancelable) {
            mCancelable = cancelable
            mBottomSheetBehavior.isHideable = cancelable
        }
    }

    override fun onStart() {
        super.onStart()
        if (mBottomSheetBehavior.state == 5) {
            mBottomSheetBehavior.state = 4
        }
    }

    override fun cancel() {
        if (mBottomSheetBehavior.state == 5) {
            super.cancel()
        } else {
            mBottomSheetBehavior.state = 5
        }
    }

    override fun setCanceledOnTouchOutside(cancel: Boolean) {
        super.setCanceledOnTouchOutside(cancel)
        if (cancel && !mCancelable) {
            mCancelable = true
        }
        mCanceledOnTouchOutside = cancel
        mCanceledOnTouchOutsideSet = true
    }

    private fun shouldWindowCloseOnTouchOutside(): Boolean {
        if (!mCanceledOnTouchOutsideSet) {
            val typedArray: TypedArray = context.obtainStyledAttributes(intArrayOf(16843611))
            mCanceledOnTouchOutside = typedArray.getBoolean(0, true)
            typedArray.recycle()
            mCanceledOnTouchOutsideSet = true
        }
        return mCanceledOnTouchOutside
    }

    private fun wrapContentView(view: View): View {
        val coordinatorLayout = CoordinatorLayout(context)
        coordinatorLayout.layoutParams = ViewGroup.LayoutParams(-1, -1)

        val outsideView = View(context)
        outsideView.isSoundEffectsEnabled = false
        outsideView.importantForAccessibility = 2
        outsideView.layoutParams = ViewGroup.LayoutParams(-1, -1)

        val sheetLayout = FrameLayout(context)
        val layoutParams = CoordinatorLayout.LayoutParams(-1, -2)
        layoutParams.gravity = 49
        layoutParams.behavior = mBottomSheetBehavior
        sheetLayout.layoutParams = layoutParams
        sheetLayout.addView(view)

        coordinatorLayout.addView(outsideView)
        coordinatorLayout.addView(sheetLayout)
        outsideView.setOnClickListener(this)
        ViewCompat.setAccessibilityDelegate(sheetLayout, BehaviorAccessibilityDelegate())
        sheetLayout.setOnTouchListener(this)
        return coordinatorLayout
    }

    fun getBottomSheetBehavior(): BottomSheetBehavior<FrameLayout> {
        return mBottomSheetBehavior
    }

    override fun onClick(view: View) {
        if (mCancelable && isShowing && shouldWindowCloseOnTouchOutside()) {
            cancel()
        }
    }

    private inner class MyBottomSheetCallback : BottomSheetBehavior.BottomSheetCallback() {
        override fun onSlide(bottomSheet: View, slideOffset: Float) {
        }

        override fun onStateChanged(bottomSheet: View, newState: Int) {
            if (newState == 5) {
                cancel()
            }
        }
    }

    private inner class BehaviorAccessibilityDelegate : AccessibilityDelegateCompat() {
        override fun onInitializeAccessibilityNodeInfo(
            host: View,
            info: AccessibilityNodeInfoCompat
        ) {
            super.onInitializeAccessibilityNodeInfo(host, info)
            if (mCancelable) {
                info.addAction(1048576)
                info.isDismissable = true
            } else {
                info.isDismissable = false
            }
        }

        override fun performAccessibilityAction(host: View, action: Int, args: Bundle?): Boolean {
            if (action != 1048576 || !mCancelable) {
                return super.performAccessibilityAction(host, action, args)
            }
            cancel()
            return true
        }
    }
}
