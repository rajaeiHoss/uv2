package androidx.core.util

interface Predicate<T> {
    fun test(value: T): Boolean
}
