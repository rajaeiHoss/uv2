package androidx.savedstate

import androidx.lifecycle.LifecycleOwner

interface SavedStateRegistryOwner : LifecycleOwner {
    fun getSavedStateRegistry(): SavedStateRegistry
}
