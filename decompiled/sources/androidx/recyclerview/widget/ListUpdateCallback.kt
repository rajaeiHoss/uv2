package androidx.recyclerview.widget

interface ListUpdateCallback {
    fun onChanged(position: Int, count: Int, payload: Any?)

    fun onInserted(position: Int, count: Int)

    fun onMoved(fromPosition: Int, toPosition: Int)

    fun onRemoved(position: Int, count: Int)
}
