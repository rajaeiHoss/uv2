package androidx.fragment.app

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner

interface FragmentResultOwner {
    fun clearFragmentResult(requestKey: String)

    fun clearFragmentResultListener(requestKey: String)

    fun setFragmentResult(requestKey: String, result: Bundle)

    fun setFragmentResultListener(
        requestKey: String,
        lifecycleOwner: LifecycleOwner,
        listener: FragmentResultListener,
    )
}
