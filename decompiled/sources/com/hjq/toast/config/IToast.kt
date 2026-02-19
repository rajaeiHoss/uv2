package com.hjq.toast.config

import android.view.View
import android.widget.TextView

interface IToast {
    fun cancel()

    fun findMessageView(view: View): TextView {
        if (view is TextView) {
            if (view.id == View.NO_ID) {
                view.id = android.R.id.message
            } else if (view.id != android.R.id.message) {
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

    fun getDuration(): Int

    fun getGravity(): Int

    fun getHorizontalMargin(): Float

    fun getVerticalMargin(): Float

    fun getView(): View?

    fun getXOffset(): Int

    fun getYOffset(): Int

    fun setDuration(duration: Int)

    fun setGravity(gravity: Int, xOffset: Int, yOffset: Int)

    fun setMargin(horizontalMargin: Float, verticalMargin: Float)

    fun setText(textId: Int)

    fun setText(text: CharSequence)

    fun setView(view: View?)

    fun show()
}
