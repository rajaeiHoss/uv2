package com.hjq.bar

import android.view.View

interface OnTitleBarListener {
    fun onLeftClick(view: View)

    fun onRightClick(view: View)

    fun onTitleClick(view: View)
}
