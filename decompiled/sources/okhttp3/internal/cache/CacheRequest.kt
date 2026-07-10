package okhttp3.internal.cache

import java.io.IOException
import okio.Sink

interface CacheRequest {
    fun abort()

    @Throws(IOException::class)
    fun body(): Sink
}
