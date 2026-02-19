package com.hjq.toast.config

import android.app.Application

interface IToastStrategy {
    fun bindStyle(style: IToastStyle<*>)

    fun cancelToast()

    fun createToast(application: Application): IToast

    fun registerStrategy(application: Application)

    fun showToast(text: CharSequence)
}
