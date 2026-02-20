package com.hjq.base

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.SparseArray
import android.view.Gravity
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.hjq.base.action.ActivityAction
import com.hjq.base.action.AnimAction
import com.hjq.base.action.ClickAction
import com.hjq.base.action.HandlerAction
import com.hjq.base.action.KeyboardAction
import com.hjq.base.action.ResourcesAction
import java.lang.ref.SoftReference

open class BaseDialog : AppCompatDialog,
    LifecycleOwner,
    ActivityAction,
    ResourcesAction,
    HandlerAction,
    ClickAction,
    AnimAction,
    KeyboardAction,
    DialogInterface.OnShowListener,
    DialogInterface.OnCancelListener,
    DialogInterface.OnDismissListener {

    private var mShowListeners: MutableList<OnShowListener>? = null
    private var mCancelListeners: MutableList<OnCancelListener>? = null
    private var mDismissListeners: MutableList<OnDismissListener>? = null
    private val mListeners: ListenersWrapper<BaseDialog>
    private val mLifecycle: LifecycleRegistry

    interface OnCancelListener {
        fun onCancel(baseDialog: BaseDialog)
    }

    interface OnClickListener<V : View> {
        fun onClick(baseDialog: BaseDialog, v: V)
    }

    interface OnCreateListener {
        fun onCreate(baseDialog: BaseDialog)
    }

    interface OnDismissListener {
        fun onDismiss(baseDialog: BaseDialog)
    }

    interface OnKeyListener {
        fun onKey(baseDialog: BaseDialog, keyEvent: KeyEvent): Boolean
    }

    interface OnShowListener {
        fun onShow(baseDialog: BaseDialog)
    }

    constructor(context: Context) : this(context, R.style.BaseDialogTheme)

    constructor(context: Context, themeResId: Int) : super(context, themeResId) {
        mListeners = ListenersWrapper(this)
        mLifecycle = LifecycleRegistry(this)
    }

    fun getContentView(): View? {
        val content = findViewById<View>(android.R.id.content)
        if (content !is ViewGroup) {
            return content
        }
        return if (content.childCount == 1) content.getChildAt(0) else content
    }

    fun setWidth(width: Int) {
        val window = window
        if (window != null) {
            val params = window.attributes
            params.width = width
            window.attributes = params
        }
    }

    fun setHeight(height: Int) {
        val window = window
        if (window != null) {
            val params = window.attributes
            params.height = height
            window.attributes = params
        }
    }

    fun setXOffset(offset: Int) {
        val window = window
        if (window != null) {
            val params = window.attributes
            params.x = offset
            window.attributes = params
        }
    }

    fun setYOffset(offset: Int) {
        val window = window
        if (window != null) {
            val params = window.attributes
            params.y = offset
            window.attributes = params
        }
    }

    fun getGravityCompat(): Int {
        val window = window
        return window?.attributes?.gravity ?: 0
    }

    fun setGravity(gravity: Int) {
        window?.setGravity(gravity)
    }

    fun setWindowAnimations(windowAnimations: Int) {
        window?.setWindowAnimations(windowAnimations)
    }

    fun getWindowAnimations(): Int {
        val window = window
        return window?.attributes?.windowAnimations ?: -1
    }

    fun setBackgroundDimEnabled(enabled: Boolean) {
        val window = window
        if (window != null) {
            if (enabled) {
                window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            } else {
                window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            }
        }
    }

    fun setBackgroundDimAmount(amount: Float) {
        window?.setDimAmount(amount)
    }

    override fun dismiss() {
        removeCallbacks()
        val focus = currentFocus
        if (focus != null) {
            (getSystemService(InputMethodManager::class.java) as InputMethodManager?)
                ?.hideSoftInputFromWindow(focus.windowToken, 0)
        }
        super.dismiss()
    }

    override val lifecycle: Lifecycle
        get() = mLifecycle

    override fun setOnShowListener(listener: DialogInterface.OnShowListener?) {
        if (listener != null) {
            addOnShowListener(ShowListenerWrapper(listener))
        }
    }

    override fun setOnCancelListener(listener: DialogInterface.OnCancelListener?) {
        if (listener != null) {
            addOnCancelListener(CancelListenerWrapper(listener))
        }
    }

    override fun setOnDismissListener(listener: DialogInterface.OnDismissListener?) {
        if (listener != null) {
            addOnDismissListener(DismissListenerWrapper(listener))
        }
    }

    override fun setOnKeyListener(onKeyListener: DialogInterface.OnKeyListener?) {
        super.setOnKeyListener(onKeyListener)
    }

    fun setOnKeyListener(onKeyListener: OnKeyListener?) {
        super.setOnKeyListener(KeyListenerWrapper(onKeyListener))
    }

    fun addOnShowListener(onShowListener: OnShowListener?) {
        if (onShowListener == null) {
            return
        }
        if (mShowListeners == null) {
            mShowListeners = ArrayList()
            super.setOnShowListener(mListeners)
        }
        mShowListeners!!.add(onShowListener)
    }

    fun addOnCancelListener(onCancelListener: OnCancelListener?) {
        if (onCancelListener == null) {
            return
        }
        if (mCancelListeners == null) {
            mCancelListeners = ArrayList()
            super.setOnCancelListener(mListeners)
        }
        mCancelListeners!!.add(onCancelListener)
    }

    fun addOnDismissListener(onDismissListener: OnDismissListener?) {
        if (onDismissListener == null) {
            return
        }
        if (mDismissListeners == null) {
            mDismissListeners = ArrayList()
            super.setOnDismissListener(mListeners)
        }
        mDismissListeners!!.add(onDismissListener)
    }

    fun removeOnShowListener(onShowListener: OnShowListener?) {
        mShowListeners?.remove(onShowListener)
    }

    fun removeOnCancelListener(onCancelListener: OnCancelListener?) {
        mCancelListeners?.remove(onCancelListener)
    }

    fun removeOnDismissListener(onDismissListener: OnDismissListener?) {
        mDismissListeners?.remove(onDismissListener)
    }

    private fun setOnShowListeners(list: MutableList<OnShowListener>?) {
        super.setOnShowListener(mListeners)
        mShowListeners = list
    }

    private fun setOnCancelListeners(list: MutableList<OnCancelListener>?) {
        super.setOnCancelListener(mListeners)
        mCancelListeners = list
    }

    private fun setOnDismissListeners(list: MutableList<OnDismissListener>?) {
        super.setOnDismissListener(mListeners)
        mDismissListeners = list
    }

    override fun onShow(dialog: DialogInterface) {
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        val showListeners = mShowListeners
        if (showListeners != null) {
            for (index in 0 until showListeners.size) {
                showListeners[index].onShow(this)
            }
        }
    }

    override fun onCancel(dialog: DialogInterface) {
        val cancelListeners = mCancelListeners
        if (cancelListeners != null) {
            for (index in 0 until cancelListeners.size) {
                cancelListeners[index].onCancel(this)
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        val dismissListeners = mDismissListeners
        if (dismissListeners != null) {
            for (index in 0 until dismissListeners.size) {
                dismissListeners[index].onDismiss(this)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
    }

    override fun onStart() {
        super.onStart()
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    override fun onStop() {
        super.onStop()
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
    }

    open class Builder<B : Builder<B>> : ActivityAction, ResourcesAction, ClickAction, KeyboardAction {
        private val mContext: Context
        private val mActivity: Activity?
        private var mThemeId = R.style.BaseDialogTheme
        private var mAnimStyle = -1
        private var mWidth = -2
        private var mHeight = -2
        private var mGravity = 0
        private var mXOffset = 0
        private var mYOffset = 0
        private var mCancelable = true
        private var mCanceledOnTouchOutside = true
        private var mBackgroundDimEnabled = true
        private var mBackgroundDimAmount = 0.5f
        private var mContentView: View? = null
        private var mClickArray: SparseArray<OnClickListener<out View>?>? = null
        private var mDialog: BaseDialog? = null
        private var mCreateListener: OnCreateListener? = null
        private var mKeyListener: OnKeyListener? = null
        private val mShowListeners: MutableList<OnShowListener> = ArrayList()
        private val mCancelListeners: MutableList<OnCancelListener> = ArrayList()
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
                    mDialog!!.setContentView(view)
                    return self()
                }

                val layoutParams = mContentView!!.layoutParams
                if (layoutParams != null && mWidth == -2 && mHeight == -2) {
                    setWidth(layoutParams.width)
                    setHeight(layoutParams.height)
                }

                if (mGravity == 0) {
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

        fun setThemeStyle(themeStyle: Int): B {
            mThemeId = themeStyle
            if (!isCreated()) {
                return self()
            }
            throw IllegalStateException("are you ok?")
        }

        fun setAnimStyle(animStyle: Int): B {
            mAnimStyle = animStyle
            if (isCreated()) {
                mDialog!!.setWindowAnimations(animStyle)
            }
            return self()
        }

        fun setWidth(width: Int): B {
            mWidth = width
            if (isCreated()) {
                mDialog!!.setWidth(width)
                return self()
            }
            val params = mContentView?.layoutParams
            if (params != null) {
                params.width = width
                mContentView!!.layoutParams = params
            }
            return self()
        }

        fun setHeight(height: Int): B {
            mHeight = height
            if (isCreated()) {
                mDialog!!.setHeight(height)
                return self()
            }
            val params = mContentView?.layoutParams
            if (params != null) {
                params.height = height
                mContentView!!.layoutParams = params
            }
            return self()
        }

        fun setGravity(gravity: Int): B {
            mGravity = Gravity.getAbsoluteGravity(gravity, mContext.resources.configuration.layoutDirection)
            if (isCreated()) {
                mDialog!!.setGravity(gravity)
            }
            return self()
        }

        fun setXOffset(offset: Int): B {
            mXOffset = offset
            if (isCreated()) {
                mDialog!!.setXOffset(offset)
            }
            return self()
        }

        fun setYOffset(offset: Int): B {
            mYOffset = offset
            if (isCreated()) {
                mDialog!!.setYOffset(offset)
            }
            return self()
        }

        fun setCancelable(cancelable: Boolean): B {
            mCancelable = cancelable
            if (isCreated()) {
                mDialog!!.setCancelable(cancelable)
            }
            return self()
        }

        fun setCanceledOnTouchOutside(canceledOnTouchOutside: Boolean): B {
            mCanceledOnTouchOutside = canceledOnTouchOutside
            if (isCreated() && mCancelable) {
                mDialog!!.setCanceledOnTouchOutside(canceledOnTouchOutside)
            }
            return self()
        }

        fun setBackgroundDimEnabled(backgroundDimEnabled: Boolean): B {
            mBackgroundDimEnabled = backgroundDimEnabled
            if (isCreated()) {
                mDialog!!.setBackgroundDimEnabled(backgroundDimEnabled)
            }
            return self()
        }

        fun setBackgroundDimAmount(backgroundDimAmount: Float): B {
            mBackgroundDimAmount = backgroundDimAmount
            if (isCreated()) {
                mDialog!!.setBackgroundDimAmount(backgroundDimAmount)
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

        fun addOnCancelListener(onCancelListener: OnCancelListener?): B {
            if (onCancelListener != null) {
                mCancelListeners.add(onCancelListener)
            }
            return self()
        }

        fun addOnDismissListener(onDismissListener: OnDismissListener?): B {
            if (onDismissListener != null) {
                mDismissListeners.add(onDismissListener)
            }
            return self()
        }

        fun setOnKeyListener(onKeyListener: OnKeyListener?): B {
            mKeyListener = onKeyListener
            if (isCreated()) {
                mDialog!!.setOnKeyListener(onKeyListener)
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
                val view = mDialog!!.findViewById<View>(viewId)
                view?.setOnClickListener(ViewClickWrapper(mDialog!!, onClickListener))
            }
            return self()
        }

        open fun create(): BaseDialog {
            val contentView = mContentView
            if (contentView != null) {
                if (isShowing()) {
                    dismiss()
                }

                if (mGravity == 0) {
                    mGravity = Gravity.CENTER
                }

                if (mAnimStyle == -1) {
                    val gravity = mGravity
                    mAnimStyle = if (gravity == Gravity.LEFT) {
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

                val dialog = createDialog(mContext, mThemeId)
                mDialog = dialog
                dialog.setContentView(contentView)
                dialog.setCancelable(mCancelable)
                if (mCancelable) {
                    dialog.setCanceledOnTouchOutside(mCanceledOnTouchOutside)
                }
                dialog.setOnShowListeners(mShowListeners)
                dialog.setOnCancelListeners(mCancelListeners)
                dialog.setOnDismissListeners(mDismissListeners)
                dialog.setOnKeyListener(mKeyListener)

                val window: Window? = dialog.window
                if (window != null) {
                    val params = window.attributes
                    params.width = mWidth
                    params.height = mHeight
                    params.gravity = mGravity
                    params.x = mXOffset
                    params.y = mYOffset
                    params.windowAnimations = mAnimStyle
                    if (mBackgroundDimEnabled) {
                        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
                        window.setDimAmount(mBackgroundDimAmount)
                    } else {
                        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
                    }
                    window.attributes = params
                }

                val clickArray = mClickArray
                var index = 0
                while (clickArray != null && index < clickArray.size()) {
                    val view = contentView.findViewById<View>(clickArray.keyAt(index))
                    if (view != null) {
                        view.setOnClickListener(ViewClickWrapper(dialog, clickArray.valueAt(index)))
                    }
                    index++
                }

                val activity = mActivity
                if (activity != null) {
                    DialogLifecycle.with(activity, dialog)
                }

                mCreateListener?.onCreate(dialog)
                return dialog
            }
            throw IllegalArgumentException("are you ok?")
        }

        fun show() {
            val activity = mActivity
            if (activity != null && !activity.isFinishing && !activity.isDestroyed) {
                if (!isCreated()) {
                    create()
                }
                if (!isShowing()) {
                    mDialog!!.show()
                }
            }
        }

        fun dismiss() {
            val activity = mActivity
            val dialog = mDialog
            if (activity != null && !activity.isFinishing && !activity.isDestroyed && dialog != null) {
                dialog.dismiss()
            }
        }

        fun isCreated(): Boolean {
            return mDialog != null
        }

        fun isShowing(): Boolean {
            return isCreated() && mDialog!!.isShowing
        }

        protected open fun createDialog(context: Context, themeStyle: Int): BaseDialog {
            return BaseDialog(context, themeStyle)
        }

        fun post(runnable: Runnable) {
            if (isShowing()) {
                mDialog!!.post(runnable)
            } else {
                addOnShowListener(ShowPostWrapper(runnable))
            }
        }

        fun postDelayed(runnable: Runnable, delayMillis: Long) {
            if (isShowing()) {
                mDialog!!.postDelayed(runnable, delayMillis)
            } else {
                addOnShowListener(ShowPostDelayedWrapper(runnable, delayMillis))
            }
        }

        fun postAtTime(runnable: Runnable, uptimeMillis: Long) {
            if (isShowing()) {
                mDialog!!.postAtTime(runnable, uptimeMillis)
            } else {
                addOnShowListener(ShowPostAtTimeWrapper(runnable, uptimeMillis))
            }
        }

        fun getContentView(): View? {
            return mContentView
        }

        fun getDialog(): BaseDialog? {
            return mDialog
        }
    }

    private class DialogLifecycle(
        private var mActivity: Activity?,
        baseDialog: BaseDialog
    ) : Application.ActivityLifecycleCallbacks,
        OnShowListener,
        OnDismissListener {

        private var mDialog: BaseDialog? = null
        private var mDialogAnim = 0

        init {
            baseDialog.addOnShowListener(this)
            baseDialog.addOnDismissListener(this)
        }

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        }

        override fun onActivityStarted(activity: Activity) {
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        }

        override fun onActivityStopped(activity: Activity) {
        }

        override fun onActivityResumed(activity: Activity) {
            val dialog = mDialog
            if (mActivity === activity && dialog != null && dialog.isShowing) {
                dialog.postDelayed(
                    {
                        val targetDialog = mDialog
                        if (targetDialog != null && targetDialog.isShowing) {
                            targetDialog.setWindowAnimations(mDialogAnim)
                        }
                    },
                    100
                )
            }
        }

        override fun onActivityPaused(activity: Activity) {
            val dialog = mDialog
            if (mActivity === activity && dialog != null && dialog.isShowing) {
                mDialogAnim = dialog.getWindowAnimations()
                dialog.setWindowAnimations(0)
            }
        }

        override fun onActivityDestroyed(activity: Activity) {
            if (mActivity === activity) {
                unregisterActivityLifecycleCallbacks()
                mActivity = null
                val dialog = mDialog
                if (dialog != null) {
                    dialog.removeOnShowListener(this)
                    dialog.removeOnDismissListener(this)
                    if (dialog.isShowing) {
                        dialog.dismiss()
                    }
                    mDialog = null
                }
            }
        }

        override fun onShow(baseDialog: BaseDialog) {
            mDialog = baseDialog
            registerActivityLifecycleCallbacks()
        }

        override fun onDismiss(baseDialog: BaseDialog) {
            mDialog = null
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
            fun with(activity: Activity, baseDialog: BaseDialog) {
                DialogLifecycle(activity, baseDialog)
            }
        }
    }

    private class ListenersWrapper<T>(listener: T) :
        SoftReference<T>(listener),
        DialogInterface.OnShowListener,
        DialogInterface.OnCancelListener,
        DialogInterface.OnDismissListener
        where T : DialogInterface.OnShowListener,
              T : DialogInterface.OnCancelListener,
              T : DialogInterface.OnDismissListener {

        override fun onShow(dialog: DialogInterface) {
            get()?.onShow(dialog)
        }

        override fun onCancel(dialog: DialogInterface) {
            get()?.onCancel(dialog)
        }

        override fun onDismiss(dialog: DialogInterface) {
            get()?.onDismiss(dialog)
        }
    }

    private class ViewClickWrapper(
        private val mDialog: BaseDialog,
        private val mListener: OnClickListener<out View>?
    ) : View.OnClickListener {

        override fun onClick(view: View) {
            val listener = mListener
            if (listener != null) {
                (listener as OnClickListener<View>).onClick(mDialog, view)
            }
        }
    }

    private class ShowListenerWrapper(listener: DialogInterface.OnShowListener) :
        SoftReference<DialogInterface.OnShowListener>(listener),
        OnShowListener {

        override fun onShow(baseDialog: BaseDialog) {
            get()?.onShow(baseDialog)
        }
    }

    private class CancelListenerWrapper(listener: DialogInterface.OnCancelListener) :
        SoftReference<DialogInterface.OnCancelListener>(listener),
        OnCancelListener {

        override fun onCancel(baseDialog: BaseDialog) {
            get()?.onCancel(baseDialog)
        }
    }

    private class DismissListenerWrapper(listener: DialogInterface.OnDismissListener) :
        SoftReference<DialogInterface.OnDismissListener>(listener),
        OnDismissListener {

        override fun onDismiss(baseDialog: BaseDialog) {
            get()?.onDismiss(baseDialog)
        }
    }

    private class KeyListenerWrapper(private val mListener: OnKeyListener?) : DialogInterface.OnKeyListener {
        override fun onKey(dialog: DialogInterface, keyCode: Int, event: KeyEvent): Boolean {
            val listener = mListener
            return if (listener == null || dialog !is BaseDialog) {
                false
            } else {
                listener.onKey(dialog, event)
            }
        }
    }

    private class ShowPostWrapper(private val mRunnable: Runnable?) : OnShowListener {
        override fun onShow(baseDialog: BaseDialog) {
            if (mRunnable != null) {
                baseDialog.removeOnShowListener(this)
                baseDialog.post(mRunnable)
            }
        }
    }

    private class ShowPostDelayedWrapper(
        private val mRunnable: Runnable?,
        private val mDelayMillis: Long
    ) : OnShowListener {

        override fun onShow(baseDialog: BaseDialog) {
            if (mRunnable != null) {
                baseDialog.removeOnShowListener(this)
                baseDialog.postDelayed(mRunnable, mDelayMillis)
            }
        }
    }

    private class ShowPostAtTimeWrapper(
        private val mRunnable: Runnable?,
        private val mUptimeMillis: Long
    ) : OnShowListener {

        override fun onShow(baseDialog: BaseDialog) {
            if (mRunnable != null) {
                baseDialog.removeOnShowListener(this)
                baseDialog.postAtTime(mRunnable, mUptimeMillis)
            }
        }
    }
}
