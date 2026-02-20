package androidx.lifecycle

interface Observer<T> {
    fun onChanged(value: T)
}
