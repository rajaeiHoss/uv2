package androidx.core.view

import android.view.View

interface ViewPropertyAnimatorListener {
    fun onAnimationCancel(view: View)

    fun onAnimationEnd(view: View)

    fun onAnimationStart(view: View)
}
