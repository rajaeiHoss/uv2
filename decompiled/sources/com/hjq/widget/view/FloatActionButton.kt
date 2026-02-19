package com.hjq.widget.view

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatImageView

class FloatActionButton : AppCompatImageView {
    private val mHideRunnable = Runnable { hideNow() }
    private val mShowRunnable = Runnable { showNow() }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun show() {
        removeCallbacks(mHideRunnable)
        postDelayed(mShowRunnable, 600)
    }

    fun hide() {
        removeCallbacks(mShowRunnable)
        post(mHideRunnable)
    }

    private fun showNow() {
        if (visibility == View.INVISIBLE) {
            visibility = View.VISIBLE
        }
        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator.duration = ANIM_TIME
        animator.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            alpha = value
            scaleX = value
            scaleY = value
        }
        animator.start()
    }

    private fun hideNow() {
        if (visibility != View.INVISIBLE) {
            val animator = ValueAnimator.ofFloat(1f, 0f)
            animator.duration = ANIM_TIME
            animator.addUpdateListener { animation ->
                val value = animation.animatedValue as Float
                alpha = value
                scaleX = value
                scaleY = value
                if (value == 0f) {
                    visibility = View.INVISIBLE
                }
            }
            animator.start()
        }
    }

    companion object {
        private const val ANIM_TIME = 300L
    }
}
