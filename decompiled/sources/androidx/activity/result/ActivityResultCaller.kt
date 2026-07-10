package androidx.activity.result

import androidx.activity.result.contract.ActivityResultContract

@JvmSuppressWildcards
interface ActivityResultCaller {
    fun <I, O> registerForActivityResult(
        contract: ActivityResultContract<I, O>,
        callback: ActivityResultCallback<O>,
    ): ActivityResultLauncher<I>

    fun <I, O> registerForActivityResult(
        contract: ActivityResultContract<I, O>,
        registry: ActivityResultRegistry,
        callback: ActivityResultCallback<O>,
    ): ActivityResultLauncher<I>
}
