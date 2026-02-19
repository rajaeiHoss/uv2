package com.hjq.widget.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class CountdownView : AppCompatTextView, Runnable {
    private var mCurrentSecond = 0
    private var mRecordText: CharSequence? = null
    private var mTotalSecond = 60

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun setTotalTime(totalSecond: Int) {
        mTotalSecond = totalSecond
    }

    fun start() {
        mRecordText = text
        isEnabled = false
        mCurrentSecond = mTotalSecond
        post(this)
    }

    fun stop() {
        mCurrentSecond = 0
        text = mRecordText
        isEnabled = true
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        removeCallbacks(this)
    }

    override fun run() {
        val second = mCurrentSecond
        if (second == 0) {
            stop()
            return
        }
        mCurrentSecond = second - 1
        text = "$mCurrentSecond $TIME_UNIT"
        postDelayed(this, 1000)
    }

    companion object {
        private const val TIME_UNIT = "S"
    }
}
