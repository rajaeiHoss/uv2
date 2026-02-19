package com.hjq.toast

import android.app.Application
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.hjq.toast.config.IToast

open class SystemToast(application: Application) : Toast(application), IToast {
    private var mMessageView: TextView? = null

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

    override fun setView(view: View?) {
        super.setView(view)
        mMessageView = if (view == null) {
            null
        } else {
            findMessageView(view)
        }
    }

    override fun setText(charSequence: CharSequence) {
        super.setText(charSequence)
        mMessageView?.text = charSequence
    }
}
