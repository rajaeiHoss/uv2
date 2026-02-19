package com.hjq.toast

import android.app.Application
import android.os.Handler
import android.widget.Toast
import java.lang.reflect.Field

class SafeToast(application: Application) : SystemToast(application) {
    init {
        try {
            val declaredField: Field = Toast::class.java.getDeclaredField("mTN")
            declaredField.isAccessible = true
            val tn = declaredField.get(this)
            val handlerField: Field = declaredField.type.getDeclaredField("mHandler")
            handlerField.isAccessible = true
            handlerField.set(tn, SafeHandler(handlerField.get(tn) as Handler))
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        }
    }
}
