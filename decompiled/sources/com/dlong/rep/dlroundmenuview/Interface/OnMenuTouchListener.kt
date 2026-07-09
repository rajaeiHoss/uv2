package com.dlong.rep.dlroundmenuview.Interface

import android.view.MotionEvent

interface OnMenuTouchListener {
    fun OnTouch(event: MotionEvent?, position: Int)
}
