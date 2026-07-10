package androidx.core.view

interface WindowInsetsAnimationControlListenerCompat {
    fun onCancelled(controller: WindowInsetsAnimationControllerCompat?)

    fun onFinished(controller: WindowInsetsAnimationControllerCompat)

    fun onReady(controller: WindowInsetsAnimationControllerCompat, types: Int)
}
