package androidx.activity.result

interface ActivityResultCallback<O> {
    fun onActivityResult(result: O)
}
