package androidx.activity.contextaware

import android.content.Context

interface ContextAware {
    fun addOnContextAvailableListener(listener: OnContextAvailableListener)

    fun peekAvailableContext(): Context?

    fun removeOnContextAvailableListener(listener: OnContextAvailableListener)
}
