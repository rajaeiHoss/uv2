package com.hjq.toast

import android.app.Activity
import android.view.View
import android.widget.TextView
import com.hjq.toast.config.IToast

class ActivityToast(activity: Activity) : IToast {
    private var mDuration: Int = 0
    private var mGravity: Int = 0
    private var mHorizontalMargin: Float = 0.0f
    private var mMessageView: TextView? = null
    private val mToastImpl: ToastImpl = ToastImpl(activity, this)
    private var mVerticalMargin: Float = 0.0f
    private var mView: View? = null
    private var mXOffset: Int = 0
    private var mYOffset: Int = 0

    override fun findMessageView(view: View): TextView {
        if (view is TextView) {
            val id = view.id
            if (id == View.NO_ID) {
                view.id = android.R.id.message
            } else if (id != android.R.id.message) {
                throw IllegalArgumentException("You must set the ID value of TextView to android.R.id.message")
            }
            return view
        }
        val messageView = view.findViewById<View>(android.R.id.message)
        if (messageView is TextView) {
            return messageView
        }
        throw IllegalArgumentException("You must include a TextView with an ID value of android.R.id.message")
    }

    override fun show() {
        mToastImpl.show()
    }

    override fun cancel() {
        mToastImpl.cancel()
    }

    override fun setText(textId: Int) {
        val view = mView
        if (view != null) {
            setText(view.resources.getString(textId))
        }
    }

    override fun setText(charSequence: CharSequence) {
        mMessageView?.text = charSequence
    }

    override fun setView(view: View?) {
        mView = view
        mMessageView = if (view == null) {
            null
        } else {
            findMessageView(view)
        }
    }

    override fun getView(): View? = mView

    override fun setDuration(duration: Int) {
        mDuration = duration
    }

    override fun getDuration(): Int = mDuration

    override fun setGravity(gravity: Int, xOffset: Int, yOffset: Int) {
        mGravity = gravity
        mXOffset = xOffset
        mYOffset = yOffset
    }

    override fun getGravity(): Int = mGravity

    override fun getXOffset(): Int = mXOffset

    override fun getYOffset(): Int = mYOffset

    override fun setMargin(horizontalMargin: Float, verticalMargin: Float) {
        mHorizontalMargin = horizontalMargin
        mVerticalMargin = verticalMargin
    }

    override fun getHorizontalMargin(): Float = mHorizontalMargin

    override fun getVerticalMargin(): Float = mVerticalMargin
}
