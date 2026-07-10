package androidx.fragment.app

import android.os.Bundle

interface FragmentResultListener {
    fun onFragmentResult(requestKey: String, result: Bundle)
}
