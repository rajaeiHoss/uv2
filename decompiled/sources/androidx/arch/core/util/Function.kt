package androidx.arch.core.util

interface Function<I, O> {
    fun apply(input: I): O
}
