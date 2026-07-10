package okhttp3

import java.io.IOException
import okio.Timeout

interface Call : Cloneable {
    interface Factory {
        fun newCall(request: Request): Call
    }

    fun cancel()

    public override fun clone(): Call

    fun enqueue(callback: Callback)

    @Throws(IOException::class)
    fun execute(): Response

    val isCanceled: Boolean

    val isExecuted: Boolean

    fun request(): Request

    fun timeout(): Timeout
}
