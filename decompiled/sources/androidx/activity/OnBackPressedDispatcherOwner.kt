package androidx.activity

import androidx.lifecycle.LifecycleOwner

interface OnBackPressedDispatcherOwner : LifecycleOwner {
    fun getOnBackPressedDispatcher(): OnBackPressedDispatcher
}
