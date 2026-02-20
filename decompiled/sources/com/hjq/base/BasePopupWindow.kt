package com.hjq.base

import android.animation.ValueAnimator
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.SparseArray
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.PopupWindowCompat
import com.hjq.base.action.ActivityAction
import com.hjq.base.action.AnimAction
import com.hjq.base.action.ClickAction
import com.hjq.base.action.HandlerAction
import com.hjq.base.action.KeyboardAction
import com.hjq.base.action.ResourcesAction
import java.lang.ref.SoftReference

open class BasePopupWindow(context: Context) : PopupWindow(context),
    ActivityAction,
    HandlerAction,
    ClickAction,
    AnimAction,
    KeyboardAction,
    PopupWindow.OnDismissListener {

    private val mContext: Context = context
    private var mShowListeners: MutableList<OnShowListener>? = null
    private var mDismissListeners: MutableList<OnDismissListener>? = null
    private var mPopupBackground: PopupBackground? = null

    interface OnClickListener<V : View> {
        fun onClick(basePopupWindow: BasePopupWindow, v: V)
    }

    interface OnCreateListener {
        fun onCreate(basePopupWindow: BasePopupWindow)
    }

    interface OnDismissListener {
        fun onDismiss(basePopupWindow: BasePopupWindow)
    }

    interface OnShowListener {
        fun onShow(basePopupWindow: BasePopupWindow)
    }

    override fun getContext(): Context {
        return mContext
    }

    override fun <V : View> findViewById(id: Int): V {
        return contentView.findViewById(id)
    }

    override fun setOnDismissListener(onDismissListener: PopupWindow.OnDismissListener?) {
        if (onDismissListener != null) {
            addOnDismissListener(DismissListenerWrapper(onDismissListener))
        }
    }

    fun addOnShowListener(onShowListener: OnShowListener?) {
        if (onShowListener == null) {
            return
        }
        if (mShowListeners == null) {
            mShowListeners = ArrayList()
        }
        mShowListeners!!.add(onShowListener)
    }

    fun addOnDismissListener(onDismissListener: OnDismissListener?) {
        if (onDismissListener == null) {
            return
        }
        if (mDismissListeners == null) {
            mDismissListeners = ArrayList()
            super.setOnDismissListener(this)
        }
        mDismissListeners!!.add(onDismissListener)
    }

    fun removeOnShowListener(onShowListener: OnShowListener?) {
        mShowListeners?.remove(onShowListener)
    }

    fun removeOnDismissListener(onDismissListener: OnDismissListener?) {
        mDismissListeners?.remove(onDismissListener)
    }

    private fun setOnShowListeners(list: MutableList<OnShowListener>?) {
        mShowListeners = list
    }

    private fun setOnDismissListeners(list: MutableList<OnDismissListener>?) {
        super.setOnDismissListener(this)
        mDismissListeners = list
    }

    override fun onDismiss() {
        val dismissListeners = mDismissListeners
        if (dismissListeners != null) {
            for (listener in dismissListeners) {
                listener.onDismiss(this)
            }
        }
    }

    override fun showAsDropDown(anchor: View, xoff: Int, yoff: Int, gravity: Int) {
        if (!isShowing && contentView != null) {
            val showListeners = mShowListeners
            if (showListeners != null) {
                for (listener in showListeners) {
                    listener.onShow(this)
                }
            }
            super.showAsDropDown(anchor, xoff, yoff, gravity)
        }
    }

    override fun showAtLocation(parent: View, gravity: Int, x: Int, y: Int) {
        if (!isShowing && contentView != null) {
            val showListeners = mShowListeners
            if (showListeners != null) {
                for (listener in showListeners) {
                    listener.onShow(this)
                }
            }
            super.showAtLocation(parent, gravity, x, y)
        }
    }

    override fun dismiss() {
        super.dismiss()
        removeCallbacks()
    }

    override fun setWindowLayoutType(type: Int) {
        if (Build.VERSION.SDK_INT >= 23) {
            super.setWindowLayoutType(type)
        } else {
            PopupWindowCompat.setWindowLayoutType(this, type)
        }
    }

    override fun getWindowLayoutType(): Int {
        return if (Build.VERSION.SDK_INT >= 23) {
            super.getWindowLayoutType()
        } else {
            PopupWindowCompat.getWindowLayoutType(this)
        }
    }

    override fun setOverlapAnchor(overlapAnchor: Boolean) {
        if (Build.VERSION.SDK_INT >= 23) {
            super.setOverlapAnchor(overlapAnchor)
        } else {
            PopupWindowCompat.setOverlapAnchor(this, overlapAnchor)
        }
    }

    fun setBackgroundDimAmount(amount: Float) {
        val alpha = 1.0f - amount
        if (isShowing) {
            setActivityAlpha(alpha)
        }

        if (mPopupBackground == null && alpha != 1.0f) {
            val popupBackground = PopupBackground()
            mPopupBackground = popupBackground
            addOnShowListener(popupBackground)
            addOnDismissListener(popupBackground)
        }

        mPopupBackground?.setAlpha(alpha)
    }

    private fun setActivityAlpha(alpha: Float) {
        val activity = getActivity()
        if (activity != null) {
            val attributes = activity.window.attributes
            val animator = ValueAnimator.ofFloat(attributes.alpha, alpha)
            animator.duration = 300
            animator.addUpdateListener {
                val value = it.animatedValue as Float
                if (value != attributes.alpha) {
                    attributes.alpha = value
                    activity.window.attributes = attributes
                }
            }
            animator.start()
        }
    }

    open class Builder<B : Builder<B>> : ActivityAction, ResourcesAction, ClickAction, KeyboardAction {
        private val mContext: Context
        private val mActivity: Activity?
        private var mAnimations = -1
        private var mWidth = -2
        private var mHeight = -2
        private var mGravity = DEFAULT_ANCHORED_GRAVITY
        private var mXOffset = 0
        private var mYOffset = 0
        private var mTouchable = true
        private var mFocusable = true
        private var mOutsideTouchable = false
        private var mBackgroundDimAmount = 0.0f
        private var mContentView: View? = null
        private var mClickArray: SparseArray<OnClickListener<out View>?>? = null
        private var mPopupWindow: BasePopupWindow? = null
        private var mCreateListener: OnCreateListener? = null
        private val mShowListeners: MutableList<OnShowListener> = ArrayList()
        private val mDismissListeners: MutableList<OnDismissListener> = ArrayList()

        constructor(activity: Activity) : this(activity as Context)

        constructor(context: Context) {
            mContext = context
            mActivity = getActivity()
        }

        private fun self(): B {
            return this as B
        }

        override fun getContext(): Context {
            return mContext
        }

        override fun <V : View> findViewById(id: Int): V {
            val contentView = mContentView
            if (contentView != null) {
                return contentView.findViewById(id)
            }
            throw IllegalStateException("are you ok?")
        }

        fun setContentView(layoutId: Int): B {
            return setContentView(
                LayoutInflater.from(mContext).inflate(layoutId, FrameLayout(mContext), false)
            )
        }

        fun setContentView(view: View?): B {
            var gravity: Int
            if (view != null) {
                mContentView = view
                if (isCreated()) {
                    mPopupWindow!!.contentView = view
                    return self()
                }

                val layoutParams = mContentView!!.layoutParams
                if (layoutParams != null && mWidth == -2 && mHeight == -2) {
                    setWidth(layoutParams.width)
                    setHeight(layoutParams.height)
                }

                if (mGravity == DEFAULT_ANCHORED_GRAVITY) {
                    if (layoutParams is FrameLayout.LayoutParams) {
                        val frameGravity = layoutParams.gravity
                        if (frameGravity != -1) {
                            setGravity(frameGravity)
                        }
                    } else if (layoutParams is LinearLayout.LayoutParams) {
                        gravity = layoutParams.gravity
                        if (gravity != -1) {
                            setGravity(gravity)
                        }
                    }
                    if (mGravity == 0) {
                        setGravity(Gravity.CENTER)
                    }
                }

                return self()
            }
            throw IllegalArgumentException("are you ok?")
        }

        fun setAnimStyle(animations: Int): B {
            mAnimations = animations
            if (isCreated()) {
                mPopupWindow!!.animationStyle = animations
            }
            return self()
        }

        fun setWidth(width: Int): B {
            mWidth = width
            if (isCreated()) {
                mPopupWindow!!.width = width
                return self()
            }
            val layoutParams = mContentView?.layoutParams
            if (layoutParams != null) {
                layoutParams.width = width
                mContentView!!.layoutParams = layoutParams
            }
            return self()
        }

        fun setHeight(height: Int): B {
            mHeight = height
            if (isCreated()) {
                mPopupWindow!!.height = height
                return self()
            }
            val layoutParams = mContentView?.layoutParams
            if (layoutParams != null) {
                layoutParams.height = height
                mContentView!!.layoutParams = layoutParams
            }
            return self()
        }

        fun setGravity(gravity: Int): B {
            mGravity = Gravity.getAbsoluteGravity(gravity, mContext.resources.configuration.layoutDirection)
            return self()
        }

        fun setXOffset(offset: Int): B {
            mXOffset = offset
            return self()
        }

        fun setYOffset(offset: Int): B {
            mYOffset = offset
            return self()
        }

        fun setTouchable(touchable: Boolean): B {
            mTouchable = touchable
            if (isCreated()) {
                mPopupWindow!!.isTouchable = touchable
            }
            return self()
        }

        fun setFocusable(focusable: Boolean): B {
            mFocusable = focusable
            if (isCreated()) {
                mPopupWindow!!.isFocusable = focusable
            }
            return self()
        }

        fun setOutsideTouchable(outsideTouchable: Boolean): B {
            mOutsideTouchable = outsideTouchable
            if (isCreated()) {
                mPopupWindow!!.isOutsideTouchable = outsideTouchable
            }
            return self()
        }

        fun setBackgroundDimAmount(backgroundDimAmount: Float): B {
            mBackgroundDimAmount = backgroundDimAmount
            if (isCreated()) {
                mPopupWindow!!.setBackgroundDimAmount(backgroundDimAmount)
            }
            return self()
        }

        fun setOnCreateListener(onCreateListener: OnCreateListener?): B {
            mCreateListener = onCreateListener
            return self()
        }

        fun addOnShowListener(onShowListener: OnShowListener?): B {
            if (onShowListener != null) {
                mShowListeners.add(onShowListener)
            }
            return self()
        }

        fun addOnDismissListener(onDismissListener: OnDismissListener?): B {
            if (onDismissListener != null) {
                mDismissListeners.add(onDismissListener)
            }
            return self()
        }

        fun setText(viewId: Int, stringId: Int): B {
            return setText(viewId, getString(stringId))
        }

        fun setText(viewId: Int, text: CharSequence?): B {
            (findViewById<View>(viewId) as TextView).text = text
            return self()
        }

        fun setTextColor(viewId: Int, textColor: Int): B {
            (findViewById<View>(viewId) as TextView).setTextColor(textColor)
            return self()
        }

        fun setHint(viewId: Int, stringId: Int): B {
            return setHint(viewId, getString(stringId))
        }

        fun setHint(viewId: Int, text: CharSequence?): B {
            (findViewById<View>(viewId) as TextView).hint = text
            return self()
        }

        fun setVisibility(viewId: Int, visibility: Int): B {
            findViewById<View>(viewId).visibility = visibility
            return self()
        }

        fun setBackground(viewId: Int, drawableId: Int): B {
            return setBackground(viewId, ContextCompat.getDrawable(mContext, drawableId))
        }

        fun setBackground(viewId: Int, drawable: Drawable?): B {
            findViewById<View>(viewId).background = drawable
            return self()
        }

        fun setImageDrawable(viewId: Int, drawableId: Int): B {
            return setBackground(viewId, ContextCompat.getDrawable(mContext, drawableId))
        }

        fun setImageDrawable(viewId: Int, drawable: Drawable?): B {
            (findViewById<View>(viewId) as ImageView).setImageDrawable(drawable)
            return self()
        }

        fun setOnClickListener(viewId: Int, onClickListener: OnClickListener<out View>?): B {
            if (mClickArray == null) {
                mClickArray = SparseArray()
            }
            mClickArray!!.put(viewId, onClickListener)
            if (isCreated()) {
                val view = mPopupWindow!!.findViewById<View>(viewId)
                view?.setOnClickListener(ViewClickWrapper(mPopupWindow!!, onClickListener))
            }
            return self()
        }

        open fun create(): BasePopupWindow {
            val contentView = mContentView
            if (contentView != null) {
                if (isShowing()) {
                    dismiss()
                }

                if (mGravity == DEFAULT_ANCHORED_GRAVITY) {
                    mGravity = Gravity.CENTER
                }

                if (mAnimations == -1) {
                    val gravity = mGravity
                    mAnimations = if (gravity == Gravity.LEFT) {
                        AnimAction.ANIM_LEFT
                    } else if (gravity == Gravity.RIGHT) {
                        AnimAction.ANIM_RIGHT
                    } else if (gravity == Gravity.TOP) {
                        AnimAction.ANIM_TOP
                    } else if (gravity == Gravity.BOTTOM) {
                        AnimAction.ANIM_BOTTOM
                    } else {
                        -1
                    }
                }

                val popupWindow = createPopupWindow(mContext)
                mPopupWindow = popupWindow
                popupWindow.contentView = contentView
                popupWindow.width = mWidth
                popupWindow.height = mHeight
                popupWindow.animationStyle = mAnimations
                popupWindow.isFocusable = mFocusable
                popupWindow.isTouchable = mTouchable
                popupWindow.isOutsideTouchable = mOutsideTouchable
                popupWindow.setBackgroundDrawable(ColorDrawable(0))
                popupWindow.setOnShowListeners(mShowListeners)
                popupWindow.setOnDismissListeners(mDismissListeners)
                popupWindow.setBackgroundDimAmount(mBackgroundDimAmount)

                val clickArray = mClickArray
                var index = 0
                while (clickArray != null && index < clickArray.size()) {
                    val view = contentView.findViewById<View>(clickArray.keyAt(index))
                    if (view != null) {
                        view.setOnClickListener(
                            ViewClickWrapper(
                                popupWindow,
                                clickArray.valueAt(index)
                            )
                        )
                    }
                    index++
                }

                val activity = mActivity
                if (activity != null) {
                    PopupWindowLifecycle.with(activity, popupWindow)
                }

                mCreateListener?.onCreate(popupWindow)
                return popupWindow
            }
            throw IllegalArgumentException("are you ok?")
        }

        fun showAsDropDown(anchor: View) {
            val activity = mActivity
            if (activity != null && !activity.isFinishing && !activity.isDestroyed) {
                if (!isCreated()) {
                    create()
                }
                mPopupWindow!!.showAsDropDown(anchor, mXOffset, mYOffset, mGravity)
            }
        }

        fun showAtLocation(parent: View) {
            val activity = mActivity
            if (activity != null && !activity.isFinishing && !activity.isDestroyed) {
                if (!isCreated()) {
                    create()
                }
                mPopupWindow!!.showAtLocation(parent, mGravity, mXOffset, mYOffset)
            }
        }

        fun isCreated(): Boolean {
            return mPopupWindow != null
        }

        fun isShowing(): Boolean {
            return isCreated() && mPopupWindow!!.isShowing
        }

        fun dismiss() {
            val activity = mActivity
            val popupWindow = mPopupWindow
            if (activity != null && !activity.isFinishing && !activity.isDestroyed && popupWindow != null) {
                popupWindow.dismiss()
            }
        }

        protected open fun createPopupWindow(context: Context): BasePopupWindow {
            return BasePopupWindow(context)
        }

        fun getContentView(): View? {
            return mContentView
        }

        fun getPopupWindow(): BasePopupWindow? {
            return mPopupWindow
        }

        fun post(runnable: Runnable) {
            if (isShowing()) {
                mPopupWindow!!.post(runnable)
            } else {
                addOnShowListener(ShowPostWrapper(runnable))
            }
        }

        fun postDelayed(runnable: Runnable, delayMillis: Long) {
            if (isShowing()) {
                mPopupWindow!!.postDelayed(runnable, delayMillis)
            } else {
                addOnShowListener(ShowPostDelayedWrapper(runnable, delayMillis))
            }
        }

        fun postAtTime(runnable: Runnable, uptimeMillis: Long) {
            if (isShowing()) {
                mPopupWindow!!.postAtTime(runnable, uptimeMillis)
            } else {
                addOnShowListener(ShowPostAtTimeWrapper(runnable, uptimeMillis))
            }
        }

        companion object {
            private const val DEFAULT_ANCHORED_GRAVITY = 8388659
        }
    }

    private class PopupWindowLifecycle(
        private var mActivity: Activity?,
        basePopupWindow: BasePopupWindow
    ) : Application.ActivityLifecycleCallbacks,
        OnShowListener,
        OnDismissListener {

        private var mPopupWindow: BasePopupWindow? = null

        init {
            basePopupWindow.addOnShowListener(this)
            basePopupWindow.addOnDismissListener(this)
        }

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        }

        override fun onActivityStarted(activity: Activity) {
        }

        override fun onActivityResumed(activity: Activity) {
        }

        override fun onActivityPaused(activity: Activity) {
        }

        override fun onActivityStopped(activity: Activity) {
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        }

        override fun onActivityDestroyed(activity: Activity) {
            if (mActivity === activity) {
                unregisterActivityLifecycleCallbacks()
                mActivity = null
                val popupWindow = mPopupWindow
                if (popupWindow != null) {
                    popupWindow.removeOnShowListener(this)
                    popupWindow.removeOnDismissListener(this)
                    if (popupWindow.isShowing) {
                        popupWindow.dismiss()
                    }
                    mPopupWindow = null
                }
            }
        }

        override fun onShow(basePopupWindow: BasePopupWindow) {
            mPopupWindow = basePopupWindow
            registerActivityLifecycleCallbacks()
        }

        override fun onDismiss(basePopupWindow: BasePopupWindow) {
            mPopupWindow = null
            unregisterActivityLifecycleCallbacks()
        }

        private fun registerActivityLifecycleCallbacks() {
            val activity = mActivity
            if (activity != null) {
                if (Build.VERSION.SDK_INT >= 29) {
                    activity.registerActivityLifecycleCallbacks(this)
                } else {
                    activity.application.registerActivityLifecycleCallbacks(this)
                }
            }
        }

        private fun unregisterActivityLifecycleCallbacks() {
            val activity = mActivity
            if (activity != null) {
                if (Build.VERSION.SDK_INT >= 29) {
                    activity.unregisterActivityLifecycleCallbacks(this)
                } else {
                    activity.application.unregisterActivityLifecycleCallbacks(this)
                }
            }
        }

        companion object {
            fun with(activity: Activity, basePopupWindow: BasePopupWindow) {
                PopupWindowLifecycle(activity, basePopupWindow)
            }
        }
    }

    private class PopupBackground : OnShowListener, OnDismissListener {
        private var mAlpha = 0.0f

        fun setAlpha(alpha: Float) {
            mAlpha = alpha
        }

        override fun onShow(basePopupWindow: BasePopupWindow) {
            basePopupWindow.setActivityAlpha(mAlpha)
        }

        override fun onDismiss(basePopupWindow: BasePopupWindow) {
            basePopupWindow.setActivityAlpha(1.0f)
        }
    }

    private class DismissListenerWrapper(onDismissListener: PopupWindow.OnDismissListener) :
        SoftReference<PopupWindow.OnDismissListener>(onDismissListener),
        OnDismissListener {

        override fun onDismiss(basePopupWindow: BasePopupWindow) {
            get()?.onDismiss()
        }
    }

    private class ViewClickWrapper(
        private val mBasePopupWindow: BasePopupWindow,
        private val mListener: OnClickListener<out View>?
    ) : View.OnClickListener {

        override fun onClick(view: View) {
            val listener = mListener
            if (listener != null) {
                (listener as OnClickListener<View>).onClick(mBasePopupWindow, view)
            }
        }
    }

    private class ShowPostWrapper(private val mRunnable: Runnable?) : OnShowListener {
        override fun onShow(basePopupWindow: BasePopupWindow) {
            if (mRunnable != null) {
                basePopupWindow.removeOnShowListener(this)
                basePopupWindow.post(mRunnable)
            }
        }
    }

    private class ShowPostDelayedWrapper(
        private val mRunnable: Runnable?,
        private val mDelayMillis: Long
    ) : OnShowListener {

        override fun onShow(basePopupWindow: BasePopupWindow) {
            if (mRunnable != null) {
                basePopupWindow.removeOnShowListener(this)
                basePopupWindow.postDelayed(mRunnable, mDelayMillis)
            }
        }
    }

    private class ShowPostAtTimeWrapper(
        private val mRunnable: Runnable?,
        private val mUptimeMillis: Long
    ) : OnShowListener {

        override fun onShow(basePopupWindow: BasePopupWindow) {
            if (mRunnable != null) {
                basePopupWindow.removeOnShowListener(this)
                basePopupWindow.postAtTime(mRunnable, mUptimeMillis)
            }
        }
    }
}
