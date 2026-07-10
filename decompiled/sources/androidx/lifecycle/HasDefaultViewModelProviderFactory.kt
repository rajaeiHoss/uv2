package androidx.lifecycle

interface HasDefaultViewModelProviderFactory {
    fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory
}
