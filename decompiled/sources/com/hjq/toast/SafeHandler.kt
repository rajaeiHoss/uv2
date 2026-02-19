package com.hjq.toast

import android.os.Handler
import android.os.Message
import android.view.WindowManager

class SafeHandler(
    private val mHandler: Handler
) : Handler() {
    override fun handleMessage(message: Message) {
        try {
            mHandler.handleMessage(message)
        } catch (e: WindowManager.BadTokenException) {
            e.printStackTrace()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }
}
