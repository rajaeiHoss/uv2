package androidx.core.text

interface TextDirectionHeuristicCompat {
    fun isRtl(text: CharSequence, start: Int, count: Int): Boolean

    fun isRtl(text: CharArray, start: Int, count: Int): Boolean
}
